<template>
  <div>
    <div class="container">
       <div id="memedit">
      <div class="edit_area">

        <h2 class="edit_title">내정보 수정</h2>

          <!-- 프로필사진 -->
          <div class="input_box">
            <span class="box-profile" style="background: #black;"> 
             
              <img class="profile" id="preview" @error="replaceImg" :src="'http://localhost:8082/members/imgs/'+ id">
            </span>
            <input type="file" id="profile" @change="previewFile"><br/> 
          </div>

          <!-- 아이디 -->
          <div class="input_box">
            <h6 class="input_title">아이디</h6><br/>
             <input class="input_txt" type="text" v-model="id" readonly><br/>
          </div>

          <!-- 이름   -->
          <div class="input_box">
            <h6 class="input_title">이름</h6><br/>
            <input class="input_txt" type="text" v-model="name" readonly><br/>
          </div>

          <!-- 비밀번호  -->
          <div class="input_box">
            <h6 class="input_title">비밀번호</h6><br/>
            <input class="input_txt" type="password" id="oldpwd1" v-model="oldpwd1" style= "width : 68%" ><button @click="pwdcheck"  style="margin-left:15px">비밀번호 확인</button><br/>
          </div>

          <div  v-show = "newpwdchecktrue">
          <!-- 새 비밀번호  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': newpwdHasError}">새 비밀번호</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': newpwdHasError}"  type="password" v-model="pwd" ><br/>
            <p class ="input_error" v-show="isPwdCheck">영문, 숫자, 특수문자 8~16문자</p><br/>  
          </div>

          <!--새 비밀번호확인  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': newpwdcheckHasError}">비밀번호확인</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': newpwdcheckHasError}" type="password" id="newpwdcheck" v-model="newpwdcheck"  @blur="checkNewPwdEqual"><br/>
            <p class ="input_error" v-show="isPwdCheckEqual">비밀번호 확인해주세요</p><br/> 
          </div> 
          </div>

          <!-- 이메일확인  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': newemailHasError}">이메일</h6><br/>
            <input input class="input_txt" :class="{ 'input_danger': newemailHasError}"  type="text" id="email" v-model="email" style= "width : 70%"><button v-on:click="emailcheck" style="margin-left:23px">이메일 확인</button><br/>
            <p class ="input_error" v-show="isEmailCheck">이메일 형식을 확인해주세요</p><br/> 
          </div>

          <!-- 이메일인증  -->
          <div class="input_box">
            <h6 class="input_title" :class="{ 'title_danger': newemailCodeHasError}">인증번호</h6><br/>
            <input input class="input_txt" :class="{ 'input_danger': newemailCodeHasError}"  type="text" id="emailCode" v-model="emailCode" style= "width : 83%"><button v-on:click="emailCodeCheck" style="margin-left:23px">인증</button><br/> 
            <p class ="input_error" v-show="isEmailCodeCheck">인증코드를 확인해주세요</p><br/> 
           </div>

          <!-- 생일  -->
          <div class="input_box">   
            <h6 class="input_title">생년월일</h6><br/> 
            <input class="input_txt" type="text" v-model="birth" readonly><br/>
          </div>

           <!-- 성별  -->
          <div class="input_box">  
            <h6 class="input_title">성별</h6>
            <input class="input_txt" type="text" v-model="gender" readonly><br/>
          </div>

           <!-- 전화번호  -->
          <div class="input_box">  
            <h6 class="input_title" :class="{ 'title_danger': newphoneHasError}">전화번호</h6><br/>
            <input class="input_txt" :class="{ 'input_danger': newphoneHasError}" type="text" v-model="phone" @blur="checkPhone"><br/>
            <p class ="input_error" v-show="isPhoneCheck">전화번호 형식을 확인해주세요</p><br/> 
          </div>

          <!-- 주소  -->
          <div class="input_box">
            <h6 class="input_title">주소</h6>    
            <input class="input_txt" type="text" v-model="address" readonly><br/>

          </div>

          <button v-on:click="editcheck" style ="padding:5px 20px 5px 20px; width:100%; margin-top:60px">내정보 수정</button>

      </div>
  </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'MyInfoEdit',
  data () {
    return {
        
    }
  },
  created: function () {
      
  },
  methods: {
    
  },
  components: {
     
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 50px;
    padding-bottom: 100px;
}

</style>
