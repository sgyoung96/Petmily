<template>
  <div class="petmily">
    <!-- 공통 헤더 -->
    <div class="pet-header"> 
      
      <!-- 로고, 로그인/회원가입, 마이페이지, 알림뱃지 등 -->
      <div class="pet-box-up">
        <div class="box-member" >
          <div v-if="loginId == null">
            <span v-on:click="login" id="link-login" >로그인</span>
            <span @click="register()">회원가입</span>
          </div>
          <div v-else class="box-logined">
            <span v-on:click="logout" id="link-logout">로그아웃</span>
            <span v-if="loginId == 'admin'" @click="admin()">관리자메인</span>
          </div>
          
        </div>
      
        <div class="box-logo" @click="gotoMain()">
          <Vue3Lottie :animationData="AstronautJSON" :height="120" :width="200" :speed="0.5" />
        </div>

        <div class="box-mypage">
          <img v-if="this.notify == false" class="ico-bell" src="./assets/bell.png" />
          <img v-if="this.notify == true" class="ico-bell bell-notify" src="./assets/bell_notify.png" @click="openNotifyBox()"/>
          <img @click="goMyPage()" class="ico-mypage" src="./assets/profile-user.png" />
        </div>
      </div>

      <div class="pet-box-bottom">
        <div class="box-category">
          <ul class="petmily-category">
            <li>
              <span @click="about_petmily()">ABOUT PETMILY</span>
            </li>
            <li>
              <span @click="groups()">OUR GROUPS</span>
            </li>
            <li>
              <span @click="all_pets()" >ALL PETS</span>
            </li>
            <li>
              <span @click="all_dogs()">ALL DOGS</span>
            </li>
            <li>
              <span @click="all_cats()">ALL CATS</span>
            </li>
            <li>
              <span @click="adopt()">ADOPT PETS</span>
            </li>
            <li>
              <span @click="volunteer()">VOLUNTEER</span>
            </li>
            <li>
              <span @click="diary()">PET DIARY</span>
            </li>
            <li>
              <span @click="guide()">GUIDE</span>
            </li>
            <li>
              <span @click="contact_us()">CONTACT US</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- //공통 헤더 -->

    <NotifyBox isBedgeClicked="1" id="notify_box" v-if="isNotifyBoxOpen" @childEvent="chkIsClicked" @click="closeNotifyBox()" />

    <!-- 이곳에 라우터로 설정한 화면이 로드됨 -->
    <router-view/>
    <!-- //이곳에 라우터로 설정한 화면이 로드됨 -->

    <!-- 풋터 -->
    <footer class="footer">
      <div id="footer" class="footer_wrap clearfix">
        <div class="wrapper clearfix">
          <div class="foot_wrap" >
            <div class="foot_logo">
              <img src="@/assets/logo_petmily.png" alt="펫밀리 로그" style="width:120px; height:80px; margin-left:70px; " >
            </div>
            <div class="foot_txt" style="padding-left:110px">

            <p class="bar_span">
              <span>(주)펫밀리</span> |
              <span>대표: 전준하</span> |
              <span>(13637) 경기도 성남시 분당구 성남대로 34 6층(구미동 하나프라자빌딩)</span>
            </p>
            <p class="bar_span">
              <span>사업자등록번호 : 012-01-01234</span> |
              <span>동물판매업 : 제1234567-213-2023-000호</span>
            </p>
            <p class="copy">
              COPYRIGHT ⓒ 2023 PETMILY, ALL RIGHTS RESERVED.
            </p>
          </div>
        <div class="foot_logo" style="padding-left:100px">
        <img src="@/assets/kosta.png" alt="코스타 로그" style="width:120px; height:80px" >
			</div>
		</div>
	</div>
</div>
    </footer>
  <!--//풋터-->
  </div>
</template>

