<template>
    <div id="myjoin">
      
            
      <div class="join_area">
        <h2 class="join_title">회원가입</h2>
          

          <!-- 프로필사진 -->
           <input type="file" id="profile"><br/> 
    
          
          <!-- 아이디 -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': idHasError}">아이디</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': idHasError}" type="text" v-model="id" placeholder="ID" @blur="idcheck" >
            <p class="input_error">{{msg}}</p>
          </div>

          <!-- 이름   -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': nameHasError}">이름</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': nameHasError}" type="text" v-model="name" placeholder="NAME" @blur="checkName" >
            <p class ="input_error" v-show="isNameCheck">한글 또는 영어만 허용합니다</p>
          </div>

           <!-- 비밀번호  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': pwdHasError}">비밀번호</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': pwdHasError}" type="password"  v-model="pwd" placeholder="pwd">
            <p class ="input_error" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</p><br/>  
          </div>

          <!-- 비밀번호확인  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': pwdcheckHasError}">비밀번호확인</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': pwdcheckHasError}"  type="password" id="pwdcheck"  v-model="pwdcheck" placeholder="PWD 확인" @blur="checkPwdEqual" >
            <p class ="input_error" v-show="isPwdCheckEqual">비밀번호 확인해주세요</p><br/>  
          </div>

          <!-- 이메일확인  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': emailHasError}">이메일</h6><br/>
            <input class="input_txt"  :class="{ 'input_danger': emailHasError}"  type="text" id="email" v-model="email" placeholder="예)petmily@petmily.co.kr" style= "width : 70%" @blur="checkEmail"><button v-on:click="emailcheck" style="margin-left:23px">이메일 확인</button>
            <p class ="input_error" v-show="isEmailCheck">이메일 형식을 확인해주세요</p><br/> 
          </div>

          <!-- 이메일인증  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': emailCodeHasError}">인증번호</h6><br/>
            <input input class="input_txt" :class="{ 'input_danger': emailCodeHasError}" type="text" id="emailCode" v-model="emailCode" style= "width : 83%"><button v-on:click="emailCodeCheck" style="margin-left:23px">인증</button>
            <p class ="input_error"  v-show="isEmailCodeCheck">인증코드를 확인해주세요</p><br/> 
          </div>
            

          <!-- 생일  -->
          <div class="input_box">  
            <h6 class="input_title">생년월일</h6><br/>  
            <input  type="date" v-model="birth" data-placeholder='생일입력' required aria-required="true"><br/>  
          </div>

          
          <!-- 성별  -->
          <div class="input_box">  
            <h6 class="input_title">성별</h6>
            <input name="g" type="radio" v-model="gender" id="m" value="m"><label for="m" style="margin-right:10px">남</label>
            <input name="g" type="radio" v-model="gender" id="f" value="f"><label for="f">여</label><br/>
          </div>

          <!-- 전화번호  -->
          <div class="input_box">  
            <h6 class="input_title" :class="{ 'title_danger': phoneHasError}">전화번호</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': phoneHasError}" type="text" v-model="phone" placeholder="예)010-1234-5678" @blur="checkPhone"><br/>
            <p class ="input_error" v-show="isPhoneCheck">전화번호 형식을 확인해주세요</p><br/> 
          </div>

          <!-- 주소  -->
          <div class="input_box">   
            <h6 class="input_title">주소</h6>
            <input class="input_txt" type="text" v-model="postcode" placeholder="우편번호" style= "width : 83%" readonly>
            <button id="postcode" @click="execDaumPostcode" style="margin-left:23px">검색</button><br/>
            <input class="input_txt" type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
            <input class="input_txt" type="text" v-model="detailAddress" placeholder="상세주소"><br/>
            <input class="input_txt" type="text" v-model="extraAddress" placeholder="참고항목"><br/>
          </div>
          
            <button v-on:click="joincheck" style ="padding:5px 20px 5px 20px; width:100%; margin-top:60px">가입</button>
          
        </div>
    </div>
  </template>
 
 <script>

