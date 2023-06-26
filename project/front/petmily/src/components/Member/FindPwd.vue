<template>


    <div id="findpwd">
        <h3>비밀번호 찾기</h3>
        id : <input type="text" v-model="id"><br/>
        email : <input type="text" v-model="email"><button v-on:click="sendEmail">이메일 확인</button><br/>
        <input type="text" v-show="emailCodeShow" v-model="emailCode" placeholder="인증코드"><button v-show="emailCodeShow" v-on:click="emailCodeCheck">인증</button><br/>
        <span class ="font_id_red" v-show="isEmailCodeCheck">인증코드를 확인해주세요</span><br/> 


        <div v-show="changepwdShow">
            <input type="password" v-model="pwd" placeholder="PWD"><br/>
            <span class ="font_id_red" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</span><br/>  
            <input type="password" id="pwdcheck" v-model="pwdcheck"  placeholder="PWD 확인" @blur="checkPwdEqual"><br/> 
            <span class ="font_id_red" v-show="isPwdCheckEqual">비밀번호 확인해주세요</span><br/>  
            <button @click="changepwd">변경</button>
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
        changepwdShow:false
       
      
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
            this.isEmailCodeCheck=false;
           
            self.changepwdShow=true;
      
        }else{
            this.isEmailCodeCheck=true;
            self.emailCode='';
           
            
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

  }

   
}
}
</script>
