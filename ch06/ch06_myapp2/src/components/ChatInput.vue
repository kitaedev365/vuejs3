<template>
  <div class="field has-addons">
    <div class="control is-expanded">
      <input class="input" v-model="inputValue" @keyup.enter="submitMessage" placeholder="Type a message" />
    </div>
    <div class="control">
      <button @click="submitMessage" class="button is-info">Send</button>
    </div>  
  </div>
</template>
<script setup>
import { ref } from 'vue';
const inputValue = ref(''); // 반응형 데이터 선언
const emit = defineEmits(['sendMessage']); // defineEmits로 emit 함수 선언
const submitMessage = () => {  // 메시지를 제출하는 함수
  if (inputValue.value.trim()) {
    emit('sendMessage', inputValue.value);  // 'sendMessage' 이벤트로 값을 emit
    inputValue.value = '';  // 전송 후 입력 필드 초기화
  }
};
</script>