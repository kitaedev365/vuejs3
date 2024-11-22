<template>
  <section>
    <h2>Main Section</h2>
    <form-component />
    
    <!-- 동적 컴포넌트 렌더링 -->
    <component :is="currentComponent" />
    
    <button @click="switchComponent">Switch Component</button>
    <async-component />
    <some-async-component />
  </section>
</template>

<script setup>
import { ref, defineAsyncComponent } from 'vue';
import FormComponent from './FormComponent.vue';
import AsyncComponent from './AsyncComponent.vue';

// 동적 컴포넌트 정의
const DynamicComponentA = defineAsyncComponent(() => import('./DynamicComponentA.vue'));
const DynamicComponentB = defineAsyncComponent(() => import('./DynamicComponentB.vue'));

// 비동기 컴포넌트 정의
const SomeAsyncComponent = defineAsyncComponent(() => import('./SomeAsyncComponent.vue'));

// 현재 렌더링될 컴포넌트 추적
const currentComponent = ref(DynamicComponentA);

// 컴포넌트 전환 함수
const switchComponent = () => {
  currentComponent.value = currentComponent.value === DynamicComponentA ? DynamicComponentB : DynamicComponentA;
};
</script>

<style scoped>
button {
  margin-top: 1rem;
}
</style>