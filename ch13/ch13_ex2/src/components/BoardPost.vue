<template>
  <div>
    <form @submit.prevent="submitPost">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="title" id="title" />
      </div>
      <div>
        <label for="content">Content:</label>
        <textarea v-model="content" id="content"></textarea>
      </div>
      <button type="submit">Submit</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>
<script setup>
import { ref } from 'vue'
const title = ref('')
const content = ref('')
const message = ref('')
const submitPost = () => {
  if (!title.value || !content.value) {
    message.value = 'Please fill out all fields.'
  } else {
    message.value = 'Post created successfully!'
    // Clear the input fields
    title.value = ''
    content.value = ''
  }
}
</script>
<style scoped>
.read-the-docs {
  color: #888;
}
</style>