<template>
  <div class="container mt-5">
    <h3>Project Details</h3><hr/>
    <div v-if="project">
      <h4>{{ project.name }}</h4>
      <p>{{ project.description }}</p>
      <router-link to="/projects" class="btn btn-secondary">Back to Projects</router-link> &nbsp; 
      <button @click="deleteProject" class="btn btn-danger ml-2" v-if="isAuthenticated">Delete Project</button>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import { useAuthStore } from '../store/authStore'
const authStore = useAuthStore()
// 반응형 computed 속성으로 변경
const isAuthenticated = authStore.isAuthenticated
const project = ref(null)
const route = useRoute()
const router = useRouter()

// 프로젝트 상세 데이터 가져오기
onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8085/api/projects/${route.params.id}`)
    project.value = response.data
  } catch (error) {
    console.error('Error fetching project details:', error)
  }
})

// 프로젝트 삭제 함수
const deleteProject = async () => {
  try {
    await axios.delete(`http://localhost:8085/api/projects/${route.params.id}`)
    alert('Project deleted successfully!')
    router.push('/projects')  // 프로젝트 목록 페이지로 리다이렉션
  } catch (error) {
    console.error('Error deleting project:', error)
  }
}
</script>