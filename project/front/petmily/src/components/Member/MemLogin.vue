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
          <span><u>회원가입</u></span><br>
          <span><u>ID/PW 찾기</u></span>
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
      pwd:''
    }
  },
  methods:{
    login(){
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
