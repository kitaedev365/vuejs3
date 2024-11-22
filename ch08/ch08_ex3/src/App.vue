<template>
  <div>
    <h1>Welcome to the Vue Application</h1>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link :to="{ name: 'user', params: { id: 1 } }">User Profile (ID: 1)</router-link>
    </nav>
    <router-view /> <!-- 현재 활성화된 라우트의 기본 컴포넌트를 렌더링 -->
  </div>
</template>

<script setup>
</script>

<style scoped>
nav {
  margin-bottom: 20px;
}
nav a {
  margin: 0 10px;
  color: #42b983;
  text-decoration: none;
}
nav a.router-link-active {
  font-weight: bold;
}
</style>