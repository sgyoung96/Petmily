<template>
    <!-- 공통 헤더 -->
    <div class="pet-header"> 
      <div class="box-left">
        <img class="logo" @click="gotoMain" src="./assets/logo_sample.png" >
        
        <!-- 반응형 600px 이하 가로사이즈일 경우 -->
        <div class="min-box-category">
          <ul class="pet-menu">
            <li>
              <img class="icon-menu" src='./assets/list.png'>
              <ul class="min-box-list">
                <li>
                  <div>
                    <span @click="gotoMain" >메인</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="guard()" >동물보호소</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="adopt()" >분양해요</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="diary()" >입양일지</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="volunteer()" >봉사모집</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="form()" >입양신청</span>
                  </div>
                </li>
                <li>
                  <div>
                    <span @click="guide()" >입양가이드</span>
                  </div>
                </li>
              </ul> 
            </li>
          </ul>
        </div>
      </div>
      
      <div class="box-category">
        <div class="category">
          <ul class="category-list">
            <li>
              <div>
                <span @click="guard()" >동물보호소</span>
              </div>
            </li>
            <li>
              <div>
                <span @click="adopt()" >분양해요</span>
              </div>
            </li>
            <li>
              <div>
                <span @click="diary()" >입양일지</span>
              </div>
            </li>
            <li>
              <div>
                <span @click="volunteer()" >봉사모집</span>
              </div>
            </li>
            <li>
              <div>
                <span @click="form()" >입양신청</span>
              </div>
            </li>
            <li>
              <div>
                <span @click="guide()" >입양가이드</span>
              </div>
            </li>
          </ul>
        </div>
      </div>

      <div class="box-right">
        <div class="member-manage">
          <span style="display: none;">로그인 연장 00:00</span> <!-- 추가기능 -->
          <div v-if="loginId == null">
            <span v-on:click="login" id="link-login" >로그인</span>
          </div>
          <div v-else>
            <span v-on:click="logout" id="link-logout">로그아웃</span>
          </div>
        </div>
        <div class="box-image">
          <img class="mypage" src="./assets/mypage_sample.jpg" >
          <img class="pet-alert" src="./assets/alert_sample.jpg" >
        </div>
      </div>
    </div>
    <!-- //공통 헤더 -->
    
    <br>

    <!-- 기존 링크 모음 (테스트용, 추후 삭제 예정) -->
    <router-link to="/kaka">카카</router-link> |
    <router-link to="/addressconvert">주소변환</router-link> |
    <router-link to="/addressmap">주소로 지도</router-link> |
    <router-link to="/applyform">Apply</router-link> |
    <router-link to="/api">Api</router-link> |
    <router-link to="" @click="send">쪽지보내기</router-link> |
    <router-link to="" @click="messagesender">보낸쪽지함</router-link> |
    <router-link to="" @click="messagereciever">받은쪽지함</router-link> |
    <span @click="exitService()" style="cursor: pointer;">회원탈퇴</span>
    <!-- //기존 링크 모음 (테스트용, 추후 삭제 예정) -->

  <!-- 이곳에 라우터로 설정한 화면이 로드됨 -->
  <router-view/>
  <!-- //이곳에 라우터로 설정한 화면이 로드됨 -->

  <!-- 풋터 -->
  <footer class="footer">
    <div>
      <p>이곳에 풋터 디자인</p>
      <p>Copyright by Petmily, ... etc</p>
    </div>
  </footer>
  <!--//풋터-->
</template>

<script>
import './assets/fonts/BagleFatOne.css';
import './assets/fonts/IBMPlexSansKR.css';

