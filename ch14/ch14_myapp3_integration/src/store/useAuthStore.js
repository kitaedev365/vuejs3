import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router'; // 라우터 직접 가져오기
export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    username: null,
    token: localStorage.getItem('token') || '',
  }),
  actions: {
    async login(credentials) {
      try {
        const response = await axios.post('http://localhost:8086/api/auth/login', credentials);
        localStorage.setItem('username', credentials.username);
        this.token = response.data.token;
        localStorage.setItem('token', this.token);
        axios.defaults.headers.Authorization = `Bearer ${this.token}`;
        router.push('/');
        await this.fetchUserProfile(credentials.username);
      } catch (error) {
        console.error('Login failed:', error);
        throw error;
      }
    },
    async register(userData) {
      try {
        await axios.post('http://localhost:8086/api/users/register', userData);
      } catch (error) {
        console.error('Registration failed:', error);
        throw error;
      }
    },
    async fetchUserProfile(username) {
      try {
        const response = await axios.get(`http://localhost:8086/api/users/${username}`);
        this.user = response.data;
        console.log("Profile: "+this.user);
      } catch (error) {
        console.error('Fetching user profile failed:', error);
        this.user = { username: 'Unknown', email: '-', role: '-', createdAt: '-' }; // 기본값 설정
      }
    },
    logout() {
      this.token = '';
      this.user = null;
      this.currentUser = null;
      localStorage.removeItem('token');
      localStorage.removeItem('username');
      axios.defaults.headers.Authorization = null;
      router.push('/');
    }
  },
  getters: {
    isAuthenticated: (state) => !!state.token
  }
});