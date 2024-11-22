<template>
  <div class="container">
    <h1 class="center-align">Sub Parent Component</h1>
    <SubChildComponent v-model="parentValue" />
  </div>
</template>
<script setup>
import { ref } from 'vue';
import SubChildComponent from './SubChildComponent.vue';
const parentValue = ref('');  // 상태 정의
</script>