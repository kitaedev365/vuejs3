import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import CurrentView from '../views/CurrentView.vue';
const routes = [   // 라우트 정의
  { path: '/', name: 'home', component: HomeView, },
  { path: '/about', name: 'about', component: AboutView, },
  { path: '/current', name: 'current', component: CurrentView, },
];
const router = createRouter({   // 라우터 객체 생성
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;