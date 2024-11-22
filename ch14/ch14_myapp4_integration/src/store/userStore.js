import { defineStore } from 'pinia'
import axios from 'axios'
export const useUserStore = defineStore('user', {
  state: () => ({
    currentUser: null,  // 로그인된 사용자 정보
    users: [],  // 전체 사용자 목록
    user: {},
  }),
  actions: {
    async fetchUser(userId) {
      try {
        const response = await axios.get(`http://localhost:8087/api/users/${userId}`);
        this.currentUser = response.data;
        this.user = response.data;
      } catch (error) {
        console.error('Failed to fetch user:', error);
      }
    },
    async updateUser(userId, userData) {     // 사용자 프로필 수정
      try {
        const response = await axios.put(`http://localhost:8087/api/users/${userId}`, userData)
        this.currentUser = response.data // 업데이트된 사용자 정보
      } catch (error) {
        console.error('Failed to update user:', error)
      }
    },
    async fetchUsers() {     // 사용자 목록 조회
      try {
        const response = await axios.get('http://localhost:8087/api/users')
        this.users = response.data
      } catch (error) {
        console.error('Failed to fetch users:', error)
      }
    },
  }
})