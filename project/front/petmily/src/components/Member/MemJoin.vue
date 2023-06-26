<template>
    <div id="myjoin">
        <h3>join form</h3>
       <input type="file" id="profile"><br/> 
        <input class="input-item" type="text" v-model="id" placeholder="ID" @blur="idcheck" ><br/>
        {{msg}}<br/>
     
      
        <input class="input-item" type="text" v-model="name" placeholder="NAME" @blur="checkName" ><br/>
        <span class ="font_id_red" v-show="isNameCheck">한글 또는 영어만 허용합니다</span><br/>
        
        <input class="input-item" type="password"  v-model="pwd" placeholder="PWD" ><br/>
         <span class ="font_id_red" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</span><br/>  
        <input class="input-item" type="password" id="pwdcheck"  v-model="pwdcheck" placeholder="PWD 확인" @blur="checkPwdEqual" ><br/>
        <span class ="font_id_red" v-show="isPwdCheckEqual">비밀번호 확인해주세요</span><br/>  

        EMAIL : <input class="input-item" type="text" id="email" v-model="email" placeholder="예)petmily@petmily.co.kr" ><button v-on:click="sendEmail">이메일 확인</button><br/>
         <span class ="font_id_red" v-show="isEmailCheck">이메일 형식을 확인해주세요</span><br/> 
         <input input class="input-item" type="text" id="emailCode" v-model="emailCode"><button v-on:click="emailCodeCheck">인증</button><br/> 
         <span class ="font_id_red" v-show="isEmailCodeCheck">인증코드를 확인해주세요</span><br/> 
       
        
        
        BIRTH : <input  type="date" v-model="birth" ><br/>  
        <input name="g" type="radio" v-model="gender" value="m" >남 / 
            <input name="g" type="radio" v-model="gender" value="f">여 <br/>
        PHONE : <input class="input-item" type="text" v-model="phone" placeholder="예)010-1234-5678" @blur="checkPhone"><br/>
         <span class ="font_id_red" v-show="isPhoneCheck">전화번호 형식을 확인해주세요</span><br/> 
        
        
        <input  type="text" v-model="postcode" placeholder="우편번호" readonly>
        <button id="postcode" @click="execDaumPostcode">검색</button><br/>
        <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
        <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
        <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
       
       <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
          <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" v-on:click="foldDaumPostcode()" alt="접기 버튼">
        </div>
        <button v-on:click="joincheck" >가입</button>
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
      tf:false
      
     
   
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
            self.emailtf=true;
            
            document.getElementById("email").readOnly = true;
            document.getElementById("emailCode").readOnly = true;
      
        }else{
            this.isEmailCodeCheck=true;
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
      const file = document.getElementById('profile')
      formdata.append('f', file.files[0]);
      
     
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
            }else{
                self.msg='사용가능한 아이디'
            }
          
          }else{
             self.msg='중복된 아이디'
             self.id=''
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
    }else{
        this.isNameCheck = false;
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
    console.log(this.pwd + "/" + this.pwdcheck)
    if(this.pwd === this.pwdcheck){
      this.isPwdCheckEqual = false;
      
    }else{
      this.isPwdCheckEqual=true;
      console.log(self.pwdcheck)
      self.pwdcheck = '';
      
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

  
  
  execDaumPostcode(){
    const element_wrap =  this.$refs.wrap;
    

    // 현재 scroll 위치를 저장해놓는다.
    const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

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

            element_wrap.style.display = 'none';
            document.body.scrollTop = currentScroll;
        },
         // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
    }).embed(element_wrap)
    element_wrap.style.display = 'block';
    },

      foldDaumPostcode () {
    const element_wrap =  this.$refs.wrap;
    
      element_wrap.style.display = 'none';
    },

 
}
}


</script>