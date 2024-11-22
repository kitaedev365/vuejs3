<template>
    <div class="container">
        <h1>Home Page</h1>
        <button @click="goToAbout">Go to About</button>
    </div>
</template>
<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();
const goToAbout = () => {
  router.push({ name: 'about' }); // 명명된 라우트로 이동
};
</script>
<style scoped>
.container {
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
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>