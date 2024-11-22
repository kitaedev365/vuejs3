import { createRouter, createWebHistory } from 'vue-router';

// 컴포넌트 지연 로딩
const HomeView = () => import('../views/HomeView.vue');
const AboutView = () => import('../views/AboutView.vue');
const NotFoundView = () => import('../views/NotFoundView.vue');
const UserProfile = () => import('../views/UserProfile.vue');

// 라우트 설정
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView, // 홈 페이지
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView, // About 페이지
  },
  {
    path: '/user/:id',
    name: 'user',
    component: UserProfile,
    props: true, // 라우트 파라미터를 props로 전달
  },
  {
    path: '/:pathMatch(.*)*', // 모든 잘못된 경로를 처리
    name: 'not-found',
    component: NotFoundView, // 404 페이지
  },
];

// 라우터 생성
const router = createRouter({
  history: createWebHistory(), // 히스토리 모드 활성화
  routes,
});

export default router;