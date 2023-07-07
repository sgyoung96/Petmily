<template>


    <div class="findid_area">
      
   

      <h2 class="findid_title">아이디 찾기</h2>

      <div v-show = "info" style="margin-bottom : 30px">
        <p>본인 인증절차가 완료되었습니다. </p>
      </div>

      <div class="input_box" >
        <input class="input_txt" type="text" id="name" v-model="name" placeholder="NAME">
      </div>
      
      <div class="input_box" >
       <input class="input_txt" type="text" id="email"  v-model="email" placeholder="EMAIL">
      </div> 
      
      <div class="input_box" >
       <button class="input_button"  v-on:click="emailcheck" :disabled="findid">이메일 확인</button><br/>
      </div>

      <div class="input_box" v-show="showemail">  
        <input type="text" class="input_code_txt" v-model="emailCode" placeholder="인증코드">
        <button class="input_button" v-on:click="emailCodeCheck">인증</button><br/>
      </div>  
        
      <div v-show="info">  
        <div class="findid" > 회원님의 아이디는 [&nbsp;<span class="myid">{{ id }}</span>&nbsp;] 입니다. </div>
         <div class="findpwd" >비밀번호가 기억나지 않는다면? <span class="findpwdlink"  @click="findpwd">비밀번호 찾기</span></div>
      </div>
       
    </div>
</template>

<script>

export default {
  name: 'FindId',
  data () {
    return {
        id:'',
        name:'',
        email:'',
        info:false,
        showemail:false,
       
    }
  },
  watch:{
   
    'email': function(){
        this.checkEmail()
    }
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
        self.showemail=true;
        document.getElementById("name").readOnly = true;
        document.getElementById("email").readOnly = true;
        
        }else{
          alert('오류')
        }
      });

     },
     emailcheck(){
    const self = this;

    if(self.name == '' || self.email == ''){
          alert('이름과 이메일을 작성해주세요')
          return
        }

    self.$axios.get('http://localhost:8082/members/email/'+self.email)
    .then(function(res){ //
        if(res.status == 200){
        
          if(res.data.dto == null){
           alert('없는 이메일')
           self.email=''
          
          }else{
            let dto = res.data.dto;
            if(dto.name == self.name){
                 
                 self.id = dto.id
                  self.sendEmail()
                 
                 
            }else{
                alert('이름 불일치')
            }
            
             
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
            
         
            self.showemail=false;
            self.info=true;
            

        }else{
            alert('인증코드를 확인해주세요')
          
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
  findpwd(){
     const self = this;
      if (sessionStorage.getItem('loginFlag') != 'normal') {
        self.$router.push('/findpwd'); 
      } else {
        console.log('normal');
      }

  }

   
}
}
</script>

<style scoped>
.findid_area {
    margin: 0 auto;
    padding: 58px 0 160px;
    width: 430px
}

.findid_title {
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
  margin-top:10px;
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

input::placeholder{
  font-size:15px;
  color:rgb(190, 190, 190);
  padding: 10px;
}
.input_button[disabled] {
  background-color: rgb(205, 204, 204);
}

.findid{
  margin-top:60px;
  font-weight: bold;
}

.myid{
  font-size: 20px;
  color: rgb(244, 191, 79)
  
}

.findpwd{
  margin-top:40px;
  color:rgb(190, 190, 190);
}

.findpwdlink{
  color:rgb(255, 214, 91);
  cursor: pointer;
}

</style>

