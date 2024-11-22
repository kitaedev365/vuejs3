import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import CommunityView from '../views/CommunityView.vue';
import DashboardView from '../views/DashboardView.vue';
import UserProfile from '../views/UserProfile.vue';
import NotFoundView from '../views/NotFoundView.vue';
const routes = [
  { path: '/', name: 'home', component: HomeView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/community', name: 'community', component: CommunityView },
  { path: '/dashboard', name: 'dashboard', component: DashboardView, meta: { requiresAuth: true } },
  { path: '/user/:id', name: 'user', component: UserProfile, props: true },
  { path: '/:pathMatch(.*)*', name: 'not-found', component: NotFoundView },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});
// 네비게이션 가드
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.meta.requiresAuth && !token) {
    next({ name: 'login' });
  } else {
    next();
  }
});
export default router;