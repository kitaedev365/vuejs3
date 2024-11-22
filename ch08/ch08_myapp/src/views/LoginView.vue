<template>
  <div class="container">
    <h1>Login</h1>
    <form @submit.prevent="handleLogin">
      <div class="input-field">
        <input
          v-model="username"
          type="text"
          placeholder="Username"
          required
        />
      </div>
      <div class="input-field">
        <input
          v-model="password"
          type="password"
          placeholder="Password"
          required
        />
      </div>
      <button class="btn" type="submit">
        Login
      </button>
    </form>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const username = ref('');
const password = ref('');
const router = useRouter();
const handleLogin = async () => { // 인증 로직은 모의 처리
  if (username.value === 'kkt' && password.value === '1234') {
    localStorage.setItem('token', 'mockToken');
    router.push({ name: 'dashboard', params: { id: username.value } });
  } else {
    alert('Invalid credentials');
  }
};
</script>