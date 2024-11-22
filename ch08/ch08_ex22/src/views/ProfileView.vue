<template>
  <div class="container">
    <h2>사용자 프로필</h2>
    <p>안녕하세요, {{ user.name }}!</p>
    <p>이메일: {{ user.email }}</p>
    <p>회원 가입일: {{ user.joined }}</p>
    <button @click="editProfile" class="btn">프로필 수정</button>
    <div v-if="isEditing">
      <h3>프로필 수정</h3>
      <label>
        Name:
        <input v-model="user.name" type="text" />
      </label>
      <label>
        Email:
        <input v-model="user.email" type="email" />
      </label>
      <button @click="saveProfile" class="btn">저장</button>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue';
const user = ref({  // 사용자 프로필 정보
  name: '김기태',
  email: 'kitaedev365@gmail.com',
  joined: 'January 1, 2025'
});
const isEditing = ref(false);   // 프로필 편집 상태
const editProfile = () => {    // 편집 모드로 전환하는 함수
  isEditing.value = true;
};
const saveProfile = () => {    // 프로필 저장 함수 (여기서는 콘솔에 출력하는 예시)
  console.log('Profile saved:', user.value);
  isEditing.value = false; // 편집 모드 종료
};
</script>
<style scoped>
</style>