<template>
  <div>
    <h1>User ID: {{ userId }}</h1>
  </div>
</template>

<script setup>
// `defineProps`는 `<script setup>`에서 직접 사용할 수 있습니다.
const props = defineProps({ 
  id: String  // id prop의 타입 지정
});

// props를 구조 분해 할당하지 않고 그대로 사용
const userId = props.id;
</script>

<style scoped>
h1 {
  color: #9C27B0;
}
</style>