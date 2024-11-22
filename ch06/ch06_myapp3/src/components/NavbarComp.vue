<template>
  <nav class="top-bar">
    <div class="top-bar-left">
      <ul class="dropdown menu" data-dropdown-menu>
        <li class="menu-text">CRM 시스템</li>
        <li><router-link to="/">홈</router-link></li>
        <li><router-link to="/customers">고객 목록</router-link></li>
        <li><router-link to="/about">정보</router-link></li>
      </ul>
    </div>
  </nav>
</template>
<script setup>
</script>
<style scoped>
.top-bar {
  background-color: #2c3e50;
  color: white;
  padding: 10px 20px;
}
.menu-text {
  font-weight: bold;
}
.menu a {
  color: white;
  text-decoration: none;
}
.menu a:hover {
  text-decoration: underline;
}
</style>  