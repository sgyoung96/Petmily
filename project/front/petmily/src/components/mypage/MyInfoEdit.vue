<template>
  <div>
    <div class="container">
        <div class="list">
            <ul class="box-ul">
                <li class="list-li">
                    <div>
                        <p class="header-profile">PROFILE</p>
                        <img @click="changeProfileImg" id="preview" @error="replaceImg" :src="'http://localhost:8082/members/imgs/'+ id">                    
                        <input type="file" id="profile" @change="previewFile">
                        <label class="announce-profile">프로필 사진을 수정하려면 클릭하세요.</label>
                    </div>
                </li>

                <li class="list-li">
                    <div>
                        <p>BASE INFO</p>
                        <br>
                        <table>
                            <tr>
                                <th>
                                    <label>아이디</label>
                                </th>
                                <td>
                                    <label>{{this.id}}</label>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>이름</label>
                                </th>
                                <td>
                                    <label id="lbl_name" @click="editName">{{this.name}}</label>
                                    <input id="ipt_name" type="text" v-model="this.name" />
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>생일</label>
                                </th>
                                <td>
                                    <label id="lbl_birth" @click="editBirth">{{this.birth}}</label>
                                    <input id="ipt_birth" type="date" v-model="birth" data-placeholder='생일입력' required aria-required="true">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>성별</label>
                                </th>
                                <td>
                                    <label id="lbl_gender" @click="editGender">{{this.gender}}</label>
                                    <div id="ipt_gender" class="input_box">  
                                        <input name="g" type="radio" v-model="gender" id="m" value="m"><label for="m" style="margin-right:10px">남</label>
                                        <input name="g" type="radio" v-model="gender" id="f" value="f"><label for="f">여</label><br/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>휴대폰</label>
                                </th>
                                <td>
                                    <label id="lbl_phone" @click="editPhone">{{this.phone}}</label>
                                    <input id="ipt_phone" type="text" v-model="phone" @blur="checkPhone"/>
                                    <p id="warn_phone" >전화번호 형식을 확인해주세요</p>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>주소</label>
                                </th>
                                <td>
                                    <label id="lbl_address" @click="editAddress">{{this.address}}</label>
                                    <div id="ipt_address">   
                                        <input type="text" v-model="postcode" @click="execDaumPostcode" placeholder="우편번호" readonly>
                                        <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
                                        <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
                                        <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </li>

                <li class="list-li email">
                    <div>
                        <p>EMAIL AUTH</p>
                        <br>
                        <table>
                            <tr>
                                <th colspan="2">
                                    <p>이메일 변경</p>
                                </th>
                            </tr>
                            <tr>
                                <th>
                                    <label>NOW</label>
                                </th>
                                <td>
                                    <div>
                                        <label class="raw-email">{{this.email}}</label>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th>
                                    <label>NEW</label>
                                </th>
                                <td>
                                    <div>
                                        <input id="new_email" type="text" placeholder="변경할 이메일" /><label @click="emailcheck" class="btn-email">인증</label>
                                        <br>
                                        <input id="email-auth" type="text" placeholder="인증번호" /><label @click="emailCodeCheck" class="btn-email">확인</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </li>


                <li class="list-li password">
                    <div>
                        <p>PASSWORD</p>
                        <br>
                        <table>
                            <tr>
                                <td>
                                    <div class="box-password">
                                        <p>비밀번호 변경</p>
                                        <input id="pwdcheck" class="ipt-pw" v-model="pw" type="password" placeholder="새 비밀번호" :readonly="isReadonly" /><br>
                                        <label id="warn_pw" class ="ipt-error" >영문, 숫자, 특수문자 8~16문자</label><br>  
                                        <input id="newpwdcheck" v-model="new_pw" class="ipt-pw" type="password" placeholder="새 비밀번호 확인" :readonly="isReadonly" /><br>
                                        <label id="warn_pw_chk" class ="ipt-error" >비밀번호가 일치하지 않아요</label><br> 
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </li>

            </ul>
        </div>
        <br>

        <div>
            <button v-on:click="edit">내정보 수정</button>
        </div>
      </div>
  </div>
</template>

<script>

