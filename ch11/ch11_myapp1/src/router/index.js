import { createRouter, createWebHistory } from 'vue-router'
import MainComp from '../pages/MainComp.vue'
import TaskList from '../pages/TaskList.vue'
import TaskDetail from '../pages/TaskDetail.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: MainComp
  },{
    path: '/tasks',
    name: 'TaskList',
    component: TaskList
  },{
    path: '/tasks/:id',
    name: 'TaskDetail',
    component: TaskDetail,
    props: true
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router