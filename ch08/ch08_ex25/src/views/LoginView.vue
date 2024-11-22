<template>
  <div class="login-view">
    <form @submit.prevent="handleLogin">
      <input v-model="username" placeholder="Username" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
  </div>  
</template>
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const username = ref('');
const password = ref('');
const router = useRouter();
const handleLogin = async () => {
  console.log("로그인 처리중");
  router.push({ name: 'dashboard' });
};
</script>
<style scoped>
.login-view {
  width: 200px;
  margin: 20px auto;
  padding: 20px;
  background-color: #abcdef;
}
input {
  display: block;
  line-height: 1.8;
  padding: 4px;
  margin: 6px;
}
button {
  display: block;
  width:80px;
  margin: 12px auto;
  outline: 0;
  border:0;
  border-radius: 8px;
  background-color: deepskyblue;
  line-height: 1.8;
}
</style>  
