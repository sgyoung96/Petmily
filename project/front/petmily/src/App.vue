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
          <div v-else>
            <span v-on:click="logout" id="link-logout">로그아웃</span>
          </div>
          
        </div>
        <div class="box-logo">
          <img @click="gotoMain()" class="petmily-logo" src="./assets/logo_petmily.png" />
        </div>
        <div class="box-mypage">
          <img class="ico-bell" src="./assets/bell.png" />
          <img class="ico-mypage" src="./assets/profile-user.png" />
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
              <span @clicl="all_cats()">ALL CATS</span>
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

    <br>

    

    <!-- 이곳에 라우터로 설정한 화면이 로드됨 -->
    <router-view/>
    <!-- //이곳에 라우터로 설정한 화면이 로드됨 -->

    <!-- 풋터 -->
    <footer class="footer">
      <div>
        <p>이곳에 풋터 디자인</p>
        <!-- 기존 링크 모음 (테스트용, 추후 삭제 예정) -->
        <router-link to="/kaka">카카</router-link> |
        <router-link to="/addressconvert">주소변환</router-link> |
        <router-link to="/addressmap">주소로 지도</router-link> |
        <router-link to="/barchart">막대그래프</router-link> |
        <router-link to="/piechart">파이차트</router-link> |
        <router-link to="/applyform">Apply</router-link> |
        <router-link to="/api">Api</router-link> |
        <div v-if="loginId != null">
          <router-link to="/messagewrite" >쪽지보내기</router-link> |
          <router-link to="/messagesender" >보낸쪽지함</router-link> |
          <router-link to="/messagereciever">쪽지함</router-link>
          <span v-show="cntcheck" @click="cntcheck">{{cnt}}</span> 
          <router-link to="/memedit">내정보 수정</router-link>
        </div>
        |
        <router-link to="/notify">알림함페이지이동</router-link> |
        <span @click="exitService()" style="cursor: pointer;">회원탈퇴</span>
        <!-- //기존 링크 모음 (테스트용, 추후 삭제 예정) -->`
        <p>Copyright by Petmily, ... etc</p>
      </div>
    </footer>
  <!--//풋터-->
  </div>
</template>

<script>
import img from "@/assets/imgs/mypage_sample.jpg";
import './assets/fonts/BagleFatOne.css';
import './assets/fonts/IBMPlexSansKR.css';

export default {
  
  data () {
    return {
      loginId:null,
      isOpen: false,
      dto: {},
      cnt:0,
      notifyData: null
    }
  },
  created:function(){ // 이 컴포넌트가 시작될때 실행되는 함수
    if (sessionStorage.getItem('loginId') != null) {
      this.loginId = sessionStorage.getItem('loginId');

      if (this.loginId != null) {
        if (this.loginId == 'admin') {
          this.$router.push('/adminhome')
        } else if (sessionStorage.getItem('loginFlag') == 'kakao') {
          const self = this;

          self.$axios.get('http://localhost:8082/members/' + this.loginId).then (function(rs) {
            console.log(rs.data.dto);
          
            if (rs.data.dto == null) {
              self.$router.push({name:'KakaoAdditionalForm', query:{kakaoId: sessionStorage.getItem('loginId'), kakaoName: sessionStorage.getItem('kakaoName')}});
            }
          });
        }
      }
    }
  },
  mounted: function() {
    //this.notifyPolling();
  },
  beforeUnmount: function() {
    clearInterval(this.notifyData);
  },
  methods:{
    notifyPolling() { // 알림 뱃지 실시간 확인
      this.notifyData = setInterval(() => {
        // 3초마다 한번씩 알림 테이블의 데이터 확인
        if (this.loginId != null) {
          const self = this;
          self.$axios.get('http://localhost:8082/notify/' + this.loginId)
          .then (function(rs) {
            console.log(rs);
          });
        }
      }, 3000);
    },
    register() { // 회원가입
      this.$router.push('/join');
    },
    gotoMain() { // 로고 클릭시 메인으로 이동
      location.href = '/';
    }, 
    about_petmily() {
      alert('페이지 추가하고 작업해야함');
    },
    groups() { // (카테고리) 동물보호소
      alert('경로 추가 및 페이지 작업 필요');
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
      this.$router.push('/'); // TODO : 수정해야함
      alert('경로 추가 및 페이지 작업 필요');
    },
    contact_us() { // 고객센터 ; 메일 발송 및 FAQ
      alert('경로 추가 및 페이지 작업 필요');
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
          this.cntcheck = false;
         }else{
          alert(self.cnt)
          this.cntcheck = true;
          this.cnt = self.cnt;
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
  position: relative;
  width: 100%;
  height: 200px;
  border-bottom: 3px solid rgb(244, 191, 79);
}

.pet-box-up {
  width: 100%;
  height: 170px;
  display: flex;
  justify-content: space-between;
  padding-left: 50px;
  padding-right: 50px;
}

.box-member {
  width: 300px;
  height: 170px;
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  margin-top: 50px;
}

.box-member > div span {
  margin: 10px;
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
  cursor: pointer;
}

.box-logo {
  height: 150px;
  padding-top: 25px;
}

.petmily-logo {
  width: 200px;
  height: auto;
  cursor: pointer;
}

.box-mypage {
  width: 300px;
  height: 170px;
  display: flex;
  justify-content: flex-end;
  padding: 10px;
  margin-top: 50px;
}

.box-mypage > img {
  margin: 10px;
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
}

.footer {
  position: absolute;
  height: 200px;
  width: 100%;
  background-color: #efefef;
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

</style>
