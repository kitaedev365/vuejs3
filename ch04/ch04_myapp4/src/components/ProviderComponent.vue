<template>
    <div>
      <h2>Provider Component</h2>
      <button @click="changeMessage">Change Message</button>
      <slot></slot>
    </div>
</template>
<script setup>
import { provide, ref } from 'vue';
const message = ref('Hello from Provider');
provide('message', message);
const changeMessage = () => {
message.value = 'Message Updated!';
};
</script>