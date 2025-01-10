import apiClient from './api';

const authService = {
  async login(credentials) {
    try {
      const response = await apiClient.post('/auth/login', credentials); // 로그인 요청
      const token = response.data; 
      return token;
    } catch (error) {
      throw error; // 에러 발생 시 상위 호출자에게 전달
    }
  },
  async getUser(credentials){
    try {
      const username = credentials.username;
      const userResponse = await apiClient.get(`/users/${username}`); // 사용자 정보 가져오기
      return userResponse.data;
    } catch (error) {
      throw error; // 에러 발생 시 상위 호출자에게 전달
    }
  },
};

export default authService;