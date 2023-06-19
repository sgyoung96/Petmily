import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiaryBoardHome from '@/components/diaryboard/DiaryBoardHome.vue'
import DiaryBoardAdd from '@/components/diaryboard/DiaryBoardAdd.vue'
import DiaryBoardDetail from '@/components/diaryboard/DiaryBoardDetail.vue'
import KakaomapHome from '@/components/kakaomap/KakaomapHome.vue'

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
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
