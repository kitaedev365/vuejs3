<template>
  <div class="card-panel teal lighten-2">
    <h3>Child Component</h3>
    <p>Parent Message: {{ parentMessage }}</p>
    <div class="input-field">
      <input type="text" v-model="childMessage" placeholder="Child Message" />
      <label for="childMessage" class="active" style="color: #fff">Child Message</label>
    </div>
    <button class="btn" @click="emitCustomEvent">Send to Parent</button>
  </div>
</template>
<script setup>
import { ref } from 'vue';
const props = defineProps({   // props를 defineProps로 정의
  parentMessage: String,
});
const emit = defineEmits(['custom-event']);    // emits를 defineEmits로 정의
const childMessage = ref('');   // 상태 변수
const emitCustomEvent = () => {    // emit을 사용하여 이벤트 발생
  emit('custom-event', childMessage.value);
};
</script>