<template>
    <form @submit.prevent="submitForm">
      <label for="name">Name</label>
      <input type="text" v-model="formData.name" id="name" />
      <label for="email">Email</label>
      <input type="email" v-model="formData.email" id="email" />
      <button type="submit" class="button">Submit</button>
    </form>
  </template>
  <script setup>
  import { ref } from 'vue';
  const formData = ref({
    name: '',
    email: ''
  });
  const submitForm = () => {
    alert(`Submitted: ${formData.value.name}, ${formData.value.email}`);
  };
  </script>
  <style scoped>
  form {
    margin-bottom: 1rem;
  }
  </style>
  