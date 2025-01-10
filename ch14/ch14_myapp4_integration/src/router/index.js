import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '../store/authStore';  // Pinia 스토어 가져오기
import HomePage from '../pages/HomePage.vue';
import LoginPage from '../pages/LoginPage.vue';
import RegisterPage from '../pages/RegisterPage.vue';
import PostDetail from '../components/PostDetail.vue';
import PostForm from '../components/PostForm.vue';
import UserProfile from '../pages/UserProfile.vue';
import EditPost from '../pages/EditPost.vue';
import NotFound from '../pages/NotFound.vue';
import ChatPage from '../pages/ChatPage.vue';
import PostList from '../components/PostList.vue';
import MessageList from '../pages/MessageList.vue';
import SendMessage from '../pages/SendMessage.vue';
import ReceiveMessage from '../pages/ReceiveMessage.vue';
import MessageForm from '../pages/MessageForm.vue';

const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage, meta: { requiresAuth: false } },
  { path: '/register', name: 'Register', component: RegisterPage, meta: { requiresAuth: false } },
  { path: '/posts', name: 'PostList', component: PostList, props: true },
  { path: '/post/:id', name: 'PostDetail', component: PostDetail, props: true },
  { path: '/post/new', name: 'PostForm', component: PostForm },
  { path: '/profile', name: 'UserProfile', component: UserProfile, meta: { requiresAuth: true } },
  { path: '/post/edit/:id', name: 'EditPost', component: EditPost, props: true, meta: { requiresAuth: true } },
  { path: '/chat', name: 'ChatPage', component: ChatPage, meta: { requiresAuth: true } },
  { path: '/:catchAll(.*)', name: 'NotFound', component: NotFound },
  { path: '/messages', name: 'MessageList', component: MessageList },
  { path: '/messages/send', name: 'SendMessage', component: SendMessage },
  { path: '/messages/receive', name: 'ReceiveMessage', component: ReceiveMessage },
  { path: '/messages/new', name: 'MessageForm', component: MessageForm },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();  // Pinia 스토어 사용
  const isAuthenticated = authStore.isAuthenticated;  // 인증 상태 확인

  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'Login' });  // 로그인 페이지로 리디렉션
  } else {
    next();  // 인증이 필요 없는 페이지는 그대로 진행
  }
});

export default router;