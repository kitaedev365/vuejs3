<template>
  <div>
    <h2>태스크 상세</h2>
    <div v-if="store.loading" class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div v-else-if="task">
      <h3>{{ task.title }}</h3>
      <p><strong>ID:</strong> {{ task.id }}</p>
      <p><strong>상태:</strong> <span :class="badgeClass">{{ task.completed ? '완료' : '진행 중' }}</span></p>
      <button class="btn btn-secondary mt-3" @click="goBack">태스크 목록으로 돌아가기</button>
    </div>
    <div v-else class="alert alert-warning">
      태스크를 찾을 수 없습니다.
    </div>
  </div>
</template>
<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import store from '../store/index.js'
const route = useRoute()
const router = useRouter()
const storeRef = store
const taskId = computed(() => parseInt(route.params.id))
const task = computed(() => storeRef.tasks.find(t => t.id === taskId.value))
const badgeClass = computed(() => {
  return storeRef.tasks.find(t => t.id === taskId.value)?.completed
    ? 'badge bg-success'
    : 'badge bg-warning'
})
const goBack = () => {
  router.push('/tasks')
}
</script>    