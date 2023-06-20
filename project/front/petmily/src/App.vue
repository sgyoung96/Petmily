<template>
    <!-- 공통 헤더 -->
    <div class="header"> 
      <div class="box-left">
        <img class="logo" src="./assets/logo_sample.png" >
      </div>
      
      <div class="box-category">
        <div class="category">
          <ul class="category-list">
            <li>
              <div>
                <span>동물보호소</span>
              </div>
            </li>
            <li>
              <div>
                <span>분양해요</span>
              </div>
            </li>
            <li>
              <div>
                <span>입양일지</span>
              </div>
            </li>
            <li>
              <div>
                <span>봉사모집</span>
              </div>
            </li>
            <li>
              <div>
                <span>입양신청</span>
              </div>
            </li>
            <li>
              <div>
                <span>입양가이드</span>
              </div>
            </li>
          </ul>
        </div>
      </div>

      <div class="box-right">
        <div class="member-manage">
          <span style="display: none;">로그인 연장 00:00</span> <!-- 추가기능 -->
          <span>로그인</span>
          <span style="display: none;">로그아웃</span>
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
    <router-link to="/">Home</router-link> |
    <router-link to="/diaryboardhome">입양일기게시판</router-link> |
    <router-link to="/adopt">분양게시판홈</router-link> |
    <router-link to="/kakaomaphome">카카오맵</router-link> |
    <router-link to="/volboardhome">봉사모집게시판</router-link> |
    <router-link to="/applyform">Apply</router-link> |
    <router-link to="/api">Api</router-link> |
    <br>
    <div v-if="loginId==null">
      <router-link to="/join">회원가입</router-link> |
      <router-link to="/login">로그인</router-link>
    </div>
    <div v-else>
      <button v-on:click="logout">로그아웃</button>
    </div>
    <!-- //기존 링크 모음 (테스트용, 추후 삭제 예정) -->

  <!-- 이곳에 라우터로 설정한 화면이 로드됨 -->
  <router-view/>
  <!-- //이곳에 라우터로 설정한 화면이 로드됨 -->

  <!-- 풋터 -->
  <footer class="footer">
    <div>
      <p>이곳에 풋터 디자인</p>
      <p>Copyright by Fetmily, ... etc</p>
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
        }else {
          this.$router.push('/') //router로 이동하면 페이지가 전체 이동하는게 아니라 <router-view/>만 바뀐다
        
     }
  },
  methods:{
    logout(){
      
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
}

/* 마이페이지, 알림뱃지 */
.box-image {
  display: block;
}

.mypage, .alert {
  height: 40px;
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
