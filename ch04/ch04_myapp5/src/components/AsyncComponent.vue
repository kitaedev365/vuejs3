<template>
  <div>
    <p>Loading async component...</p>
    <AsyncContent v-if="AsyncContent" />
  </div>
</template>

<script setup>
import { defineAsyncComponent } from 'vue';

// 비동기 컴포넌트 로드
const AsyncContent = defineAsyncComponent(() => import('./AsyncContent.vue'));
</script>

<style scoped>
p {
  color: gray;
}
</style>
