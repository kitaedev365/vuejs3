<template>
  <div>
    <nav>
       <router-link to="/about" class="btn">어바웃페이지로</router-link>
       <router-link to="/current" class="btn">currentRoute 객체</router-link>
    </nav>
    <h1>Current Route</h1>
    <p>Route Name: {{ route.name }}</p>
    <p>Route Path: {{ route.path }}</p>
  </div>
</template>
<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();  // 현재 라우트 정보 가져오기
</script>    