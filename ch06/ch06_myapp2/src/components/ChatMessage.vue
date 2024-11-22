<template>
  <div class="message is-primary mb-2">
    <div class="message-header">
      <p>{{ message.timestamp }}</p>
    </div>
    <div class="message-body">
      {{ message.text }}
    </div>
  </div>
</template>
<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  message: {
    type: Object,
    required: true,
  },
})
</script>
<style scoped>
.message {
  word-wrap: break-word;
}
</style>    