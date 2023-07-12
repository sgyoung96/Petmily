<template>
  <div>
    <div class="container">
        <span class="notice">서비스를 탈퇴하시겠습니까?</span><br>
        <span class="ask">사유를 입력해주시면 감사하겠습니다. 개인 정보는 회원 탈퇴시 모두 삭제됩니다.</span>
        <br>
        <textarea class="reason" maxlength="300"/>
        <br>
        <label @click="exitService()" class="lbl-exit"><span class="span-exit">회원탈퇴</span></label>
        <br><br>
        <span class="ask">그동안 펫밀리를 이용해주셔서 감사합니다.</span>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ExitService',
  data () {
    return {
        
    }
  },
  created: function () {
      
  },
  methods: {
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
  },
  components: {
     
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 50px;
    padding-bottom: 100px;
    display: block;
}

.notice {
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 20px;   
}

.ask {
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 13px;   
  margin-top: 20px;
}

.reason {
  margin-top: 20px;
  font-family: 'IBMPlexSansKR-Light';
  width: 800px;
  height: 300px;
  resize: none;
  appearance: unset;
  border: 2px solid #eee;
  border-radius: 20px;
}

.lbl-exit {
  width: 200px;
  height: 50px;
  background-color: rgb(244, 191, 79);
  color: white;
  justify-content: center;
  border: rgb(244, 191, 79);
  border-radius: 20px;
  padding-top: 10px;
  margin-top: 50px;
  cursor: pointer;
}

.span-exit {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
}


</style>
