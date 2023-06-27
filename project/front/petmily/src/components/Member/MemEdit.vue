<template>


    <div id="memedit">

        

        <span class="box-profile" style="background: #black;"> 
            <img class="profile" :src="'http://localhost:8082/members/imgs/'+ id">
        </span>
        <input type="file" id="profile"><br/> 
        ID : <input type="text" v-model="id" readonly><br/>
        NAME : <input type="text" v-model="name" readonly><br/>
        PWD : <input type="password" v-model="oldpwd1" ><button @click="pwdcheck">비밀번호 확인</button><br/>
        new pwd : <input type="password" v-show = "newpwdchecktrue" v-model="pwd" ><br/>
         <span class ="font_id_red" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</span><br/>  
        pwd check : <input class="input-item" type="password"  v-show = "newpwdchecktrue" id="newpwdcheck" v-model="newpwdcheck"  @blur="checkNewPwdEqual"><br/>
        <span class ="font_id_red" v-show="isPwdCheckEqual">비밀번호 확인해주세요</span><br/>  
        EMAIL : <input type="text" id="email" v-model="email"><button v-on:click="emailcheck" >이메일 확인</button><br/>
        <span class ="font_id_red" v-show="isEmailCheck">이메일 형식을 확인해주세요</span><br/> 
         <input input class="input-item" type="text" id="emailCode" v-model="emailCode"><button v-on:click="emailCodeCheck">인증</button><br/> 
         <span class ="font_id_red" v-show="isEmailCodeCheck">인증코드를 확인해주세요</span><br/> 
        BIRTH : <input type="text" v-model="birth" readonly><br/>
        GENDER : <input type="text" v-model="gender" readonly><br/>
        PHONE : <input type="text" v-model="phone" @blur="checkPhone"><br/>
         <span class ="font_id_red" v-show="isPhoneCheck">전화번호 형식을 확인해주세요</span><br/> 
        ADDRESS : <input type="text" v-model="address" readonly><br/>
        <button v-on:click="editcheck" >내정보 수정</button>
     
  </div>
</template>

 <script>

