import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import SidebarComp from '../components/SidebarComp.vue';
const routes = [
  {
    path: '/',
    components: {
      default: HomeView,
      sidebar: SidebarComp, // 명명된 뷰
    },
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;