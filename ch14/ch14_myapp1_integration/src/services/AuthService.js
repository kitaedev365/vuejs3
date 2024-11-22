import axios from 'axios'

const API_URL = 'http://localhost:8084/api/auth/'  // 백엔드 API URL
const API_URL2 = 'http://localhost:8084/api/users/'  // 백엔드 API URL2

export default {
  async login(username, password) {
    try {
      // 로그인 요청 시 JWT 토큰을 포함한 응답을 받음
      const response = await axios.post(API_URL + 'login', { username, password })
      
      // 서버에서 반환한 JWT 토큰을 localStorage에 저장
      localStorage.setItem('user', JSON.stringify(response.data))  // 토큰을 localStorage에 저장

      return response.data  // 토큰을 반환
    } catch (error) {
      console.error('Failed to login', error)
      throw error
    }
  },
  
  logout() {
    // 로그아웃 시 localStorage에서 사용자 정보 및 토큰 삭제
    localStorage.removeItem('user')
  },

  async register(user) {
    const response = await axios.post(API_URL2 + 'register', user)
    return response.data
  }
}