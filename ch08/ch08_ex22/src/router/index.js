import { createRouter, createWebHistory } from 'vue-router';
import DashboardView from '../views/DashboardView.vue';
import ProfileView from '../views/ProfileView.vue';
import SettingsView from '../views/SettingsView.vue';
const routes = [  //중첩 라우트 추가
  {
    path: '/dashboard',
    component: DashboardView,
    children: [
      {
        path: 'profile', // 부모 경로인 /dashboard의 하위 경로
        component: ProfileView,
      },
      {
        path: 'settings', // 부모 경로인 /dashboard의 하위 경로
        component: SettingsView,
      },
    ],
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;