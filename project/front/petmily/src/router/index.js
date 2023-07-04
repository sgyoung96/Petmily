import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MemberHome from '../views/MemberView.vue'
import KakaoAdditionalForm from '@/components/Member/AdditionalForm.vue'
import MyPageHome from '../views/MypageView.vue'
import AboutPetmily from '../views/AboutPetmily.vue'
import Groups from '../views/Groups.vue'
import Guide from '../views/Guide.vue'
import DiaryBoardHome from '@/components/diaryboard/DiaryBoardHome.vue'
import AdoptBoardHome from '@/components/adopt/AdoptList.vue'
import AdoptForm from '@/components/adopt/AdoptForm.vue'
import AdoptDetail from '@/components/adopt/AdoptDetail.vue'
import DiaryBoardAdd from '@/components/diaryboard/DiaryBoardAdd.vue'
import DiaryBoardDetail from '@/components/diaryboard/DiaryBoardDetail.vue'
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
import BarChart from '@/components/kaka/BarChart.vue'
import PieChart from '@/components/kaka/PieChart.vue'
import MemEdit from '@/components/Member/MemEdit.vue'
import FindPwd from '@/components/Member/FindPwd.vue'
import FindId from '@/components/Member/FindId.vue'


import AddressConvert from '@/components/kaka/AddressConvert.vue' 
import MessageWrite from '@/components/Message/MessageWrite.vue' 
import MessageSender from '@/components/Message/MessageSender.vue' 
import MessageReciever from '@/components/Message/MessageReciever.vue'
import NotifyList from '@/components/notify/NotifyList.vue'
import ApiCat from '@/components/cdh/ApiCat.vue'
import ApiDog from '@/components/cdh/ApiDog.vue'
import AdminAppliedList from '@/components/admin/ApplyList.vue'
import ApplyDetail from '@/components/admin/ApplyDetail.vue'
import VolList from '@/components/admin/VolList.vue'
import AddressMap from '@/components/kaka/AddressMap.vue'
import Weather from '@/components/kaka/Weather.vue'

import AnimalHospital from '@/components/kaka/AnimalHospital.vue'
import MyPageMain from '@/components/Member/MyPage.vue'

import MyInfo from '@/components/mypage/MyInfo.vue'
import GoodList from '@/components/mypage/GoodList.vue'
import SavedList from '@/components/mypage/SavedList.vue'
import AppliedList from '@/components/mypage/AppliedList.vue'
import StateList from '@/components/mypage/StateList.vue'
import MessageBox from '@/components/mypage/MessageBox.vue'
import ExitService from '@/components/mypage/ExitService.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  { 
    path: '/aboutpetmily',
    name: 'AboutPetmily',
    component: AboutPetmily
  },
  { 
    path: '/groups',
    name: 'Groups',
    component: Groups
  },
  {
    path: '/guide',
    name: 'Guide',
    component: Guide
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
    path: '/animalhospital/:name/:addr',
    name: 'AnimalHospital',
    component: AnimalHospital
  },
  {
    path: '/member/kakaoform',
    name: 'KakaoAdditionalForm',
    component: KakaoAdditionalForm
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
    path: '/kaka',
    name: 'Kaka',
    component: Kaka
  },
  {
    path: '/weather',
    name: 'Weather',
    component: Weather
  },
  {
    path: '/addressmap',
    name: 'AddressMap',
    component: AddressMap
  },
  {
    path: '/barchart',
    name: 'BarChart',
    component: BarChart
  },
  {
    path: '/piechart',
    name: 'PieChart',
    component: PieChart
  },
  {
    path:'/adopt',
    name: 'AdoptBoardHome',
    component: AdoptBoardHome
  },
  {
    path:'/adoptdetail',
    name: 'AdoptDetail',
    component: AdoptDetail
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
    path: '/detail',
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
  },
  {
    path: '/messagewrite',
    name: 'MessageWrite',
    component: MessageWrite
  },
  {
    path: '/messagesender',
    name: 'MessageSender',
    component: MessageSender
  },
  {
    path: '/messagereciever',
    name: 'MessageReciever',
    component: MessageReciever
  },
  {
    path: '/memedit',
    name: 'MemEdit',
    component: MemEdit
  },
  {
    path: '/findpwd',
    name: 'FindPwd',
    component: FindPwd
  },
  {
    path: '/findid',
    name: 'FindId',
    component: FindId
  },
  {
    path: '/notify',
    name: 'NotifyList',
    component: NotifyList
  },
  {
    path: '/apicat',
    name: 'ApiCat',
    component: ApiCat
  },
  {
    path: '/apidog',
    name: 'ApiDog',
    component: ApiDog
  },
  {
    path: '/admin/ApplyList',
    name: 'AdminAppliedList',
    component: AdminAppliedList
  },
  {
    path: '/admin/ApplyDetail',
    name: 'ApplyDetail',
    component: ApplyDetail
  },
  {
    path: '/admin/VolList',
    name: 'VolList',
    component: VolList
  },
  {
    path: '/member/mypage/home',
    name: 'MyPageMain',
    component: MyPageMain
  },
  {
    path: '/mypage/info',
    name: 'MyInfo',
    component: MyInfo
  }, 
  {
    path: '/mypage/good',
    name: 'GoodList',
    component: GoodList
  },
  {
    path: '/mypage/saved',
    name: 'SavedList',
    component: SavedList
  },
  {
    path: '/mypage/apply',
    name: 'AppliedList',
    component: AppliedList
  },
  {
    path: '/mypage/state',
    name: 'StateList',
    component: StateList
  }, 
  {
    path: '/mypage/message',
    name: 'MessageBox',
    component: MessageBox
  }, 
  {
    path: '/mypage/exit',
    name: 'ExitService',
    component: ExitService
  } 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