export default {
  name: 'MyInfoEdit',
  data () {
    return {
        id : sessionStorage.getItem('loginId'),
        img_upload_img: '',
        name: '',
        email: '',
        birth: '',
        gender: '',
        phone: '',
        address: '',

        pw: '',
        rawpw: '',
        new_pw: '',

        postcode: '',
        roadAddress: '',
        detailAddress: '',
        extraAddress: '',


        isPwdCheck: true,
        isPwdCheckEqual: true,

        isReadonly: false,
    }
  },
  watch:{
    'pw' : function(){
        this.checkNewPwd();
    },
    'new_pw' : function () {
        this.isCheckNewPwd();
    },
    'phone': function() {
        this.checkPhone();
    }
  },
  created: function () {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
        const moment = require('moment');
        //let token = sessionStorage.getItem('token');
        const self = this;
        this.$axios.get('http://localhost:8082/members/' + this.id)
        .then(function(res){
            if (res.status == 200) {
                let dto = res.data.dto
                if (dto != null) { 
                    //this.id = dto.id
                    self.rawpw = dto.pwd
                    console.log(self.rawpw);
                    self.name = dto.name
                    self.email = dto.email
                    self.birth = moment(dto.birth).format('L');
                    self.gender = dto.gender
                    self.phone = dto.phone
                    self.address = dto.address

                    self.setBaseInfo();
                } else {
                    alert('없는 아이디 이거나 만료된 토큰')
                }
            } else {
                alert('에러코드 :' + res.status)
            }
        });
    },
    setBaseInfo() {
        console.log(this.name);
        document.getElementById('warn_pw').style = 'display: none;';
        document.getElementById('warn_pw_chk').style = 'display: none;';

        if (sessionStorage.getItem('loginFlag') == 'kakao') {
            this.isReadonly = true;
        } else {
            this.isReadonly = false;
        }
    },
    changeProfileImg() {
        const ipt_file_upload = document.getElementById('profile');
        ipt_file_upload.click();
    },
    previewFile() {
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

    checkNewPwd(){
        const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
        if(!validatePassword.test(this.pw)){
            document.getElementById('warn_pw').style = "display: block;";
            document.getElementById("newpwdcheck").readOnly = true;
            document.getElementById('warn_pw_chk').style = 'display: none;';
        } else {
            document.getElementById("newpwdcheck").readOnly = false;
            document.getElementById('warn_pw').style = 'display: none;';
            document.getElementById('warn_pw_chk').style = 'display: none;';
        }
    },
    isCheckNewPwd() {
        if (this.pw == this.new_pw) {
            document.getElementById('warn_pw_chk').style = 'display: none;';
        } else {
            document.getElementById('warn_pw_chk').style = 'display: block;';
        }
    },
    
    emailcheck(){
        const self = this;

        self.$axios.get('http://localhost:8082/members/email/'+ document.getElementById('new_email').value)
        .then(function(res){ 
            if(res.status == 200){
                if(res.data.dto == null){
                    self.sendEmail()
                } else {
                    alert('중복된 이메일')
                }
            } else {
                alert('에러코드 :' + res.status)
            }
        });
    },
    sendEmail(){
        const self = this;

        let formdata = new FormData();
        formdata.append('email', document.getElementById('new_email').value)
        self.$axios.post('http://localhost:8082/emailConfirm', formdata)
        .then(function(res){ 
        if(res.status == 200){
            let confirm = res.data.confirm
            alert(confirm);
            alert('메일 전송 완료');
        } else {
            alert('오류')
        }
      });

     },
    emailCodeCheck(){
        const self = this;
      
        if (self.confirm === self.emailCode){
            alert('인증완료')
        } else{
            alert('인증을 다시 시도해주세요');
        }
    },
    editName () {
        document.getElementById('lbl_name').style = 'display: none';
        document.getElementById('ipt_name').style = 'display: block';
    },
    editGender() {
        document.getElementById('lbl_gender').style = 'display: none;';
        document.getElementById('ipt_gender').style = 'display: block';
    },
    editBirth() {
        document.getElementById('lbl_birth').style = 'display: none;';
        document.getElementById('ipt_birth').style = 'display: block';
    },
    editPhone() {
        document.getElementById('lbl_phone').style = 'display: none;';
        document.getElementById('ipt_phone').style = 'display: block';
    },
    checkPhone(){
        const validatePhone = /^\d{3}-\d{3,4}-\d{4}$/;
        if(!validatePhone.test(this.phone)){
            document.getElementById('warn_phone').style = 'display: block';
        }else{
            document.getElementById('warn_phone').style = 'display: none';
        }
    },
    editAddress() {
        document.getElementById('lbl_address').style = 'display: none;';
        document.getElementById('ipt_address').style = 'display: block;';
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
    edit(){
        
      const self = this;
      const moment = require('moment');
      const mybirth = moment(self.birth).format('L');
     

      var address = '';
      if (self.roadAddress != '') {
          address = self.roadAddress;
          if (self.extraAddress != '') {
            address += "," + self.extraAddress;
          }
       } else {
           address = self.address;
       }

      let formdata = new FormData();
      formdata.append('id',self.id)

      //alert(self.rawpw);

      if (self.new_pw != null) {
          formdata.append('pwd',self.rawpw);
      } else {
          formdata.append('pwd',self.new_pw);
      }

      formdata.append('name',self.name)
      formdata.append('email',self.email)
      formdata.append('gender',self.gender)
      formdata.append('birth',mybirth)
      formdata.append('phone',self.phone)
      formdata.append('address', address)

      //alert('파일' + document.getElementById('profile').value)
      if(document.getElementById('profile').value !== ''){
        const file = document.getElementById('profile').files[0]
       
        formdata.append('f', file)}
        console.log(this.f)
     
      self.$axios.put('http://localhost:8082/members', formdata,
      {headers:{"Content-Type":"multipart/form-data"}}) 
      .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
          console.log(dto);
           alert('회원정보가 수정되었습니다.');
           location.href = '/member/mypage/home';
        }else{
          alert('에러코드:' + res.status)
        }
      });
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
    color: black;
}

