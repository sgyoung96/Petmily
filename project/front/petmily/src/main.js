import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
<<<<<<< HEAD
import axios from 'axios'


//createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) //vue 객체 생성
app.use(store) //전역 저장 라이브러리 사용
app.use(router)//라우터 사용설정. url별로 실행될 component등록
=======
import axios from "axios";

// createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) //vue 객체 생성
app.use(store) //전역 저장 라이브러리 사용
app.use(router) //라우터 사용설정. url별로 실행될 component등록
>>>>>>> develop
app.config.globalProperties.$axios = axios; //비동기요청객체
app.mount('#app')