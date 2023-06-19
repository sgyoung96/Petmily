import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
import VueMoment from 'moment'



//createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) // vue 객체 생성
app.use(store) // 전역 저장 라이브러리 사용
app.use(router) // 라우터 사용설정. url별로 실행 될 component등록
app.config.globalProperties.$axios = axios; // 비동기 요청 객체
app.mount('#app')
app.use(VueMoment)
