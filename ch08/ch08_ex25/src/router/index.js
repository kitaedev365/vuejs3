import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import DashboardView from '../views/DashboardView.vue';
const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/dashboard', name: 'dashboard', component: DashboardView, meta: { requiresAuth: true } }, 
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
// 네비게이션 가드
router.beforeEach((to, from, next) => {
  const isAuthenticated = false; // 실제 인증 로직으로 변경 필요
  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'login' }); // 로그인 페이지로 리디렉션
  } else {
    next();
  }
});
export default router;