export default {
   
  name: 'MemJoin',
  data () {
   
    return {
      
      
      id:'',
      pwd:'',
      isPwdCheckEqual:'',
      name:'',
      email:'',
      emailCode:'',
      isEmailCodeCheck:false,
      emailtf:false,
      pwdcheck:'',
      birth:'',
      gender:'m',
      phone:'',
      isPhoneCheck:false,
      postcode:'',
      roadAddress:'',
      detailAddress:'',
      extraAddress:'',
      address:'',
      msg:'',
      tf:false,
      
      idHasError:false,
      nameHasError:false,
      pwdHasError:false,
      pwdcheckHasError:false,
      emailHasError:false,
      emailCodeHasError:false,
      phoneHasError:false
   
    }

    
   
  },

 
  watch : {
     
    'name': function(){
        this.checkName()
    },
    'email': function(){
        this.checkEmail()
    },
    'pwd' : function(){
        this.checkPwd()
    },
   

    },
  


 
  methods:{

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

    emailCodeCheck(){
        
        const self = this;

        alert(self.emailCode)
        alert(self.confirm)
  
        if(self.confirm === self.emailCode){
            alert('인증완료')
            this.isEmailCodeCheck=false;
            self.emailCodeHasError=false;
            self.emailtf=true;
            
            document.getElementById("email").readOnly = true;
            document.getElementById("emailCode").readOnly = true;
      
        }else{
            this.isEmailCodeCheck=true;
            self.emailCodeHasError=true;
            self.emailtf=false;
            
        }

    },

    //회원가입 필수항목 검사
    joincheck(){
      const self = this;
      if(this.isEmpty(self.id)&&this.isEmpty(self.name)&&this.isEmpty(self.pwd)&&this.isEmpty(self.pwdcheck)&&self.emailtf 
      &&this.isEmpty(self.birth)&&this.isEmpty(self.phone)&&this.isEmpty(self.postcode)){
 
       this.join()
      return true
      }else{
  
       
        alert('필수 항목 기입 확인 필요')
        return false;
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

    join(){
      
      const self = this;
      const moment = require('moment');
      const mybirth = moment(self.birth).format('L');
     
      var address = self.roadAddress
      address += self.detailAddress 
      if(self.extraAddress != ''){
      address += "," + self.extraAddress
      }

      let formdata = new FormData();
      formdata.append('id',self.id)
      formdata.append('pwd',self.pwd)
      formdata.append('name',self.name)
      formdata.append('email',self.email)
      formdata.append('gender',self.gender)
      formdata.append('birth',mybirth)
      formdata.append('phone',self.phone)
      formdata.append('address', address)

      alert('파일' + document.getElementById('profile').value)
     if(document.getElementById('profile').value !== ''){
        const file = document.getElementById('profile').files[0]
       
        formdata.append('f', file)}
        console.log(this.f)
        
  
     
      self.$axios.post('http://localhost:8082/members', formdata,
      {headers:{"Content-Type":"multipart/form-data"}}) 
      .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
        alert(dto.id +'님 회원가입 완료')
         window.location.href = "/"
        }else{
          alert('에러코드:' + res.status)
        }
      });
    },
  
  idcheck(){
    const self = this;
    const validateId = /^[a-zA-Z0-9]+$/;
    self.$axios.get('http://localhost:8082/members/'+self.id)
    .then(function(res){ //
        if(res.status == 200){
        
          if(res.data.dto == null){
            if(!validateId.test(self.id)){
            self.msg='영문과 숫자만 가능합니다'
            self.idHasError = true;
            }else{
                self.msg='사용가능한 아이디'
                self.idHasError = false;
            }
          
          }else{
             self.msg='중복된 아이디'
             self.id=''
             self.idHasError = true;
          }
        }else{
          alert('에러코드 :' + res.status)
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
 

    checkName(){
      const self = this;
      const validateName = /^[가-힣a-zA-Z]+$/;
         if(!validateName.test(this.name)){
    
        this.isNameCheck = true;
        self.name='';
        self.nameHasError=true;
    }else{
        this.isNameCheck = false;
        self.nameHasError=false;
    }
    },

    checkPwd(){
        const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
    if(!validatePassword.test(this.pwd)){
    
        this.isPwdCheck = true;
        document.getElementById("pwdcheck").readOnly = true;
        this.pwdHasError = true;
    }else{
        this.isPwdCheck = false;
        document.getElementById("pwdcheck").readOnly = false;
        this.pwdHasError = false;
    }
  },

  checkPwdEqual(){
    const self = this;
    console.log(this.pwd + "/" + this.pwdcheck)
    if(this.pwd === this.pwdcheck){
      this.isPwdCheckEqual = false;
      self.pwdcheckHasError=false;
      
    }else{
      this.isPwdCheckEqual=true;
      console.log(self.pwdcheck)
      self.pwdcheck = '';
      self.pwdcheckHasError = true;
      
    }

  },

  checkEmail(){
    
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/
     
    if(!validateEmail.test(this.email)){
        this.isEmailCheck = true;
        this.emailHasError=true;
       
    }else{
        this.isEmailCheck = false;
        this.emailHasError=false;
        
    }
  
  },

  checkPhone(){
    const self = this;
    const validatePhone = /^\d{3}-\d{3,4}-\d{4}$/;
    if(!validatePhone.test(this.phone)){
        self.isPhoneCheck = true;
        self.phone='';
        self.phoneHasError=true;
    }else{
        self.isPhoneCheck = false;
        self.phoneHasError = false;
    }
  },

  
  
 execDaumPostcode(){
 
    

    new window.daum.Postcode({
        oncomplete:(data) =>{

            var addr='';
            var extraAddr='';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
            // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

            // 조합된 참고항목을 해당 필드에 넣는다.
                    this.extraAddress = extraAddr;
                
                } else {
                    this.extraAddress = '';
                }    
                
          
            this.postcode = data.zonecode;
            this.roadAddress = addr;

       
        },
        
    }).open();
    
    },

 
}
}


</script>

<style scoped>

.join_area {
    margin: 0 auto;
    padding: 58px 0 160px;
    width: 400px
}

.join_title {
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

.input_box input[type=radio]{
    display: none;
}

.input_box input[type=radio]+label{
    display: inline-block;
    cursor: pointer;
    height: 24px;
    width: 90px;
    border: 1px solid #a2a2a2;
    line-height: 24px;
    text-align: center;
    font-weight:bold;
    font-size:13px;
}

.input_box input[type=radio]+label{
    background-color: #fff;
    color: rgb(255, 214, 91);
}
.input_box input[type=radio]:checked+label{
    background-color: rgb(255, 214, 91);
    color: #fff;
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

input::placeholder {
  color: rgb(209, 209, 209);
  
}

input[type='date']::before {
  content: attr(data-placeholder);
  width: 100%;
}

input[type='date']:focus::before,
input[type='date']:valid::before {display: none;}
input[data-placeholder]::before{
  color:rgb(209, 209, 209);
  font-size:15px;
  padding-left:33px;
}
input[type='date']{
  position:relative;
  width:160px;
  height:37px;
  padding-left:12px;
  border:1px solid #e1e1e1;
  border-radius:10px;
}
input[type='date']::-webkit-clear-button,
input[type='date']::-webkit-inner-spin-button{display:none;}
input[type='date']::-webkit-calendar-picker-indicator{
  position:absolute;
  left:0;
  top:0;
  width:100%;
  height:100%;
  background: transparent;
  color:transparent;
  cursor:pointer;
}
   

  </style>