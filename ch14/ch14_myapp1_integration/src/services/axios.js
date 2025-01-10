import axios from 'axios';

// Axios 기본 설정 (API 서버 기본 URL 설정)
axios.defaults.baseURL = 'http://localhost:8084/api/auth2';

// axios 인터셉터를 설정하여 모든 요청에 JWT 토큰을 포함시킴
axios.interceptors.request.use(
  async (config) => {
    let token = localStorage.getItem('token'); // localStorage에서 토큰 가져오기

    // 토큰이 없을 경우, 로그인 요청을 통해 새 토큰을 받아오기
    if (!token) {
      try {
        const response = await axios.post('/login', {
          username: 'your-username', // 필요 시 실제 사용자 입력을 동적으로 전달
          password: 'your-password', // 필요 시 실제 사용자 입력을 동적으로 전달
        });

        token = response.data.token; // 서버에서 받은 토큰
        localStorage.setItem('token', token); // 로컬스토리지에 저장
      } catch (error) {
        console.error('Failed to retrieve token:', error);
        return Promise.reject(error);
      }
    }

    // Authorization 헤더에 Bearer 토큰 추가
    config.headers['Authorization'] = `Bearer ${token}`;

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axios;