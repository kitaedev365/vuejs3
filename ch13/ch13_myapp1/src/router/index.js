import { createRouter, createWebHistory } from 'vue-router';
import BoardList from '../components/BoardList.vue';
const routes = [
  { path: '/', name: 'BoardList', component: BoardList },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;