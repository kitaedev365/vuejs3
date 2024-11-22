<template>
  <div class="sidebar">
    <h2>Sidebar Menu</h2>
    <ul>
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/">About</router-link></li>
      <li><router-link to="/">Services</router-link></li>
    </ul>
  </div>
</template>
<script setup>
</script>
<style scoped>
.sidebar {
  width: 200px;
  padding: 20px;
  background-color: #eee;
  border-right: 1px solid #ccc;
}
ul { 
  list-style: none;
}
ul a {
  padding: 14px;
}
</style>  