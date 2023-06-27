<template>


    <div id="findid">
        <h3>id 찾기</h3>
        name : <input type="text" v-model="name">
        email : <input type="text" v-model="email"><button v-on:click="emailcheck">이메일 확인</button><br/>
        <input type="text" v-show="emailCodeShow" v-model="emailCode" placeholder="인증코드"><button v-show="emailCodeShow" v-on:click="emailCodeCheck">인증</button><br/>
        <span class ="font_id_red" v-show="isEmailCodeCheck">인증코드를 확인해주세요</span><br/> 
        <span v-show="findid">아이디는 {{id}}입니다.</span>
       
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
        findid:false
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
           alert('없는 이메일')
           self.email=''
          
          }else{
            let dto = res.data.dto;
            if(dto.name == self.name){
                 alert('아이디 알려줌')
                 self.id = dto.id
                 self.findid=true;
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
            this.isEmailCodeCheck=false;
         
            
            document.getElementById("email").readOnly = true;
            document.getElementById("emailCode").readOnly = true;
      
        }else{
            this.isEmailCodeCheck=true;
          
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
  }

   
}
}
</script>

