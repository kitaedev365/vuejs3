<template>
    <div>
      <h4>Child Component</h4>
      <p>{{ sharedData }}</p>
    </div>
  </template>
  <script setup>
  import { inject } from 'vue';
  const sharedData = inject('sharedData');
  </script>
  