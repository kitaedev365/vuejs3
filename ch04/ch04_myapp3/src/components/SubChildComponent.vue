<template>
  <div class="card-panel blue lighten-2">
    <h3>SubChild Component</h3>
    <div class="input-field">
      <input id="childInput" :value="modelValue" @input="updateValue" />
      <label for="childInput" class="active">Child Input</label>
    </div>
  </div>
</template>
<script setup>
import { defineProps, defineEmits } from 'vue';
const props = defineProps({   // Props 정의
  modelValue: String
});
const emit = defineEmits(['update:modelValue']);   // Emits 정의
const updateValue = (event) => {   // 메서드 정의
  emit('update:modelValue', event.target.value);
};
</script>