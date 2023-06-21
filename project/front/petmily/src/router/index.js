import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MemberHome from '../views/MemberView.vue'
import MyPageHome from '../views/MypageView.vue'
import DiaryBoardHome from '@/components/diaryboard/DiaryBoardHome.vue'
import AdoptBoardHome from '../views/adopt/AdoptBoardView.vue'
import AdoptForm from '@/components/adopt/AdoptForm.vue'
import DiaryBoardAdd from '@/components/diaryboard/DiaryBoardAdd.vue'
import DiaryBoardDetail from '@/components/diaryboard/DiaryBoardDetail.vue'
import DiaryBoardEdit from '@/components/diaryboard/DiaryBoardEdit.vue'
import Kaka from '@/components/kaka/KakaoMapHome.vue'
import VolBoardAdd from '@/components/volboard/VolBoardAdd.vue'
import VolBoardHome from '@/components/volboard/VolBoardHome.vue'
import VolBoardDetail from '@/components/volboard/VolBoardDetail.vue'
import ApplyForm from '@/components/cdh/ApplyForm.vue'
import Detail from '@/components/cdh/Detail.vue'
import Api from '@/components/cdh/Api.vue'
import MemJoin from '@/components/Member/MemJoin.vue'
import MemLogin from '@/components/Member/MemLogin.vue' 
import AdminHome from '@/components/Member/AdminHome.vue'
import AddressConvert from '@/components/kaka/AddressConvert.vue' 

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  { 
    path: '/member',
    name: 'MemberHome',
    component: MemberHome
  },
  {
    path: '/mypage',
    name: 'MyPageHome',
    component: MyPageHome
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
    path: '/diaryboardedit',
    name: 'DiaryBoardEdit',
    component: DiaryBoardEdit
  },
  {
    path: '/kaka',
    name: 'Kaka',
    component: Kaka
  },
  {
    path:'/adopt',
    name: 'AdoptBoardHome',
    component: AdoptBoardHome
  },
  {
    path:'/addressconvert',
    name: 'AddressConvert',
    component: AddressConvert
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
  },
  {
    path: '/volboarddetail',
    name: 'VolBoardDetail',
    component: VolBoardDetail
  }
  ,
  {
    path: '/applyform',
    name: 'ApplyForm',
    component: ApplyForm
  },
  {
    path: '/detail/:desertionNo',
    name: 'Detail',
    component: Detail
  },
  {
    path: '/api',
    name: 'Api',
    component: Api
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
