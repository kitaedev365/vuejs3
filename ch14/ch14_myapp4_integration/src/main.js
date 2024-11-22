import { createApp } from 'vue';  // Vue 3 생성 함수
import App from './App.vue';      // 루트 컴포넌트
import router from './router'; 
import { createPinia } from 'pinia';  // Pinia 상태 관리
import './styles/base.css';  // 기본 스타일
import './styles/foundation.css';  // Foundation CSS
import './styles/variables.css';  // 스타일 변수
import api from './utils/api';  // API 인스턴스
import { saveAuthToken, removeAuthToken } from './utils/auth';  // 인증 관련 유틸리티

// Pinia 상태 관리 설정
const pinia = createPinia();

// Vue 애플리케이션 생성
const app = createApp(App);

// Vue Router 및 Pinia 사용 설정
app.use(router);
app.use(pinia);

// 글로벌 API 및 인증 유틸리티 주입
app.config.globalProperties.$api = api;
app.config.globalProperties.$auth = {
  saveAuthToken,
  removeAuthToken,
};

// 애플리케이션 마운트
app.mount('#app');