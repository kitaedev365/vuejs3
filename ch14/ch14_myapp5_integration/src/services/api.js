import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8088/api', 
  headers: {
    'Content-Type': 'application/json',
  },
});

// 요청 시 토큰 추가
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
});

/*
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // 인증 오류 발생 시 로그인 페이지로 리디렉션
      localStorage.removeItem('authToken');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
*/
export default apiClient;