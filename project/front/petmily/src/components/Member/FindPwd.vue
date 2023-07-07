<template>


    <div class="findpwd_area">
      <h2 class="findpwd_title">비밀번호 찾기</h2>

      <div v-show="showidemail">
        <!-- 아이디 -->
        <div class="input_box" >
          <input type="text" class="input_txt" v-model="id" placeholder="ID"><br/>
        </div> 

        <!-- 이메일 -->
        <div class="input_box" >
          <input type="text"  class="input_txt" v-model="email" placeholder="EMAIL">  
        </div>


        <!-- 이메일 인증 보내기 버튼-->
        <div class="input_box">
        <button class="input_button" v-show="showemailbtn" v-on:click="sendEmail">이메일 확인</button><br/>
        </div>
      </div>
        
        <span class="findid" v-show="findid">아이디가 기억나지 않는다면? <span class="findidlink"  @click="findId">아이디 찾기</span></span>
      


        <!-- 이메일 인증코드 입력 -->
        <div class="input_box" v-show="emailCodeShow">
          <input type="text" class="input_code_txt" id="input_code_txt" v-model="emailCode" placeholder="인증코드">
          <button  class="input_button"  v-on:click="emailCodeCheck">인증</button><br/>
        </div>


           


        <div class="input_box" v-show="changepwdShow">
          <p>본인 인증절차가 완료되었습니다. </p>
          <p>새로운 비밀번호를 재설정해주시기 바랍니다.</p><br/>
          <input type="password" class="input_txt" v-model="pwd" placeholder="PWD"><br/>
          <span class ="input_error" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</span><br/>

          <input type="password" class="input_txt" id="pwdcheck" v-model="pwdcheck"  placeholder="PWD 확인" @blur="checkPwdEqual"><br/> 
          <span class ="input_error" v-show="isPwdCheckEqual">비밀번호 확인해주세요</span><br/>  
          <button class="input_button" @click="changepwd" style="margin-top:10px">변경</button>
        </div>

     
    </div>
</template>

<script>

export default {
  name: 'FindPwd',
  data () {
    return {
        id:'',
        pwd:'',
        email:'',
        emailCode:'',
        emailCodeShow:false,
        pwdtf:false,
        isPwdCheck:false,
        isPwdCheckEqual:false,
        changepwdShow:false,
        showemailbtn:true,
        showidemail:true,
        findid:true
       
      
    }
  },

  watch:{
   'pwd' : function(){
        this.checkPwd()
    }
  },


  methods:{
    sendEmail(){
        const self = this;

        let formdata = new FormData();
        formdata.append('id',self.id)
        formdata.append('email',self.email)

        if(self.id == '' || self.email == ''){
          alert('아이디와 이메일을 작성해주세요')
          return
        }

        self.$axios.get('http://localhost:8082/members/' + self.id)
        .then(function(res){ 
        if(res.status == 200){
        self.dto = res.data.dto
        
        if(self.dto != null){
            if(self.dto.id === self.id && self.dto.email === self.email){
                
               self.$axios.post('http://localhost:8082/emailConfirm', formdata)
                 .then(function(res){ 
                 if(res.status == 200){
                self.confirm = res.data.confirm
        
                alert(self.confirm)
        
                alert('메일 전송 완료')
                self.emailCodeShow = true;
                self.showemailbtn=false;
                self.findid=false;
        
                }else{
                     alert('오류')
                }
                }); 
            }else{
                alert('아이디와 이메일 확인 필요')
            }
        }else{
            alert('유효하지 않은 아이디')
        }
       
        }else{
          alert('오류')
        }
      });   
     },
      emailCodeCheck(){
        
        const self = this;

        alert(self.emailCode)
        alert(self.confirm)
  
        if(self.confirm === self.emailCode){
            alert('인증완료')
          
            self.changepwdShow=true;
            self.emailCodeShow=false;
            self.showidemail=false;
      
        }else{
            
            self.emailCode='';
            alert('인증번호 확인해주세요')
           
            
        }

    },
    changepwd(){
        const self = this;
 

        let formdata = new FormData();
        formdata.append('id',self.id)
        formdata.append('pwd',self.pwd)
        if(self.pwdtf){
            alert('비밀번호 확인 완료')
                self.$axios.put('http://localhost:8082/members/pwd', formdata)
                .then(function(res){
                if(res.status == 200){
                
                alert('비밀번호 수정 완료')
                window.location.href = "/"
                }else{
                    console.log('에러코드 :' + res.status);
                }
                }); 


        }else{
            alert('비밀번호 확인 필요')
        }
    },
     checkPwd(){
        const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
    if(!validatePassword.test(this.pwd)){
    
        this.isPwdCheck = true;
        document.getElementById("pwdcheck").readOnly = true;
    }else{
        this.isPwdCheck = false;
        document.getElementById("pwdcheck").readOnly = false;
        
    }
  },

    checkPwdEqual(){
    const self = this;
    
    if(this.pwd === this.pwdcheck){
      this.isPwdCheckEqual = false;
      self.pwdtf=true;
       
      
    }else{
      this.isPwdCheckEqual=true;
      console.log(self.pwdcheck)
      self.pwdcheck = '';
      
      
    }

  },
  findId(){
      const self = this;
      if (sessionStorage.getItem('loginFlag') != 'normal') {
        self.$router.push('/findid'); 
      } else {
        console.log('normal');
      }

    }

   
}
}
</script>

<style scoped>
.findpwd_area {
    margin: 0 auto;
    padding: 58px 0 160px;
    width: 430px
}
.findpwd_title {
    padding-bottom: 46px;
    text-align : center;
    font-size: 32px;
    letter-spacing: -.48px;
    color: #000
}
.input_box {
  
    padding: 0 0 20px;
    position: relative;
}

.input_txt{
  width: 100%;
  height: 60px;
  border:1px solid gray;
  border-radius: 10px;
  font-size:large;
  padding: 10px;
  
}



.input_code_txt{
  width: 100%;
  height: 45px;
  border:1px solid gray;
  border-radius: 10px;
  margin-bottom:10px;
  padding: 10px;

}

.input_button{
  width: 100%;
  height: 45px;
  border-radius: 10px;
  border:0px;
  background-color:rgb(255, 214, 91);
  font-size:large;
  color:white;
  font-weight:bold

}

.input_button:active {
    background-color:rgb(205, 204, 204);
}

.input_error {
  display: flex;
  text-align: left;
  line-height: 16px;
  font-size: 13px;
  color: red;
  position: absolute;
}

p{
  font-size: small;
}

input::placeholder{
  font-size:15px;
  color:rgb(190, 190, 190);
  padding: 10px;
}

.findid{
  padding:20px;
  color:rgb(190, 190, 190);
}

.findidlink{
  color:rgb(255, 214, 91);
  cursor: pointer;
}

</style>
