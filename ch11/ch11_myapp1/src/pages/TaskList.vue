<template>
  <div>
    <h2>태스크 목록</h2>
    <div v-if="store.loading" class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div v-else>
      <ul class="list-group">
        <li v-for="task in store.tasks" :key="task.id" class="list-group-item d-flex justify-content-between align-items-center">
          <router-link :to="`/tasks/${task.id}`" class="text-decoration-none">{{ task.title }}</router-link>
          <span v-if="task.completed" class="badge bg-success">완료</span>
          <span v-else class="badge bg-warning">진행 중</span>
        </li>
      </ul>
    </div>
    <div v-if="store.error" class="alert alert-danger mt-3">
      오류가 발생했습니다: {{ store.error.message }}
    </div>
  </div>
</template>
<script setup>
import { onMounted } from 'vue'
import store from '../store/index.js'
const storeRef = store
onMounted(() => {
  storeRef.actions.fetchTasks()
})
</script>
<style scoped>
.list-group-item a {
  flex-grow: 1;
}
</style>    