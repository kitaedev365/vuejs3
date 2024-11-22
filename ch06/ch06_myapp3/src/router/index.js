import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'
import Customers from '../views/CustomersComp.vue'
import About from '../views/AboutView.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/customers',
    name: 'Customers',
    component: Customers
  },
  {
    path: '/about',
    name: 'About',
    component: About
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router