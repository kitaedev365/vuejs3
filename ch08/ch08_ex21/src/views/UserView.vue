<template>
  <div>
    <h1>User ID: {{ userId }}</h1>
  </div>
</template>
<script setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute(); // 현재 라우트 정보 가져오기
const userId = ref(route.params.id); // 반응형 변수로 초기화
// 라우트 파라미터가 변경될 때마다 userId 업데이트
watch(
  () => route.params.id,
  (newId) => {
    userId.value = newId;
  }
);
</script>