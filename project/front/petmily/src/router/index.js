import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
<<<<<<< HEAD
import DiaryboardHome from '@/components/dboard/DiaryboardHome.vue'
<<<<<<< HEAD
import AdoptBoardHome from '../views/adopt/AdoptBoardView.vue'
import AdoptForm from '@/components/adopt/AdoptForm.vue'
=======
import DiaryBoardHome from '@/components/diaryboard/DiaryBoardHome.vue'
import DiaryBoardAdd from '@/components/diaryboard/DiaryBoardAdd.vue'
import DiaryBoardDetail from '@/components/diaryboard/DiaryBoardDetail.vue'
import KakaomapHome from '@/components/kakaomap/KakaomapHome.vue'
>>>>>>> develop
=======
import VolBoardAdd from '@/components/volboard/VolBoardAdd.vue'
import VolBoardHome from '@/components/volboard/VolBoardHome.vue'
>>>>>>> 332eb10dff6d05428f38826a8ae74405d34a0118

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/diaryboardhome',
    name: 'DiaryBoardHome',
    component: DiaryBoardHome
  },
  {
    path: '/diaryboardadd',
    name: 'DiaryBoardAdd',
    component: DiaryBoardAdd
  },
  {
    path: '/diaryboarddetail',
    name: 'DiaryBoardDetail',
    component: DiaryBoardDetail
  },
  {
    path: '/kakaomaphome',
    name: 'KakaomapHome',
    component: KakaomapHome
  },
  {
    path:'/adopt',
    name: 'AdoptBoardHome',
    component: AdoptBoardHome
  },
  {
    path:'/adopt/addform',
    name: 'AdoptForm',
    component: AdoptForm
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
