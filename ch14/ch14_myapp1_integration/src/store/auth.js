import { defineStore } from 'pinia'
import AuthService from '../services/AuthService'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,               // 사용자 정보
    isAuthenticated: false,   // 로그인 여부
    username: null,           // 사용자 이름
  }),
  actions: {
    async login(username, password) {
      try {
        await AuthService.login(username, password)  // 사용자 정보 받아오기
        const user = await this.fetchUserDetails(username)  // 사용자 정보 불러오기
        this.user = user
        this.username = username
        localStorage.setItem('user', JSON.stringify(user))  // user를 JSON 문자열로 저장
        localStorage.setItem('username', username)
        this.isAuthenticated = true
      } catch (error) {
        console.error('Failed to login', error)
        throw error
      }
    },
    // 사용자 정보를 가져오는 메소드
    async fetchUserDetails(username) {
      try {
        const response = await fetch(`http://localhost:8084/api/users/${username}`, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('authToken')}`  // 토큰을 Authorization 헤더에 포함시킬 수 있음
          }
        })
        if (!response.ok) {
          throw new Error('Failed to fetch user details')
        }
        const userDetails = await response.json()
        return userDetails  // 사용자 정보를 반환
      } catch (error) {
        console.error('Error fetching user details:', error)
        throw error  // 사용자 정보를 불러오는 데 실패했을 경우 에러 처리
      }
    },
    logout() {
      this.user = null
      this.username = null
      this.isAuthenticated = false
      localStorage.removeItem('username')
      localStorage.removeItem('user')
      AuthService.logout()
    },
    // 앱 초기화 시 localStorage에서 상태를 읽어서 설정
    initialize() {
      const storedUser = localStorage.getItem('user')
      const storedUsername = localStorage.getItem('username')
      
      if (storedUser && storedUsername) {
        this.user = JSON.parse(storedUser)  // JSON 문자열을 객체로 변환
        this.username = storedUsername
        this.isAuthenticated = true
      }
    },
  },
})