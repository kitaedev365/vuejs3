<template>
    <div>
      <h2>Async Component</h2>
      <AsyncComponent />
    </div>
</template>
<script setup>
import { defineAsyncComponent } from 'vue';
const AsyncComponent = defineAsyncComponent(() =>
    import('./BaseComponent.vue')
);
</script>