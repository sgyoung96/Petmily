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
                                    <label>{{this.name}}</label>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>생일</label>
                                </th>
                                <td>
                                    <label>{{this.birth}}</label>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>성별</label>
                                </th>
                                <td>
                                    <label>{{this.gender}}</label>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>휴대폰</label>
                                </th>
                                <td>
                                    <label>{{this.phone}}</label>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>주소</label>
                                </th>
                                <td>
                                    <label>{{this.address}}</label>
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
                                        <input id="new_email" type="text" placeholder="변경할 이메일" /><label class="btn-email">인증</label>
                                        <br>
                                        <input id="email-auth" type="text" placeholder="인증번호" /><label class="btn-email">확인</label>
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
                                        <input class="ipt-pw" type="password" placeholder="새 비밀번호" /><br>
                                        <label class ="ipt-error" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</label><br>  
                                        <input class="ipt-pw" type="password" placeholder="새 비밀번호 확인" /><br>
                                        <label class ="ipt-error" v-show="isPwdCheckEqual">비밀번호가 일치하지 않아요</label><br> 
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
            <button v-on:click="editcheck">내정보 수정</button>
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

        isPwdCheck: true,
        isPwdCheckEqual: true
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
                    console.log(dto);
                    //this.id = dto.id
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
        // 변수 할당된거 화면에 뿌리는 함수
        console.log(this.name);
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
    width: 50px;
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
