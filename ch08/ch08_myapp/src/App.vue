<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper">
        <a href="/" class="brand-logo">My App</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li>
            <router-link to="/">Home</router-link>
          </li>
          <li>
            <router-link to="/about">About</router-link>
          </li>
          <li>
            <router-link to="/community">Community</router-link>
          </li>
          <li>
            <router-link to="/dashboard">Dashboard</router-link>
          </li>
          <li v-if="isLoggedIn">
            <a href="#" @click.prevent="handleLogout">Logout</a>
          </li>
          <li v-else>
            <router-link to="/login">Login</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <router-view />
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const isLoggedIn = ref(false);
const router = useRouter();
const checkLoginStatus = () => {  // 로그인 상태 확인 함수
  isLoggedIn.value = !!localStorage.getItem('token');
};
onMounted(() => {  // 컴포넌트가 마운트될 때 로그인 상태 확인
  checkLoginStatus();
});
const handleLogout = () => {  // 로그아웃 처리 함수
  localStorage.removeItem('token');
  isLoggedIn.value = false; // 로그인 상태 업데이트
  router.push('/'); // 로그아웃 후 홈으로 이동
};
</script>