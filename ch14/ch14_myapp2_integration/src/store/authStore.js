import { defineStore } from 'pinia'
import axios from '../plugins/axios' // Axios 설정 파일

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isAuthenticated: false,
    user: null,
  }),
  actions: {
    async register(userData) {
      try {
        // 회원가입 API 호출
        const response = await axios.post('/users/register', userData)
        console.log('Registration successful:', response.data)
      } catch (error) {
        console.error('Registration failed:', error)
        throw error
      }
    },
    async login(userData) {
      try {
        // 로그인 API 호출
        const response = await axios.post('/auth/login', userData)
        this.isAuthenticated = true
        this.user = response.data
        console.log('Login successful:', response.data)
      } catch (error) {
        console.error('Login failed:', error)
        throw error
      }
    },
    logout() {
      this.isAuthenticated = false
      this.user = null
    },
  },
})