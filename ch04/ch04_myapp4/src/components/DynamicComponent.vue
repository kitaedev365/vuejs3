<template>
    <component :is="componentName" />
  </template>
  <script setup>
  import { ref } from 'vue';
  const componentName = ref('Base');   // 동적으로 로드할 컴포넌트 이름 정의
  </script>
  