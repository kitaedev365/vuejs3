<template>
    <div>
      <h3>Parent Component</h3>
      <child-component />
    </div>
  </template>
  <script setup>
  import { provide } from 'vue';
  import ChildComponent from './ChildComponent.vue';
  const sharedData = 'This is shared via Provide/Inject!';
  provide('sharedData', sharedData);
  </script>