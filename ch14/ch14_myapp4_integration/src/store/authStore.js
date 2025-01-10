import { defineStore } from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('auth_token') || null, // 로컬스토리지에서 토큰 불러오기
    user: {}, // 사용자 정보
    username: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.token, // 인증 여부 체크
    getUser: (state) => state.user, // 현재 로그인된 사용자 정보
  },
  actions: {
    async login(credentials) {
      try {
        const response = await fetch('http://localhost:8087/api/auth/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(credentials),
        });

        this.username = credentials.username;
        const res = await axios.get(`http://localhost:8087/api/users/${this.username}`);
        this.user = res.data;
        localStorage.setItem("username", this.username);
        localStorage.setItem("user", this.user);
        const data = await response.json();
        if (data.token) {
          this.token = data.token; // 토큰 저장
          localStorage.setItem('auth_token', data.token); // 로컬스토리지에 저장
        }
      } catch (error) {
        console.error('Login failed:', error);
      }
    },
    logout() {
      this.token = null;
      this.user = null;
      localStorage.removeItem('auth_token'); // 로컬스토리지에서 토큰 삭제
      localStorage.removeItem('username');
      localStorage.removeItem('user');
    },
    // 새로고침 시 로컬스토리지에 저장된 토큰으로 상태를 복원
    restoreToken() {
      const savedToken = localStorage.getItem('auth_token');
      const savedUsername = localStorage.getItem("username");
      const savedUser = localStorage.getItem("user");
      if (savedToken) {
        this.token = savedToken;  // 로컬스토리지에서 토큰 복원
        this.username = savedUsername;
        this.user = savedUser;
      }
    },
  },
});