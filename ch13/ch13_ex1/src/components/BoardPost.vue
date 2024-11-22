<template>
  <div>
    <h2>Create a Board Post</h2>
    <form @submit.prevent="submitPost">
      <input v-model="title" placeholder="Title" />
      <textarea v-model="content" placeholder="Content"></textarea>
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
  if (title.value && content.value) {
    message.value = 'Post created successfully!'
    title.value = ''
    content.value = ''
  } else {
    message.value = 'Please fill out all fields.'
  }
}
</script>  