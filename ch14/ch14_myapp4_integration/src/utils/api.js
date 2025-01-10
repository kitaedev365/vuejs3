import axios from 'axios';
const api = axios.create({  // Axios 인스턴스 생성
  baseURL: 'http://localhost:8087/api/', // API 서버 URL
  headers: {
    'Content-Type': 'application/json',
  },
});
api.interceptors.request.use(  // 요청 인터셉터 설정 (토큰 추가)
  (config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);
api.interceptors.response.use(  // 응답 인터셉터 설정 (에러 처리)
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // 인증 실패시 토큰 삭제 및 로그인 페이지로 리다이렉트
      localStorage.removeItem('authToken');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
export default api;