export default {
  
  data () {
    return {
      loginId:null,
      isOpen: false,
      dto: {}
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
  methods:{
    gotoMain() { // 로고 클릭시 메인으로 이동
      location.href = '/';
    }, 
    guard() { // (카테고리) 동물보호소
      this.$router.push('/'); // TODO : 수정해야함
      alert('경로 추가 및 페이지 작업 필요');
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
    alarmTestSaver() {
      const self = this;
      self.$axios.post('http://localhost:8082/alarm/sender').then (function(rs) {
        alert('DB에 적재되었습니다.');
        console.log(rs);
      });
    },
    alarmTestHistory() { // 트리거를 통해 saver 의 값을 알림 전용 테이블에 insert, insert된 데이터를 가져와 콘솔에 출력
      
    }
  }
}
</script>

<style>
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
  display: inline-flex;
  width: 100%;
  border-bottom: 3px solid rgb(244, 191, 79);
}

/* 좌측 로고 */
.box-left {
  width: 200px;
  display: flex;
  justify-content: left;
}

.logo {
  width: 100px;
  height: 50px;
  cursor: pointer;
}

/* 중앙 카테고리 */
.img-category {
  width: 20px;
  height: 20px;
}

.box-category {
  position: relative;
  width: inherit;
  height: 50px;
  display: flex;
  justify-content: center;
}

.category {
  display: flex;
  margin: 0 auto;
}

.category-list {
  list-style: none;
  list-style-type: none;
}

.category-list li {
  margin-top: 10px;
  float: left;
  margin-left: 20px;
  font-weight: normal;
  cursor: pointer;
  font-family: 'BagleFatOne';
  font-size: 20px;
}

.category-list li:hover {
  color: rgb(244, 191, 79);
}

.min-box-category  {
  display: none;
}

/* 우측 콘텐츠 컨테이너 */
.box-right {
  width: 200px;
  display: flex;
  justify-content: right;
  align-items: center;
}

/* 로그인 관련 */
.member-manage {
  display: block;
  font-size: 12px;
  font-weight: bold;
  justify-content: right;
}

.member-manage span {
  margin-right: 5px;
  cursor: pointer;
  font-family: 'IBMPlexSansKR-Bold';
}

.member-manage span:hover {
  color: rgb(244, 191, 79);
  margin-right: 5px;
  cursor: pointer;
}

/* 마이페이지, 알림뱃지 */
.box-image {
  display: block;
}

.mypage, .pet-alert {
  height: 40px;
  cursor: pointer;
}

@media screen and (max-width: 1050px) {

  /* 헤더 전체 영역 */
  .pet-header {
    border-bottom: 3px solid rgb(244, 191, 79);
    display: inline-flex;
    justify-content:space-between;
    align-items: center;
  }

  /* 좌측 로고 및 카테고리 영역 */
  .box-left {
    display: flex;
    position: relative;
    width: 200px;
    justify-content: left;
    height: 40px;
  }

  /* 숨김처리할 것들 */
  div.box-category, .category, .logo {
    display: none;
  }

  /* 카테고리 셀렉트박스 */
  .min-box-category {
    position: absolute;
    display: flex;
    justify-content: left;
    list-style: none;
    height: 40px;
  }

  .icon-menu {
    width: 25px;
    height: 25px;
    cursor: pointer;
    margin-left: -30px;
    margin-top: -10px;
  }

  .min-box-category li {
    margin-top: 10px;
    float: inherit;
    position: relative;
    list-style: none;
    vertical-align: middle;
    text-align: left;
    padding: 5px;
  }

  .min-box-list li {
    margin-left: -50px;
  }

  .min-box-list li span {
    font-weight: normal;
    padding: 10px;
    font-size: 20px;
    font-family: 'BagleFatOne';
  }

  ul.pet-menu > li:hover ul.min-box-list {
    display: block;
    margin: -20px;
  }

  ul.pet-menu > li ul.min-box-list {
    display: none;
  }

  .min-box-category li div span {
    text-decoration: none;
    font-weight: bold;
    background-color: white;
  }

  .min-box-category li div span:hover {
    color: white;
    background-color: rgb(244, 191, 79);
  }

  /* 우측 콘텐츠 컨테이너 */
  .box-right {
    width: 300px;
    display: flex;
    position: relative;
    justify-content: right;
    align-items: right;
  }

  /* 로그인 관련 */
  .member-manage {
    display: block;
    font-size: 12px;
    font-weight: bold;
  }

  .member-manage span {
    margin-right: 5px;
    cursor: pointer;
  }

  /* 마이페이지, 알림뱃지 */
  .box-image {
    display: block;
  }

  .mypage, .pet-alert {
    height: 40px;
    cursor: pointer;
  }
}

.tmp-line {
  border-bottom: 3px solid rgb(244, 191, 79);
}

.footer {
  height: 200px;
  background-color: #efefef;
}

</style>
