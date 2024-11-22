import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue'; // AboutView 컴포넌트 임포트
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView, // 홈 경로
  },
  {
    path: '/about',
    name: 'about', // 명명된 라우트로 정의
    component: AboutView, // About 페이지 경로
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;