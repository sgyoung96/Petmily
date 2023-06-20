<template>
    <!-- 공통 헤더 -->
    <div class="header"> 
      <div class="box-left">
        <img class="logo" @click="gotoMain" src="./assets/logo_sample.png" >
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
          <img class="alert" src="./assets/alert_sample.jpg" >
        </div>
      </div>
    </div>
    <!-- //공통 헤더 -->
    
    <br>
    <!-- 기존 링크 모음 (테스트용, 추후 삭제 예정) -->
    <router-link to="/kakaomaphome">카카오맵</router-link> | 
    <router-link to="/api">Api</router-link>
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
export default {
  
  data () {
    return {
      loginId:null,
    }
  },
  created:function(){ // 이 컴포넌트가 시작될때 실행되는 함수
    this.loginId = sessionStorage.getItem('loginId')
    
    if(this.loginId == 'admin'){
      this.$router.push('/adminhome')
    } else {
      this.$router.push('/') //router로 이동하면 페이지가 전체 이동하는게 아니라 <router-view/>만 바뀐다    
    }
  },
  methods:{
    gotoMain() { // 로고 클릭시 메인으로 이동
      this.$router.push('/');
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
      this.$router.push('/member');
    },
    logout(){ // 로그아웃
      sessionStorage.clear()
      window.location.href = "/" //리디렉션
    },
    out(){
      
      const self = this;

      let token = sessionStorage.getItem('token')
      self.$axios.delete('http://localhost:8082/members/' + self.loginId, {headers:{'token':token}}) 
      .then(function(res){ 
        if(res.status == 200){
          if(res.data.flag){
          self.logout()
          alert('탈퇴완료')
          }
        }else{
          alert('에러코드:' + res.status)
        }
      });
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 헤더 전체 영역 */
.header {
  position: relative;
  display: flex;
}

/* 좌측 로고 */
.box-left {
  width: 500px;
  display: flex;
  justify-content: left;
}

.logo {
  width: 100px;
  height: 50px;
  cursor: pointer;
}

/* 중앙 카테고리 */
.box-category {
  width: 100%;
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
  float: left;
  margin-left: 20px;
  font-weight: bold;
  cursor: pointer;
}

/* 우측 콘텐츠 컨테이너 */
.box-right {
  width: 500px;
  display: flex;
  justify-content: right;
  align-items: center;
}

/* 로그인 관련 */
.member-manage {
  display: block;
  font-size: 12px;
  font-weight: bold;
}

.member-manage span {
  /*content: "|";*/
  margin-right: 5px;
  cursor: pointer;
}

/* 마이페이지, 알림뱃지 */
.box-image {
  display: block;
}

.mypage, .alert {
  height: 40px;
  cursor: pointer;
}

.footer {
  height: 200px;
  background-color: #efefef;
}




nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
