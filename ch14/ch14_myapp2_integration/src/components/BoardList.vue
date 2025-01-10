<template>
  <div class="container">
    <h1>DashBoard</h1>
    <button @click="saveChanges" class="btn btn-primary mb-3">Save Changes</button>
    <hr />
    <div class="row gx-4 gy-4">
      <div class="col-md-6 col-lg-4 d-flex flex-column" v-for="board in boards" :key="board.id">
        <div class="p-3 border bg-light rounded flex-grow-1">
          <h3 class="text-center">{{ board.name }}</h3>
          <!-- Button to add a new post -->
          <button @click="addPost(board)" class="btn btn-success mb-3">Add Post</button>
          <!-- Draggable posts list -->
          <draggable 
            v-model="board.posts" 
            group="posts" 
            item-key="id" 
            class="list-group mt-3"
            @end="onDragEnd(board)"
          >
            <template #item="{ element: post }">
              <li class="list-group-item card mb-3">
                <div class="card-body">
                  <p class="card-text">
                    <strong class="text-muted">#{{ post.position }}</strong>
                  </p>
                  <h5 v-if="!post.isEditing" @click="editPost(post, 'title')">{{ post.title }}</h5>
                  <input 
                    v-if="post.isEditing && post.editingField === 'title'"
                    v-model="post.title"
                    @blur="savePost(post, 'title')"
                    @keyup.enter="savePost(post, 'title')"
                    class="form-control"
                  />
                  <hr />
                  <p v-if="!post.isEditing" @click="editPost(post, 'content')">{{ post.content }}</p>
                  <textarea 
                    v-if="post.isEditing && post.editingField === 'content'"
                    v-model="post.content"
                    @blur="savePost(post, 'content')"
                    @keyup.enter="savePost(post, 'content')"
                    class="form-control"
                  />
                  <!-- Delete button for each post -->
                  <button @click="deletePost(board, post)" class="btn btn-danger mt-2">Delete</button>
                </div>
              </li>
            </template>
          </draggable>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import draggable from 'vuedraggable'

const boards = ref([])

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8085/api/boards/list-with-posts')
    boards.value = response.data
  } catch (error) {
    console.error('Error fetching boards and posts:', error)
  }
})

const saveChanges = async () => {
  try {
    const updatedBoards = boards.value.map(board => ({
      id: board.id,
      posts: board.posts.map((post, index) => ({ id: post.id, position: index + 1 }))
    }))
    await axios.post('http://localhost:8085/api/boards/save-order', updatedBoards)
    alert('Changes saved successfully!')
  } catch (error) {
    console.error('Error saving changes:', error)
  }
}

const onDragEnd = (board) => {
  // Recalculate positions within the dragged board
  board.posts.forEach((post, index) => {
    post.position = index + 1
  })
}

// Edit post method
const editPost = (post, field) => {
  post.isEditing = true
  post.editingField = field
}

// Save post method
const savePost = async (post, field) => {
  post.isEditing = false
  post.editingField = null

  // Update post in the database or locally
  try {
    await axios.put(`http://localhost:8085/api/posts/${post.id}`, {
      [field]: post[field]  // Update the specific field (title or content)
    })
    console.log(`${field} updated successfully for post ${post.id}`)
  } catch (error) {
    console.error(`Error updating ${field}:`, error)
  }
}

// Add new post method
const addPost = (board) => {
  const newPost = {
    title: 'New Post',
    content: 'Content of the new post',
    position: board.posts.length + 1,
    isEditing: true, // Set to true to start editing
    editingField: 'title' // Start editing the title
  }
  board.posts.push(newPost)
}

// Delete post method
const deletePost = async (board, post) => {
  try {
    // Remove post from the local board
    board.posts = board.posts.filter(p => p.id !== post.id)

    // Delete post from the database
    await axios.delete(`http://localhost:8085/api/posts/${post.id}`)
    console.log(`Post ${post.id} deleted successfully`)
  } catch (error) {
    console.error('Error deleting post:', error)
  }
}
</script>