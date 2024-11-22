<template>
    <div>
      <h2>Notices</h2><hr/>
      <!-- 공지사항 목록 -->
      <ul class="list-group">
        <li class="list-group-item" v-for="notice in notices" :key="notice.id" style="min-height:14px">
          <router-link :to="`/notices/${notice.id}`">{{ notice.title }}</router-link>
        </li>
      </ul>
  
      <hr />
      <div v-if="isAuthenticated">
        <h3>공지사항 등록</h3>
        <form @submit.prevent="addNotice">
            <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" v-model="newNotice.title" required />
            </div>
            <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" v-model="newNotice.content" rows="3" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">글 등록</button>
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

  const notices = ref([]) // 공지사항 목록
  const newNotice = ref({ title: '', content: '' }) // 새 공지사항 데이터
  
  // 페이지 로드 시 공지사항 목록을 불러오는 함수
  onMounted(async () => {
    try {
      const response = await axios.get('http://localhost:8085/api/notices/list')
      notices.value = response.data
    } catch (error) {
      console.error('Error fetching notices:', error)
    }
  })
  
  // 새 공지사항을 추가하는 함수
  const addNotice = async () => {
    try {
      const response = await axios.post('http://localhost:8085/api/notices', {
        title: newNotice.value.title,
        content: newNotice.value.content
      })
      // 새 공지사항 추가 후 목록 다시 가져오기
      notices.value.push(response.data)
      newNotice.value.title = ''
      newNotice.value.content = ''
    } catch (error) {
      console.error('Error adding new notice:', error)
    }
  }
  </script>