<template>
  <div class="additional-form-container">
    <div class="form-header">
        <div><span class="form-header-title-msg">서비스 이용을 위해 추가 정보를 기입해 주세요.</span></div>
        <span class="form-header-sub-msg">SNS 로그인시 원활한 서비스 이용을 위해 최초 1회만 작성합니다.</span>
    </div>


    <div class="join_area">
      <!-- 프로필사진 -->
      <div class="input_box">
        <div class="profileimg">
          <div class="box-profile">
            <div class="profile" id="preview" ></div>
          </div>
        </div>

          <div class="file-upload">
          <input type="text" id="profilename" class="upload-name" placeholder="파일선택" disabled="disabled">
          <label for="profile">업로드</label> 
          <input type="file" id="profile" class="upload-hidden" @change="previewFile"><br/> 
        </div>
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
  name: 'KakaoAdditionalForm',
  data () {
    return {

      kakaoId: this.$route.query.kakaoId,
      kakaoName: this.$route.query.kakaoName,
      loginFlag: this.$route.query.loginFlag,
      name: this.$route.query.kakaoName,

      email:'',
      emailCode:'',
      isEmailCodeCheck:false,
      emailtf:false,

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
      
      emailHasError:false,
      emailCodeHasError:false,
      phoneHasError:false,
      previewImage: require('@/assets/imgs/mypage_sample.jpg')
  

    }
  },
  created: function () {
    const self = this;
    if (sessionStorage.getItem('loginFlag') == 'kakao') {
      self.name = sessionStorage.getItem('kakaoName');
    }
  },
  mounted: function() {
    const previewDiv = document.getElementById('preview');
    const img = new Image();
    img.src = this.previewImage;

    img.onload = function() {
      previewDiv.innerHTML = '';
      previewDiv.appendChild(img);
    };
  },
  watch : {
   'email': function(){
      this.checkEmail();
    },
    'phone': function() {
      this.checkPhone();
    }
  },  
  methods:{
    previewFile(){
      const fileInput = document.getElementById('profile');
      const previewDiv = document.getElementById('preview');
      const profilename = document.getElementById('profilename');
      

      // 파일이 선택되지 않았을 경우 미리보기를 초기화
      if (!fileInput.files || fileInput.files.length === 0) {
        const img = new Image();
        img.src = this.previewImage;

        img.onload = function() {
        previewDiv.innerHTML = '';
        previewDiv.appendChild(img);
        profilename.value = '파일선택';
      }
      
      return;
      
    }
    // 첫 번째 파일을 가져옵니다.
    const file = fileInput.files[0];

    // 이미지 파일인지 확인
    if (file.type.startsWith('image/')) {
      const reader = new FileReader();
    
      // 파일 로드 완료 시 미리보기를 생성
      reader.onload = function(event) {
      const img = document.createElement('img');
      img.src = event.target.result;

      img.classList.add('profile');
      previewDiv.innerHTML = '';
      previewDiv.appendChild(img); 
      
      profilename.value = file.name;
    };
      

    // 파일을 읽어옵니다.
    reader.readAsDataURL(file);

     
  } else {
    
    // 이미지 파일이 아닌 경우 미리보기를 초기화합니다.
    previewDiv.innerHTML = '';
    previewDiv.style.backgroundImage = `url(${this.previewImage})`; 
    profilename.value = '파일선택';
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

  emailCodeCheck(){
      
    const self = this;

    // alert(self.emailCode)
    // alert(self.confirm)

    if(self.confirm === self.emailCode){
      alert('인증완료')
      this.isEmailCodeCheck=false;
      self.emailCodeHasError=false;
      self.emailtf=true;
      self.email = document.getElementById('email').value;
      
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
    if(self.emailtf&&this.isEmpty(self.birth)&&this.isEmpty(self.phone)&&this.isEmpty(self.postcode)&&this.isEmpty(document.getElementById('profilename').value)){

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
    
    var address = self.roadAddress + ',';
    address += self.detailAddress 
    if(self.extraAddress != ''){
      address += "," + self.extraAddress
    }

    let formdata = new FormData();
    formdata.append('id',self.kakaoId)
    formdata.append('pwd','')
    formdata.append('name',self.kakaoName)
    formdata.append('email',self.email)
    formdata.append('gender',self.gender)
    formdata.append('birth',mybirth)
    formdata.append('phone',self.phone)
    formdata.append('address', address)

    //alert('파일' + document.getElementById('profile').value)
    if(document.getElementById('profile').value !== ''){
      const file = document.getElementById('profile').files[0]
       
      formdata.append('f', file)
      
    }
    console.log(this.f)
    
    self.$axios.post('http://localhost:8082/members', formdata,
    {headers:{"Content-Type":"multipart/form-data"}}) 
    .then(function(res){ 
      if(res.status == 200){
        let dto = res.data.dto
      alert(dto.name +'님 회원가입 완료')
      window.location.href = "/"
      }else{
        alert('에러코드:' + res.status)
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
        //self.phone='';
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
  },
  components: {
      
  }
  
}
</script>

<style scoped>

/* 전체 div */
.additional-form-container {
    font-family: 'IBMPlexSansKR-Regular';
    padding-bottom: 50px;
}

/* 헤더 div */
.form-header {
    padding-top: 50px;
}

/* 안내문구 1 */
.form-header-title-msg {
    font-family: 'IBMPlexSansKR-Bold';    
    font-size: 20px;
}

/* 안내문구 2 */
.form-header-sub-msg {
    font-family: 'IBMPlexSansKR-ExtraLight';    
    font-size: 12px;
    color: black;
}




.box-profile {
   display: flex;
  justify-content: center;
  align-items: center; /* 이미지를 수직, 수평 가운데로 정렬 */
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
  display: flex; /* 이미지를 가운데 정렬하기 위해 flex 설정 */
  justify-content: center;
  align-items: center;
  
}

.profileimg{
   display:flex;
   justify-content: center;

}
.file-upload{
  margin-top:20px;
}

.file-upload label {
    display: inline-block;
    padding: .5em .75em;
    color: #999;
    font-size: inherit;
    line-height: normal;
    vertical-align: middle;
    background-color: #fdfdfd;
    cursor: pointer;
    border: 1px solid #ebebeb;
    border-bottom-color: #e2e2e2;
    border-radius: .25em;
    margin-left:10px;
}

.file-upload input[type="file"] {  /* 파일 필드 숨기기 */
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip:rect(0,0,0,0);
    border: 0;
}



/* named upload */
.file-upload .upload-name {
    display: inline-block;
    padding: .5em .75em;  /* label의 패딩값과 일치 */
    font-size: inherit;
    font-family: inherit;
    line-height: normal;
    vertical-align: middle;
    background-color: #f5f5f5;
    border: 1px solid #ebebeb;
    border-bottom-color: #e2e2e2;
    border-radius: .25em;
    -webkit-appearance: none; /* 네이티브 외형 감추기 */
    -moz-appearance: none;
    appearance: none;
   
}


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
  border:none;border-right:0px; border-top:0px; border-left:0px; 
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
  color:white;
  font-weight: bold;


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
