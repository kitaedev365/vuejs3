<template>
    <teleport to="body">
      <div class="notification">
        This notification is teleported to the body!
      </div>
    </teleport>
  </template>
  <script setup>
  </script>
  <style scoped>
  .notification {
    background: yellow;
    padding: 10px;
    position: fixed;
    top: 20px;
    right: 20px;
  }
  </style>
  