export default {
  name: 'MemEdit',
  data () {
    return {
        id:'',
        name:'',
        oldpwd1:'',
        pwd:'',
        newpwdcheck:'',
        newpwdchecktrue:false,
        isPwdCheck:false,
        isPwdCheckEqual:false,
        email:'',
        emailCode:'',
        emailtf:false,
        isEmailCodeCheck:false,
        birth:'',
        gender:'',
        phone:'',
        isPhoneCheck:false,
        address:'',
        edittf:false,
        editemailtf:false
      
    }
  },

  watch:{
    'pwd' : function(){
        this.checkNewPwd()
    },
    'email': function(){
        this.checkEmail()
    }
  },

  created:function(){
      const moment = require('moment');
     

    let token = sessionStorage.getItem('token')
    alert(token)

    const self = this;
    this.id = sessionStorage.getItem('loginId')
    alert(self.id)

    self.$axios.get('http://localhost:8082/members/' + self.id, {headers:{'token':token}})
    .then(function(res){
        if(res.status == 200){
            let dto = res.data.dto
            if(dto != null){
            self.id = dto.id
            self.name = dto.name
            self.oldpwd2 = dto.pwd
            self.email = dto.email
            self.oldemail = dto.email
            self.birth = moment(dto.birth).format('L');
            self.gender = dto.gender
            self.phone = dto.phone
            self.address = dto.address
            }else{
            alert('없는 아이디 이거나 만료된 토큰')
          }
        
        }else{
          alert('에러코드 :' + res.status)
        }
    })
   

  },

  methods:{

    editcheck(){
      const self = this;
      this.editcheckEmail()
      alert(self.editemailtf)
      
      if(self.edittf == true){
      
      if(this.isEmpty(self.pwd)&&this.isEmpty(self.pwdcheck)&&self.editemailtf
      &&this.isEmpty(self.phone)){
 
       this.edit()
   
      }else{
       
       if(!self.emailtf){
        alert('이메일이 변경되었습니다. 인증 필요')
       }else{
        alert('필수 항목 기입 확인 필요')
       }
      }
      }else{
        alert('비밀번호 확인 필요')
      }
    },

    isEmpty(value){
      const self = this;
      
      if(value == '' || value == null || value == undefined){
        return false
      }else{
         self.tf = true;
        return true
      }
    },

    edit(){
      
      const self = this;
      //const moment = require('moment');
      //const mybirth = moment(self.birth).format('L');

      let formdata = new FormData();
      formdata.append('id',self.id)
      formdata.append('pwd',self.pwd)
      formdata.append('email',self.email)
      formdata.append('phone',self.phone)

      const file = document.getElementById('profile')
      formdata.append('f', file.files[0]);
  
      
     
      self.$axios.put('http://localhost:8082/members', formdata,
      {headers:{"Content-Type":"multipart/form-data"}}) 
      .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
        alert(dto.id +'님 내정보 수정 완료')
         window.location.href = "/"
        }else{
          alert('에러코드:' + res.status)
        }
      });
    },

    pwdcheck(){
        const self = this;
        alert(self.oldpwd1)
        alert(self.oldpwd2)
        if(self.oldpwd1 === self.oldpwd2){
            self.newpwdchecktrue=true;
            self.edittf = true;
        }else{
            alert('비밀번호 재확인 필요')
            self.edittf = false;
        }
    },
    checkNewPwd(){
        const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
    if(!validatePassword.test(this.pwd)){
    
        this.isPwdCheck = true;
        document.getElementById("newpwdcheck").readOnly = true;
    }else{
        this.isPwdCheck = false;
        document.getElementById("newpwdcheck").readOnly = false;
    }
  },
   checkNewPwdEqual(){
    const self = this;
    console.log(this.pwd + "/" + this.newpwdcheck)
    if(this.pwd === this.newpwdcheck){
      this.isPwdCheckEqual = false;
      
    }else{
      this.isPwdCheckEqual=true;
      console.log(self.newpwdcheck)
      self.newpwdcheck = '';
      
    }

  },
  sendEmail(){
        const self = this;

         let formdata = new FormData();
        formdata.append('email',self.email)
        self.$axios.post('http://localhost:8082/emailConfirm', formdata)
        .then(function(res){ 
        if(res.status == 200){
        self.confirm = res.data.confirm
        
        alert(self.confirm)
        
        alert('메일 전송 완료')
        
        }else{
          alert('오류')
        }
      });

     },
     emailcheck(){
    const self = this;

    self.$axios.get('http://localhost:8082/members/email/'+self.email)
    .then(function(res){ //
        if(res.status == 200){
        
          if(res.data.dto == null){
           self.sendEmail()
          
          }else{
             alert('중복된 이메일')
             self.email=''
          }
        }else{
          alert('에러코드 :' + res.status)
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
            self.emailtf=true;
            
            document.getElementById("email").readOnly = true;
            document.getElementById("emailCode").readOnly = true;
      
        }else{
            this.isEmailCodeCheck=true;
            self.emailtf=false;
            self.emailCode='';
            
        }

    },
     checkEmail(){
    
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/
     
    if(!validateEmail.test(this.email)){
        this.isEmailCheck = true;
    }else{
        this.isEmailCheck = false;
    }
  },
  editcheckEmail(){
    const self = this;
 
    if(self.oldemail === self.email){
        self.emailtf=true;
        self.editemailtf=true;
    }else{
        if(self.emailtf){
           
            self.editemailtf = true;
        }else{
           
            self.editemailtf = false;
        }
    }
  },
  checkPhone(){
    const self = this;
    const validatePhone = /^\d{3}-\d{3,4}-\d{4}$/;
    if(!validatePhone.test(this.phone)){
        self.isPhoneCheck = true;
        self.phone='';
    }else{
        self.isPhoneCheck = false;
    }
  },
}
}
</script>
 
 <style scoped>
 .box-profile {
  display: block;
  width: 100px;
  height: 100px; 
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
 </style>