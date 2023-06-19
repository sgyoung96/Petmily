<<<<<<< HEAD
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import axios from 'axios'


=======
import axios from "axios"

>>>>>>> 332eb10dff6d05428f38826a8ae74405d34a0118
//createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) //vue 객체 생성
app.use(store) //전역 저장 라이브러리 사용
app.use(router)//라우터 사용설정. url별로 실행될 component등록
<<<<<<< HEAD
=======
import axios from "axios";

// createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) //vue 객체 생성
app.use(store) //전역 저장 라이브러리 사용
app.use(router) //라우터 사용설정. url별로 실행될 component등록
>>>>>>> develop
=======
>>>>>>> 332eb10dff6d05428f38826a8ae74405d34a0118
app.config.globalProperties.$axios = axios; //비동기요청객체
app.mount('#app')
=======
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';

const app = createApp(App);
app.config.productionTip = false;
app.config.globalProperties.$http = axios;

app.use(store).use(router).mount('#app');
>>>>>>> 0801ff5d1df1c063466acc099989a66b609aee22
=======
import axios from "axios";
import VueMoment from 'moment'



//createApp(App).use(store).use(router).mount('#app')
const app = createApp(App) // vue 객체 생성
app.use(store) // 전역 저장 라이브러리 사용
app.use(router) // 라우터 사용설정. url별로 실행 될 component등록
app.config.globalProperties.$axios = axios; // 비동기 요청 객체
app.mount('#app')
app.use(VueMoment)
>>>>>>> c4ba16ca132204a42ac5c26955da46858a907088
