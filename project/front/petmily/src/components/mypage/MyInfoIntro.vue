<template>
  <div>
    <div class="container">
        <img class="bg-pet" src="../../assets/images/bg_chk_pw.png" />
        <div class="text-box">
            <p class="header">안전한 회원 정보 수정을 위해 비밀번호를 한번 더 입력해 주세요.</p>
            <input v-model="pw" class="chk-pw" type="password" @keyup.enter="chkPw()" placeholder="password">
            <img class="ico-enter" @click="chkPw()" src="../../assets/ico-enter.png" /><br>
            
            <label @click="chkPw()" class="lbl-btn"><span class="span-btn">정보수정</span></label>
            <p class="txt-kakao">카카오 로그인 회원은 정보수정 버튼을 눌러주세요.</p>
        </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'MyInfoIntro',
  data () {
    return {
        id: sessionStorage.getItem('loginId'),
        chkpw: '',
        pw: '',
        flag: false,
    }
  },
  created: function () {
      
  },
  methods: {
    chkPw() {
        this.getMemInfo();
    },
    getMemInfo() {
        if (sessionStorage.getItem('loginFlag') == 'kakao') {
            this.emitData();
        } else {
            const self = this;
            this.$axios.get('http://localhost:8082/members/' + this.id)
            .then(function(res){
                if(res.status == 200){
                    let dto = res.data.dto
                    if(dto != null){
                        self.chkpw = dto.pwd;
                        if (self.chkpw == self.pw) {
                            self.flag = true;
                            self.emitData();
                        } else {
                            self.flag = false;
                            self.pw = '';
                            self.emitData();
                        }
                    } else {
                        self.flag = false;
                        self.pw = '';
                        self.emitData();
                    }
                } else {
                    self.flag = false;
                    self.pw = '';
                    console.log('에러코드 :' + res.status)
                    self.emitData();
                }
            });
        }
        
    },
    emitData() {
        this.$emit('childEvent', this.flag);
    }
  },
  components: {
     
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 20px;
    padding-bottom: 20px;
    display: flex;
    justify-content: center;
}

.header {
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 15px;
    margin-left: 30px;
    margin-top: 50px;
}

.bg-pet {
    width: 200px;
    height: auto;
    border: 1px solid white;
}

.chk-pw {
  width: 200px;
  height: 50px;
  margin-bottom: 5px;
  border: none;
  padding: 10px;
  position: relative;
  color: #777777;
  font-family: 'IBMPlexSansKR-Bold';
  background-color: #eee;
  border-radius: 10px;
  margin-top: 20px;
}

.chk-pw:focus {
  outline: #777777;
  border-radius: 10px;
}

.chk-pw::placeholder {
    color: #777777;
}

.ico-enter {
  position: absolute;
  width: 25px;
  height: auto;
  z-index: 2;
  margin-left: -35px;
  margin-top: 33px;
  cursor: pointer;
}

.lbl-btn {
    margin-top: 20px;
    width: 200px;
    height: 50px;
    border: 1px solid rgb(244, 191, 79);
    border-radius: 10px;
    justify-content: center;
    padding-top: 12px;
    background: rgb(244, 191, 79);
    cursor: pointer;
}

.span-btn {
    font-family: 'IBMPlexSansKR-SemiBold';
    font-size: 15px;
    color: white;
}

.txt-kakao {
    margin-top: 5px;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}
</style>
