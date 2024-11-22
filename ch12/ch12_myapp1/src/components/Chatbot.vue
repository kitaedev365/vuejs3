<template>
  <div class="grid-container">
    <h1>상품 상담 챗봇</h1>
    <div>
      <input type="text" v-model="question" placeholder="질문을 입력하세요" />
      <button @click="sendQuestion" class="button primary">질문하기</button>
    </div>
    <div v-if="loading">응답을 기다리는 중...</div>
    <div v-if="answer">
      <h2>응답</h2><p>{{ answer }}</p>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
const question = ref('');
const answer = ref('');
const loading = ref(false);
const sendQuestion = async () => {
  loading.value = true;
  answer.value = ''; // 이전 응답 초기화
  try {
    const response = await axios.post('http://localhost:8000/chatbot', {
      question: question.value,
    });
    answer.value = response.data.answer;
  } catch (error) {
    console.error("챗봇 요청 실패:", error);
    answer.value = "오류가 발생했습니다. 다시 시도해주세요.";
  } finally {
    loading.value = false;
    question.value = ''; // 질문 입력 필드 초기화
  }
};
</script>
<style scoped>
input {
  margin-right: 10px;
  padding: 5px;
  width: 300px;
}
button {
  padding: 5px;
}
h2 {
  color: #007BFF;
}
</style>    