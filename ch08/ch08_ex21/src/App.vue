<template>
  <div id="app">
    <nav>
      <router-link to="/user/kkt" class="nav-link">User 1</router-link>
      <router-link to="/user/kdc" class="nav-link">User 2</router-link>
    </nav>
    <main>
      <router-view /> <!-- 라우트에 맞는 컴포넌트를 렌더링 -->
    </main>
  </div>
</template>
<script setup>
</script>  
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  padding: 14px;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