.list {
    display: flex;
    justify-content: center;
    border: 2px solid #eee;
    border-radius: 20px;
    padding-top: 20px;
    padding-bottom: 20px;
}

ul {
    list-style: none;
    list-style-type: none;
    justify-content: space-evenly;
}

li {
    font-size: 11px;
    cursor: unset;
}

li:hover {
    font-size: 11px;
    cursor: unset;
}

.list-li {
    float:left;
}

.header-profile {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 20px;
}

.list-li div table tr td {
    width: 230px;
    padding-left: 10px;
    padding-right: 10px;
}

.list-li div table tr th label {
    color: black;
    margin-top: 10px;
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 13px;
}

.list-li div table tr td label {
    color: black;
    margin-top: 10px;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px
}

.list-li div p {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 20px;
}

.list-li  div table tr td p {
    color: black;
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
}

.list-li:hover > div table tr th label {
    color: black;
    margin-top: 10px;
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 13px;
}

.list-li:hover > div table tr td label {
    margin-top: 10px;
    color: black;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px
}

.list-li:hover > div p {
    color: black;
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 20px;
}

.list-li:hover > div table tr td p {
    color: black;
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
}

.txt-email {
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 10px;
    color: black;
}

.list-li .email > .raw-email {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    color: black;
}

.email div table tr th p {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    color: black;
}

.email:hover div table tr th p {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    color: black;
}

.email div table tr td div .raw-email {
    font-family: 'IBMPlexSansKR-SemiBold';
    font-size: 13px;
    color: black;
}

.email > div table tr td {
    width: 230px;
}

.email > div table tr td input {
    width: 150px;
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 12px;
    margin-left: 10px;
}

.email > div table tr td input::placeholder {
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 12px;
}

.list-li > div table tr td div p {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    color: black;
}

.btn-email {
    margin-left: 10px;
    width: 35px;
    background: white;
    border: 1px solid rgb(244, 191, 79);
    border-radius: 10px;
}

#profile {
    display: none;
}

.announce-profile {
    margin-top: 10px;
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 10px;
    color: black;
}

.announce-profile:hover {
    margin-top: 10px;
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 10px;
    color: black;
}

.header-text {
    text-align: center;
    justify-content: center;
}

.header-edit {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
}

.ipt-pw {
    width: 150px;
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 12px;
    margin-left: 10px;
    margin-top: 10px;
}

.ipt-pw::placeholder {
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 12px;
}

.box-password > .ipt-error {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

.box-ul .password div table tr td .box-password p .ipt-error {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

.box-ul .password div table tr td .box-password p:hover .ipt-error {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

.box-ul .password .ipt-error {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

.box-ul .password:hover .ipt-error {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

#preview {
    display: block;
    width: 200px;
    height: 200px;
    border-radius: 70%;
    overflow: hidden;
}

#preview, #lbl_name, #lbl_gender, #lbl_birth, #lbl_phone, #lbl_address {
    cursor: pointer;
}

#ipt_name {
    margin-top: 10px;
    border: none;
    border-bottom: 1px solid;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
    text-align: center;
    display: none;
    margin-left: 40px;
    margin-right: 40px;
}

#ipt_gender {
    display: none;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
}

#lbl_gender {
    width: 70px;
    justify-content: center;
}

#ipt_birth {
    display: none;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
    margin-top: 10px;
    margin-left: 50px;
    margin-right: 50px;
}

#ipt_phone {
    display: none;
    margin-top: 10px;
    border: none;
    border-bottom: 1px solid;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
    text-align: center;
    display: none;
    margin-left: 40px;
    margin-right: 40px;
}

#warn_phone {
    display: none;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 8px;
}

#ipt_address {
    display: none;
    margin-left: 20px;
    margin-right: 20px;
    justify-content: center;
    padding-left: 20px;
    padding-right: 20px;
}

#ipt_address input {
    display: block;
    border: none;
    border-bottom: 1px solid;
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
    text-align: center;
    width: 100%;
}

#ipt_address > input::placeholder {
    font-family: 'IBMPlexSansKR-Light';
    font-size: 12px;
}

button {
    background-color: rgb(244, 191, 79);
    color: white;
    font-family: 'IBMPlexSansKR-Bold';
    width: 100px;
    height: 50px;
    font-size: 15px;
    border: 3px solid rgb(244, 191, 79);
    border-radius: 20px;
}

</style>
