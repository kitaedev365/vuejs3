<template>
  <div>
    <h2>Projects</h2><hr/>
    
    <!-- 프로젝트 목록 -->
    <ul class="list-group">
      <li class="list-group-item" v-for="project in projects" :key="project.id">
        <router-link :to="`/projects/${project.id}`">{{ project.name }}</router-link>
      </li>
    </ul>
    
    <!-- 프로젝트 등록 폼 -->
    <hr />
    <div v-if="isAuthenticated">
      <h3>새 프로젝트 등록</h3><hr>
      <form @submit.prevent="addProject">
        <div class="mb-3">
          <label for="projectName" class="form-label">프로젝트명</label>
          <input type="text" id="projectName" v-model="newProject.name" class="form-control" required />
        </div>
        <div class="mb-3">
          <label for="projectDescription" class="form-label">프로젝트 설명</label>
          <textarea id="projectDescription" v-model="newProject.description" class="form-control" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">프로젝트 등록</button>
      </form>
    </div>  
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useAuthStore } from '../store/authStore'
const authStore = useAuthStore()
// 반응형 computed 속성으로 변경
const isAuthenticated = authStore.isAuthenticated
const projects = ref([])
const newProject = ref({
  name: '',
  description: ''
})

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8085/api/projects')
    projects.value = response.data
  } catch (error) {
    console.error('Error fetching projects:', error)
  }
})

// 프로젝트 추가 함수
const addProject = async () => {
  try {
    const response = await axios.post('http://localhost:8085/api/projects', newProject.value)
    projects.value.push(response.data)  // 새 프로젝트를 목록에 추가
    newProject.value.name = ''  // 입력 필드 초기화
    newProject.value.description = ''
    alert('Project added successfully!')
  } catch (error) {
    console.error('Error adding project:', error)
  }
}
</script>