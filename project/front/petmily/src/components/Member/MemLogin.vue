<template>

  <div class="box-logo">
    <img class="login-logo" src="../../assets/logo_sample.png" >
  </div>
  
  <div class="form-container">
    <div id="mylogin">
      <input type="text" id="input_id" v-model="id" placeholder="아이디를 입력하세요.">
      <br>
      <input type="password" id="input_pwd" v-model="pwd" placeholder="비밀번호를 입력하세요."><br/>
      <input type="text" class="btn-login" v-on:click="login" value="로그인" readonly>

      <div class="box-btns">
        <div id="kakao-login" @click="kakaoLogin()">
          <img class="btn-kakao" src="../../assets/kakao_login_large.png" >
        </div>
        <div class="links">
          <span @click="join()" ><u>회원가입</u></span><br>
          <span><u>ID/PW 찾기</u></span>
        </div>
      </div>
    </div>
  </div>
  <div @click="kakaoLogout()">카카오로그아웃테스트</div>
</template>

  <script>

export default {
  name: 'MemLogin',
  data () {
    return {
      id:'',
      pwd:''
    }
  },
  methods:{
    login(){ // 로그인
      const self = this;
      const form = new FormData();
      form.append('id', self.id) // form.append('name이 id인것(폼양식의 이름)', self.id(id의 값))
      form.append('pwd', self.pwd)

      //post(url, 폼데이터)
      self.$axios.post('http://localhost:8082/members/login', form)
      .then(function(res){ 
        if(res.status == 200){
        
          if(res.data.flag){
            sessionStorage.setItem('token', res.data.token)
            sessionStorage.setItem('loginId', self.id)
            sessionStorage.setItem('type', res.data.type)
            // self.$router.push('/') location.href와 비슷 강사님 코드
           window.location.href = "/"
            alert('로그인')
          }else{
             alert('로그인실패')
          }
        }else{
          alert('에러코드 :' + res.status)
        }
      });
    },
    kakaoLogin() { // 카카오로그인
      window.Kakao.Auth.login({
        scope: "account_email",
        success: this.getKakaoAccount
      });
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          const email = kakao_account.email;
          console.log("email: " + email)
          console.log('kakao', res)
          // console.log('nickname', kakao_account.profile_nickname) >> undefined

          alert('로그인되었습니다.');
          
          /* 세션 처리하기 */
          //sessionStorage.setItem('loginId', kakao_account.name)
          
          this.$router.push('/'); // 메인 페이지로 이동
        },
        fail: (error) => {
          console.log(error)
        }
      });
    },
    kakaoLogout() { // '카카오로그아웃테스트' 클릭시 이벤트 발생 (ui 수정 필요)
      const self = this;

      /* 로그아웃 하나, 완전히 연결을 끊지 않음 */
      // window.Kakao.Auth.logout((res) => {
      //   console.log(res)
      //   alert('로그아웃되었습니다.');
      //   self.$router.push('/');
      // })

      /* 사용자가 카카오 연결을 끊고자 할 때 */
      window.Kakao.API.request({
        url: '/v1/user/unlink'
      }).then(function(response) {
        console.log(response)
        self.$router.push('/'); // 메인 페이지로 이동
      }).catch(function(error) {
        console.log(error)
      })
    },
    join() {
      const loginId = sessionStorage.getItem('loginId')
      const self = this;
      
      if (loginId != null) {
        alert('로그아웃 후 이용 가능한 서비스입니다.');
        self.$router.push('/'); // 메인 화면으로 이동
      } else { // loginId 가 null ; 존재하지 않음
        self.$router.push('/join'); // 회원가입 화면으로 이동
      }
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.box-logo {
  position: relative;
  display: inline;
  z-index: 2;
}

.login-logo {
  width: 30%;
  margin-bottom: -5%;
}

.form-container {
  position: relative;
  display: flex;
  justify-content: center;
  vertical-align: middle;
  z-index: 1;
}

#mylogin {
  width: 50%;
  border: 3px solid rgb(147, 214, 147);
  background-color: rgb(147, 214, 147);
  border-radius: 30px; 
  padding-top: 5%;
  padding-bottom: 5%;
  justify-content: center;
}

input {
  width: 200px;
  margin-bottom: 5px;
  border: none;
  padding: 10px;
}

input:focus {
  outline: none;
}

.btn-login {
  text-align: center;
  background-color: rgb(237, 255, 237);
  border: none;
  padding: 10px;
  font-weight: bold;
}

.btn-login {
  width: 200px;
  cursor: pointer;
}

.box-btns {
  display: flex;
  justify-content: center;
}

.btn-kakao {
  cursor: pointer;
}

.links {
  margin-left: 10px;
  padding-left: 10px;
  padding-right: 10px;
  height:50px;
  background-color: #42b983;
  font-size: 15px;
  font-weight: bold;
}

.links span {
  cursor: pointer;
}

.btn-kakao {
  width: 100px;
}

  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    /*margin: 0 10px;*/
  }
  a {
    color: #42b983;
  }
</style>
