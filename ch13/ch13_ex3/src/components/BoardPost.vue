<template>
  <div>
    <h2>Create a Board Post</h2><hr>
    <form @submit.prevent="submitPost">
      <input v-model="title" placeholder="Title" data-cy="title" />
      <textarea v-model="content" placeholder="Content" data-cy="content"></textarea>
      <button type="submit" data-cy="submit-button">Submit</button>
    </form>
    <p v-if="message" data-cy="message">{{ message }}</p>
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
<style scoped>
input, textarea { 
  display:block; line-height:1.6; 
  padding: 0.5rem;  margin:10px auto; 
  width: 90%; 
}
</style>  