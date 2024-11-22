<template>
    <div>
      <h3>Injected Component</h3>
      <p>Message: {{ message }}</p>
    </div>
</template>
<script setup>
import { inject } from 'vue';
const message = inject('message');
</script>