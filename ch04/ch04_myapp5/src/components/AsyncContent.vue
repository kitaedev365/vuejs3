<template>
    <div class="async-content">
      <h3>This is the asynchronously loaded content!</h3>
      <p>You can use this component to load content lazily when needed.</p>
    </div>
  </template>
  
  <script setup>
  </script>
  
  <style scoped>
  .async-content {
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    padding: 20px;
    border-radius: 4px;
    text-align: center;
    margin-top: 20px;
  }
  
  h3 {
    color: #333;
  }
  
  p {
    color: #666;
  }
  </style>
  