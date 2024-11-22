<template>
    <div class="container">
      <h2>Settings</h2>
      <form @submit.prevent="saveSettings">
        <label>
          알림 기능:
          <input type="checkbox" v-model="settings.notifications" />
        </label>
        <label>
          언어:
          <select v-model="settings.language">
            <option value="ko">Korean</option>
            <option value="en">English</option>
            <option value="fr">French</option>
            <option value="es">Spanish</option>
          </select>
        </label>
        <button type="submit" class="btn">설정 저장</button>
      </form>
      <p v-if="savedMessage" class="message">{{ savedMessage }}</p>
    </div>
  </template>
  <script setup>
  import { ref } from 'vue';
  const settings = ref({   // 설정 데이터
    notifications: true,
    language: 'ko'
  });
  const savedMessage = ref('');   // 저장 완료 메시지 상태
  const saveSettings = () => {   // 설정 저장 함수
    console.log('Settings saved:', settings.value);     // 실제 저장 로직은 여기에 포함될 수 있습니다.
    savedMessage.value = 'Settings have been saved!';     // 저장 메시지를 표시
    setTimeout(() => {     // 일정 시간 후 메시지 숨기기
      savedMessage.value = '';
    }, 3000);
  };
  </script>
  <style scoped>
  </style>  