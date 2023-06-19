import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiaryboardHome from '@/components/dboard/DiaryboardHome.vue'
import VolBoardAdd from '@/components/volboard/VolBoardAdd.vue'
import VolBoardHome from '@/components/volboard/VolBoardHome.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/diaryboardhome',
    name: 'DiaryboardHome',
    component: DiaryboardHome
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/volboardhome',
    name: 'VolBoardHome',
    component: VolBoardHome
  },
  {
    path: '/volboardadd',
    name: 'VolBoardAdd',
    component: VolBoardAdd
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
