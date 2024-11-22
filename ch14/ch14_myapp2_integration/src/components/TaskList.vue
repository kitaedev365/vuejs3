<template>
    <div>
      <h2>Tasks</h2><hr/>
      <ul class="list-group">
        <li class="list-group-item" v-for="task in tasks" :key="task.id" style="min-height:14px">
          {{ task.name }}
        </li>
      </ul>
    </div>
  </template>
  <script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  const tasks = ref([])
  const baseURL = 'http://localhost:8085/api/tasks' // 서버의 API 주소
  onMounted(async () => {
      try {
          const response = await axios.get(baseURL)
          tasks.value = response.data
          console.log('Tasks:', response.data)
      } catch (error) {
          console.error('Error fetching tasks:', error)
      }
  })
  </script>
  