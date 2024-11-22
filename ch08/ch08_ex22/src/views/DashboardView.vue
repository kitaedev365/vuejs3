<template>
  <div>
    <h1>Dashboard</h1>
    <router-link to="/dashboard/profile">Profile</router-link>
    <router-link to="/dashboard/settings">Settings</router-link>
    <router-view /> <!-- 중첩된 라우트를 렌더링할 위치 -->
  </div>
</template>
<script setup>
</script> 
<style>
a {
  padding: 14px;
}
</style> 