<script>
import img from "@/assets/imgs/mypage_sample.jpg";
import './assets/fonts/BagleFatOne.css';
import './assets/fonts/IBMPlexSansKR.css';
import NotifyBox from './components/notify/NotifyList.vue';
import { Vue3Lottie } from 'vue3-lottie'
import AstronautJSON from './assets/펫밀리로고.json'
export default {
  
  data () {
    return {
      loginId:null,
      isOpen: false,
      dto: {},
      cnt:0,
      cntchecktf:true,
      notifyData: [],
      notify: false,
      isNotifyBoxOpen: false,
      isClickNotifyBoxOpen: false,
      emit: '',
      isclicked: false,
      childNumList: [],
      AstronautJSON
    }
  },
  beforeMount: function() {
    this.notifyPolling();
  },
  created:function(){ // 이 컴포넌트가 시작될때 실행되는 함수
    //this.chkKakaoValidatoion();
    if (sessionStorage.getItem('loginId') != null) {
      this.loginId = sessionStorage.getItem('loginId');
    }
  },
  mounted: function() {
    //this.notifyPolling();
  },
  beforeUnmount: function() {
    clearInterval(this.notifyData);
  },
  methods:{
    chkKakaoValidatoion() {
      if (sessionStorage.getItem('loginId') != null) {
      this.loginId = sessionStorage.getItem('loginId');
      

        if (sessionStorage.getItem('loginFlag') == 'kakao') {
          const self = this;
          self.$axios.get('http://localhost:8082/members/' + this.loginId).then (function(rs) {
            console.log(rs.data.dto);
          
            if (rs.data.dto == null) {
              self.$router.push({name:'KakaoAdditionalForm', query:{kakaoId: sessionStorage.getItem('loginId'), kakaoName: sessionStorage.getItem('kakaoName')}});
            }
          });
        }
      }
    },
    async emitData() {
      const self = this;
      self.stopPolling();
      if (self.childNumList.length > 0) {
        for (let i = 0; i < self.childNumList.length; i++) {
          try {
            await self.$axios.post('http://localhost:8082/notify/read/' + self.childNumList[i]);
            console.log(i);
          } catch (error) {
            console.log(error);
          }
        }
      } 
    },
    notifyPolling() { // 알림 뱃지 실시간 확인
      this.notifyData = setInterval(() => {
        // 3초마다 한번씩 알림 테이블의 데이터 확인
        if (this.loginId != null) {
          const self = this;
          self.$axios.get('http://localhost:8082/notify/new/' + this.loginId)
          .then (function(rs) {
            self.notifyData = rs.data.list;
            if (self.notifyData.length == 0) {
              self.notify = false;
            } else {
              self.notify = true;
            }
          })
          .catch(function(e) {
            console.log(e);
          });
        }
      }, 1500);
    },
    stopPolling() {
      clearInterval(this.notifyData);
    },
    chkIsClicked(value) { // 알림박스 닫을때 이벤트
      console.log('closed');
      const self = this;
      self.childNumList = value;
      //this.emitData();
    },
    register() { // 회원가입
      this.$router.push('/join');
    },
    gotoMain() { // 로고 클릭시 메인으로 이동
      location.href = '/';
    }, 
    about_petmily() {
      this.$router.push('/aboutpetmily')
    },
    groups() { // (카테고리) 동물보호소
      this.$router.push('/groups');
    },
    all_pets() { // 모든 동물
      this.$router.push('/api');
    },
    all_dogs() { // 강아지
      this.$router.push('/apidog');
    },
    all_cats() { // 고양이
      this.$router.push('/apicat');
    },
    adopt() { // (카테고리) 분양해요
      this.$router.push('/adopt');
    },
    diary() { // (카테고리) 입양일지
      this.$router.push('/diaryboardhome');
    },
    volunteer() { // (카테고리) 봉사모집
      this.$router.push('/volboardhome')
    },
    form() { // (카테고리) 입양신청
      this.$router.push('/applyform');
    },
    guide() { // (카테고리) 입양가이드
      this.$router.push('/guide'); // TODO : 수정해야함
    },
    contact_us() { // 고객센터 ; 메일 발송 및 FAQ
      this.$router.push('/contactus');
    },
    login() { // 로그인
      if (sessionStorage.getItem('loginFlag') == 'kakao') {
        alert('소셜 로그인이 연동된 상태입니다. \n로그아웃 후 이용 바랍니다.');
        window.href = '/';
      } else {
        this.$router.push('/member');
      }
    },
   
    logout(){ // 로그아웃
      if (sessionStorage.getItem('loginFlag') != 'kakao') {
        sessionStorage.clear()
        window.location.href = "/" //리디렉션
      } else {
        /* 로그아웃 하나, 완전히 연결을 끊지 않음 */
        window.Kakao.Auth.logout((res) => {
          console.log(res)
          alert('로그아웃되었습니다.');

          /* 세션에 저장되어 있는 정보 날리기 */
          sessionStorage.clear()
          window.location.href = "/" //리디렉션
        })

        /* 사용자가 카카오 연결을 끊고자 할 때 -> 회원 탈퇴시 이 함수 사용하도록 하기 */
        // window.Kakao.API.request({
        //   url: '/v1/user/unlink'
        // }).then(function(response) {
        //   console.log(response)
        //   alert('로그아웃되었습니다.');

        //   /* 세션에 저장되어 있는 정보 날리기 */
        //   sessionStorage.clear();
        //   window.location.href = "/"; //리디렉션
        // }).catch(function(error) {
        //   console.log(error)
        // });
      }
    },
    exitService() {
      var result = confirm('회원탈퇴를 진행하시겠어요?');
      if (result) {
        if (sessionStorage.getItem('loginFlag') == 'normal') {
          this.out();
        } else { 
          this.kakaoExitService();
        }
      } else {
        alert('회원탈퇴를 취소하셨습니다.');
      }
    }, 
    kakaoExitService() { // 카카오 회원 탈퇴
      const self = this;
      
      window.Kakao.API.request({
        url: '/v1/user/unlink'
      }).then(function(response) {
        console.log(response);

        self.$axios.delete('http://localhost:8082/members/' + sessionStorage.getItem('loginId')) 
        .then(function(res){ 
          console.log(res);
          if( res.status == 200){
            if (res.data.flag) {
              /* 세션에 저장되어 있는 정보 날리기 */
              sessionStorage.clear();
              alert('회원 정보가 삭제 되었습니다.');

              window.location.href = "/"; //리디렉션
            }
          } else {
            console.log('에러코드:' + res.status);
            window.location.href = "/"; //리디렉션
          }
        });
      }).catch(function(error) {
        console.log(error);
        window.location.href = "/"; //리디렉션
      });
    },
    out(){
      const self = this;

      let token = sessionStorage.getItem('token')
      self.$axios.delete('http://localhost:8082/members/' + self.loginId, {headers:{'token':token}}) 
      .then(function(res){ 
        if(res.status == 200){
          if(res.data.flag){
            self.logout();
            sessionStorage.clear();
            alert('회원 정보가 삭제 되었습니다.');
            location.href = '/';
          }
        } else {
          console.log('에러코드:' + res.status);
          location.href = '/';
        }
      });
    },

   adminhome(){
    this.$router.push('/adminhome');
   },

    cntcheck(){
      alert('cntcheck 클릭')
      this.loginId = sessionStorage.getItem('loginId')
     const self = this;
     self.$axios.get('http://localhost:8082/message/cnt/' + self.loginId)
      .then(function(res){
        if(res.status == 200){
         self.cnt = res.data.cnt
         alert('cnt' + self.cnt)
         if(self.cnt == 0){
          self.cntchecktf = false;
         }else{
          alert(self.cnt)
          self.cntchecktf = true;
          
         }
        
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
    },
    send() {
      alert('send 클릭')
      if (this.loginId == null) {
        alert('로그인 후 이용가능');
      } else {
        this.$router.push('/messagewrite');
      }
    },
    messagesender(){
      if(this.loginId == null){
        alert('로그인 후 이용가능')
      }else{
        this.$router.push('/messagesender')
      }
    },
    messagereciever(){
      if(this.loginId == null){
        alert('로그인 후 이용가능')
      }else{
        this.$router.push('/messagereciever')
      }
    },
      replaceImg(e) {
        e.target.src = img;
    },
    admin() { // 어드민페이지
      this.$router.push('/adminhome');
    },
    goMyPage() {
      if (sessionStorage.getItem('loginId') != null) {
        this.$router.push('/member/mypage/home');
      } else {
        alert('로그인 후 이용 가능한 페이지입니다.');
        this.login();
      }

    },
    openNotifyBox() {
      this.stopPolling();
      this.isNotifyBoxOpen = true;
      clearInterval(this.notifyData);
    },
    closeNotifyBox() {
      this.isNotifyBoxOpen = false;
      this.emitData();
    },
  },
  components: {
    Vue3Lottie,
    NotifyBox
  }
}
</script>

<style>
.petmily {
  position: relative;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, BagleFatOne;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 헤더 전체 영역 */
.pet-header {
  position: sticky;
  top:0;
  background-color: white;
  z-index:3000;
  width: 100%;
  height: 150px;
  border-bottom: 3px solid rgb(244, 191, 79);
}

.pet-box-up {
  width: 100%;
  height: 120px;
  display: flex;
  justify-content: space-between;
  padding-left: 50px;
  padding-right: 50px;
}

.box-member {
  width: 300px;
  height: 120px;
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  margin-top: 40px;
}

.box-member > div span {
  margin: 10px;
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
  cursor: pointer;
}

.box-logo {
  height: 150px;
  padding-top: 5px;
  cursor: pointer;
}

.petmily-logo {
  width: 200px;
  height: auto;
  cursor: pointer;
}

.box-mypage {
  width: 300px;
  height: 110px;
  display: flex;
  justify-content: flex-end;
  padding: 10px;
  margin-top: 30px;
}

.box-mypage > img {
  margin: 10px;
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
}

.footer {
  position: relative;
  height: 200px;
  width: 100%;
  background-color: #efefef;
  z-index: -1;
}

.pet-box-bottom {
  height: 30px;
  justify-content: center;
  display: flex;
  border-top: 1px solid rgb(244, 191, 79);
  padding-top: 5px;
}

.petmily-category {
  list-style: none;
  list-style-type: none;
  height: 30px;
  justify-content: center;
}

li {
  float:left;
  margin-right: 50px;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 12px;
  cursor: pointer;
}

li:hover {
  float:left;
  margin-right: 50px;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 13px;
  cursor: pointer;
  color: rgb(244, 191, 79);
}

.ico-mypage, .ico-bell {
  width: 25px;
  height: 25px;
  cursor: pointer;
}

#notify_box {
  position: absolute;
  display: inline-block;
  z-index: 20;
  width: 800px;
  height: 500px;
  background: white;
  transform: translateX(-50%) translateY(-25%);
  border: 1px solid rgb(244, 191, 79);
  border-radius: 30px;
}

.bell-notify {
  cursor: pointer;
}

.footer_wrap {
    background-color: #c9d4d2;
    position: relative;
    width: 100%;
    font-weight: 300;
    text-align: left;
    padding: 30px 0;
    font-size: 14px;
}

.wrapper {
    width: 100%;
    max-width: 1200px;
    position: relative;
    margin: 0 auto;
}

#footer {
  position: absolute;
}

.footer_wrap {
    display: flex;
    flex-wrap: nowrap;
}

.footer_wrap .foot_wrap {
    display: flex;
    align-items: center;
}

.foot_txt .bar_span {
    margin-bottom: 5px;
}

.foot_txt .copy {
    margin-top: 5px;
}
</style>
