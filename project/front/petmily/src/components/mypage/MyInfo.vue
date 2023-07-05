<template>
  <div class="container">
    <div class="container-chkpw">
      <MyInfoIntro id="intro" @childEvent="sendData" />
    </div>
    <div class="container-edit">
      <MyInfoEdit id="edit" />
    </div>
  </div>
</template>

 <script>
import img from "@/assets/imgs/mypage_sample.jpg";
import MyInfoIntro from '@/components/mypage/MyInfoIntro.vue'
import MyInfoEdit from '@/components/mypage/MyInfoEdit.vue'

export default {
  name: 'MyInfo',
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
        editemailtf:false,
        newpwdHasError:false,
        newpwdcheckHasError:false,
        newemailHasError:false,
        newemailCodeHasError:false,
        newphoneHasError:false,
        changepwd:false,


        chkPwResult: true,
      
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
    //alert(token)

    const self = this;
    this.id = sessionStorage.getItem('loginId')
    //alert(self.id)

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
            //alert('없는 아이디 이거나 만료된 토큰')
          }
        
        }else{
          //alert('에러코드 :' + res.status)
        }
    })
   

  },

  methods:{
    previewFile(){
      const fileInput = document.getElementById('profile');
      const previewImg = document.getElementById('preview');

      // 파일이 선택되지 않았을 경우 미리보기를 초기화
      if (!fileInput.files || fileInput.files.length === 0) {
      previewImg.src = `http://localhost:8082/members/imgs/${this.id}`;
      return;
      }

      // 첫 번째 파일을 가져옵니다.
      const file = fileInput.files[0];
  
      // 이미지 파일인지 확인
      if (file.type.startsWith('image/')) {
      const reader = new FileReader();
    
      // 파일 로드 완료 시 미리보기를 생성
      reader.onload = function(event) {
     
       previewImg.src = event.target.result;
    };
      

      // 파일을 읽어옵니다.
      reader.readAsDataURL(file);
      } else {
    
      // 이미지 파일이 아닌 경우 미리보기를 초기화합니다.
      previewImg.src = `http://localhost:8082/members/imgs/${this.id}`;
      }
    },

    editcheck(){
      const self = this;
      this.editcheckEmail()
      alert('email' + self.editemailtf)
      
      if(self.edittf == true){
      
      //if(this.isEmpty(self.pwd)&&this.isEmpty(self.pwdcheck)&&self.editemailtf
      //&&this.isEmpty(self.phone)){
      const changepwd = this.changepwdtf()
      alert('pwd' + changepwd)
      if(changepwd&&self.editemailtf
      &&this.isEmpty(self.phone)){
        alert(self.pwd)
        alert('여기')
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

      
        if(document.getElementById('profile').value !== ''){
        const file = document.getElementById('profile').files[0]
       
        formdata.append('f', file)}
   
      
     
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
            document.getElementById("oldpwd1").readOnly = true;
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
        this.newpwdHasError=true;
    }else{
        this.isPwdCheck = false;
        document.getElementById("newpwdcheck").readOnly = false;
        this.newpwdHasError=false;
    }
  },
   checkNewPwdEqual(){
    const self = this;
    console.log(this.pwd + "/" + this.newpwdcheck)
    if(this.pwd === this.newpwdcheck){
      this.isPwdCheckEqual = false;
      this.newpwdcheckHasError=false;
     
      
    }else{
      this.isPwdCheckEqual=true;
      console.log(self.newpwdcheck)
      self.newpwdcheck = '';
      this.newpwdcheckHasError=true;
      this.changepwdtf=false;
      
    }

  },

  changepwdtf(){
    const self = this;
    if(!this.isEmpty(self.pwd)){
      self.pwd = self.oldpwd1;
      alert('비밀번호 공백')
      return true;
    }else if(this.isPwdCheckEqual === false){
      alert('비밀번호 공백이 아니면서 pwd체크 받음')
      return true;

    }else {
      alert('비밀번호 공백이 아니면서 pwd체크 안받음')
      return false;
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
            this.newemailCodeHasError=false;
            self.emailtf=true;
            
            document.getElementById("email").readOnly = true;
            document.getElementById("emailCode").readOnly = true;
      
        }else{
            this.isEmailCodeCheck=true;
            this.newemailCodeHasError=true;
            self.emailtf=false;
            self.emailCode='';
            
        }

    },
     checkEmail(){
    
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/
     
    if(!validateEmail.test(this.email)){
        this.isEmailCheck = true;
        this.newemailHasError=true;
    }else{
        this.isEmailCheck = false;
        this.newemailHasError=false;
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
    replaceImg(e) {
            e.target.src = img;
        },
        
    sendData(value) {
      console.log(value);
      this.chkPwResult = value;
      if (this.chkPwResult == true) {
        //alert('비밀번호 확인');
        document.getElementById('intro').style = 'display: none;';
        document.getElementById('edit').style = 'display: block;';
      } else {
        alert('비밀번호를 다시 확인해 주세요.');
      }
    }
  },

  components: {
    MyInfoIntro,
    MyInfoEdit
  }
}
</script>
 
 <style scoped>

#edit {
  display: none;
}

.box-profile {
  display: block;
  width: 150px;
  height: 150px; 
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}

.edit_area {
    margin: 0 auto;
    padding: 58px 0 160px;
    width: 400px
}

.eidt_title {
    padding-bottom: 46px;
    text-align : center;
    font-size: 32px;
    letter-spacing: -.48px;
    color: #000
}

.input_box {
    padding: 0 0 40px;
    position: relative;
}

.input_title {
    width: 100%;
    position: relative;
    font-weight: bold;
    font-size:small;
    text-align: left;
    display: inline-block;
    padding-right: 6px
}

.input_txt{
  width: 100%;
  border:0px;
  border-bottom : 2px solid;
  outline-offset: 0;
  outline: none;
  color:black;
}
.input_error {
  text-align: left;
  line-height: 16px;
  font-size: 13px;
  color: red;
  position: absolute;
}

.title_danger {
    color: red;
  }

.input_danger {
  color: red;
}

button{
  border-radius: 10px;
  border:0px;
  background-color:rgb(255, 214, 91);
  font-size:medium;
 

}  

 </style>