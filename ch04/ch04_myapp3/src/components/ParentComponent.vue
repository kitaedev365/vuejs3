<template>
    <div class="card-panel teal lighten-4">
      <h2>Parent Component</h2>
      <ChildComponent @custom-event="handleCustomEvent" :parentMessage="parentMessage" /><!-- 자식 컴포넌트에 데이터 전달 (props) -->
      <div class="input-field"><!-- 부모의 상태 -->
        <input type="text" v-model="parentMessage" placeholder="Parent Message" />
        <label for="parentMessage" class="active" style="padding-top:12px">Parent Message</label>
      </div>
    </div>
  </template>
  <script setup>
  import { ref } from 'vue';
  import ChildComponent from './ChildComponent.vue';
  const parentMessage = ref('Hello from Parent');
  const handleCustomEvent = (message) => { // 자식에서 받은 이벤트를 처리
    parentMessage.value = message; // 자식에서 전달된 메시지를 부모의 상태에 업데이트
  };
  </script>