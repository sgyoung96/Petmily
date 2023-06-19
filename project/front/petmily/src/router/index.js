import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
<<<<<<< HEAD
<<<<<<< HEAD
import DiaryboardHome from '@/components/dboard/DiaryboardHome.vue'
<<<<<<< HEAD
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
=======
import ApplyForm from '@/components/cdh/ApplyForm.vue'
import Api from '@/components/cdh/Api.vue'
>>>>>>> 0801ff5d1df1c063466acc099989a66b609aee22
=======
import MemJoin from '@/components/Member/MemJoin.vue'
import MemLogin from '@/components/Member/MemLogin.vue' 
import AdminHome from '@/components/Member/AdminHome.vue' 


>>>>>>> c4ba16ca132204a42ac5c26955da46858a907088

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
<<<<<<< HEAD
<<<<<<< HEAD
    path: '/volboardhome',
    name: 'VolBoardHome',
    component: VolBoardHome
  },
  {
    path: '/volboardadd',
    name: 'VolBoardAdd',
    component: VolBoardAdd
=======
    path: '/applyform',
    name: 'ApplyForm',
    component: ApplyForm
  },
  {
    path: '/api',
    name: 'Api',
    component: Api
>>>>>>> 0801ff5d1df1c063466acc099989a66b609aee22
=======
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
>>>>>>> c4ba16ca132204a42ac5c26955da46858a907088
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
