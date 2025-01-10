<template>
    <div class="container mt-5">
      <h3>Notice Details</h3><hr/>
      <div v-if="notice">
        <h4>{{ notice.title }}</h4>
        <p>{{ notice.content }}</p>
        
        <!-- 삭제 버튼 추가 -->
        <button @click="deleteNotice" class="btn btn-danger" v-if="isAuthenticated">삭제</button>
        &nbsp;
        <!-- 뒤로가기 버튼 -->
        <router-link to="/notices" class="btn btn-secondary">Back to Notices</router-link>
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
  const notice = ref(null)
  const route = useRoute()
  const router = useRouter()
  
  // 공지사항 상세정보를 불러오는 함수
  onMounted(async () => {
    try {
      const response = await axios.get(`http://localhost:8085/api/notices/${route.params.id}`)
      notice.value = response.data
    } catch (error) {
      console.error('Error fetching notice details:', error)
    }
  })
  
  // 공지사항을 삭제하는 함수
  const deleteNotice = async () => {
    try {
      await axios.delete(`http://localhost:8085/api/notices/${notice.value.id}`)
      alert('Notice deleted successfully')
      router.push('/notices')  // 삭제 후 공지사항 목록으로 이동
    } catch (error) {
      console.error('Error deleting notice:', error)
    }
  }
  </script>