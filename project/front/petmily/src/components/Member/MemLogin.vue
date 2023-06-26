<template>

  <div class="box-logo">
    <img class="login-logo" src="../../assets/logo_sample.png" >
  </div>
  
  <div class="form-container">
    <div id="mylogin">
      <input type="text" id="input_id" v-model="id" placeholder="아이디를 입력하세요.">
      <br>
      <input type="password" id="input_pwd" v-model="pwd" @keyup.enter="login()" placeholder="비밀번호를 입력하세요.">
      
      <div class="box-btns">
        <div class="logins">
          <img class="btn-kakao" @click="kakaoLogin()" src="../../assets/kakao_login_large_narrow.png" >
          <br>
          <span class="link-join" @click="join()" >회원가입</span>
          <span class="deco">&nbsp;&nbsp;|&nbsp;&nbsp;</span>
          <span class="link-id">ID 찾기</span>
          <span class="deco">/</span>
          <span class="link-pw" @click="findPwd">PW 찾기</span>
        </div>
        
      </div>
    </div>
  </div>
</template>

  <script>

export default {
  name: 'MemLogin',
  data () {
    return {
      id:'',
      pwd:'',
      dto: {},
      kakaoId: '',
      kakaoName: '',
      loginFlag: ''
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
        console.log(res.data);
        if (res.status == 200) {
          if (res.data.flag) {
            sessionStorage.setItem('token', res.data.token)
            sessionStorage.setItem('loginId', self.id)
            sessionStorage.setItem('type', res.data.type)
            sessionStorage.setItem('loginFlag', 'normal')
            
            window.location.href = "/";
          } else {
            alert('회원정보를 다시 확인해 주세요.');
          }
        } else {
          alert('서비스 이용에 불편을 드려 죄송합니다. \n다시 시도해 주세요.');
          console.log('에러코드 :' + res.status);
        }
      });
    },
    kakaoLogin() { // 카카오로그인
      window.Kakao.Auth.login({
        success: this.getKakaoAccount
      });
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          console.log('kakao', res);
          
          this.kakaoId = res.id;
          this.kakaoName = res.kakao_account.profile.nickname;
          this.loginFlag = 'kakao';

          sessionStorage.setItem('loginId', res.id);
          sessionStorage.setItem('kakaoName', res.kakao_account.profile.nickname);
          sessionStorage.setItem('loginFlag', 'kakao');
          
          const self = this;

          self.$axios.get('http://localhost:8082/members/' + sessionStorage.getItem('loginId')).then (function(rs) {
            console.log(rs);
            window.location.href = '/';
            // self.dto = rs.data.dto;
            // if (self.dto != null) {
            //   window.location.href = "/";
            // } else {
            //   self.$router.push({name:'KakaoAdditionalForm', query:{kakaoId: self.kakaoId, kakaoName: self.kakaoName, loginFlag: self.loginFlag}}) // 추가정보기입화면
            // }
          });
        },
        fail: (error) => {
          console.log(error)
        }
      });
    },
    join() {
      const self = this;
      if (sessionStorage.getItem('loginFlag') != 'normal') {
        self.$router.push('/join'); // 회원가입 화면으로 이동
      } else {
        console.log('normal');
      }
    },
    findPwd(){
      const self = this;
      if (sessionStorage.getItem('loginFlag') != 'normal') {
        self.$router.push('/findpwd'); // 회원가입 화면으로 이동
      } else {
        console.log('normal');
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
  border: 10px solid rgb(248, 237, 138);
  background-color: #fff;
  border-radius: 30px; 
  padding-top: 5%;
  padding-bottom: 5%;
}

#input_id, #input_pwd {
  color: white;
  font-family: 'IBMPlexSansKR-Bold';
  background-color: rgb(244, 206, 79);
  border-radius: 10px;
}

#input_id::placeholder, #input_pwd::placeholder {
  color: white;
  font-family: 'IBMPlexSansKR-Bold';
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
  background-color: white;
  border: 5px solid rgb(248, 237, 138);
  border-radius: 10px;
  padding: 10px;
  font-weight: bold;
  font-family: 'IBMPlexSansKR-Bold';
  color: rgb(244,191,79);
  font-size: 18px;
}

.btn-login {
  width: 200px;
  cursor: pointer;
}

.box-btns {
  margin-top: 5px;
  position: relative;
}

.logins {
  display: block;
}

.deco {
  font-family: 'IBMPlexSansKR-Regular';
  font-weight: bold;
  font-size: 12px;
}

.btn-kakao {
  cursor: pointer;
  border-radius: 20px;
  width: 200px;
  padding-bottom: 5px;
}

.links {
  margin-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  background-color: #fff;
  border-radius: 20px;
  display: flex;
  justify-content: space-evenly;
  width: 180px;
}

.link-join, .link-id, .link-pw {
  cursor: pointer;
  font-family: 'IBMPlexSansKR-Regular';
  font-weight: bold;
  font-size: 12px;
}

.link-join:hover, .link-id:hover, .link-pw:hover {
  cursor: pointer;
  font-family: 'IBMPlexSansKR-Medium';
  font-weight: bold;
  font-size: 14px;
  color: rgb(244,191,79);
}

</style>
