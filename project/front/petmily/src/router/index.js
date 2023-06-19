import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiaryboardHome from '@/components/dboard/DiaryboardHome.vue'
import MemJoin from '@/components/Member/MemJoin.vue'
import MemLogin from '@/components/Member/MemLogin.vue' 
import AdminHome from '@/components/Member/AdminHome.vue' 



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
    path: '/join',
    name: 'MemJoin',
    component: MemJoin
  },
  {
    path: '/login',
    name: 'MemLogin',
    component: MemLogin
  },
  {
    path: '/adminhome',
    name: 'AdminHome',
    component: AdminHome
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
