<template>ssage_area
  <div class="MessageModal">
    <div class="wrapper">
        <div class="container">
            
                <div class="mtitle">
                  <p style="font-weight:bold"> Send Message To {{reciever}} </p>  
                  <hr style="display:flex">
                </div>

                <input type="hidden" v-model="sender">
                <input type="hidden" v-model="reciever">

                <div class="message_box">
                  <input class="input_box" type="text" v-model="title" placeholder="제목">
                  <textarea class="input_txt" v-model="content" cols="30" rows="5" placeholder="message"></textarea>
              
                  <button class="message_botton" style="margin-top:20px" @click="$emit('close')">취소</button>
                  <button class="message_botton" style="background-color:rgb(255, 214, 91);" @click="send">쪽지보내기</button>
                </div> 
           
        </div>
    </div>
  </div>
</template>
<script>
export default{
    name: 'MessageModal',
    props:{
        resender:String
    },
    data(){
        return{
        reciever:this.resender,
        sender:'',
        content:''
        }
    },
    created:function(){
        this.sender = sessionStorage.getItem('loginId')
    },
    methods: {
    send() {
      //쪽지보내기

      if(this.content.length > 0){
      const self = this;
      const form = new FormData();
      form.append("sender", self.sender);
      form.append("reciever", self.reciever);
      form.append("title", self.title);
      form.append("content", self.content);

      self.$axios
        .post("http://localhost:8082/message", form)
        .then(function (res) {
          if (res.status == 200) {
            if (res.data.flag) {
              alert("쪽지 보내기 완료");
              self.$emit('close')
            } else {
              alert("쪽지 보내기 실패");
            }
          } else {
            alert("에러코드 :" + res.status);
          }
        });
      }else{
        alert('메세지를 입력해주세요')
      }

    },
    
  },
   
}

</script>
<style scoped>

.message_box{
  width: 400px;
  margin:0 auto;

}
.container {
  background: #fff;
  width: 40%;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px;
  box-sizing: border-box;
  overflow: hidden;
}

.wrapper {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  max-width: 100%;
  height: 100%;
  top:0;
  left:0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99999;
}
.mtitle{
  width:100%
}
.input_box{
  margin-top:40px;
  width:100%;
   border:none;border-right:0px; border-top:0px; border-left:0px; 
  border-bottom : 1px solid;
  outline: none;
}

.input_txt{
  width:100%;
  margin-top:20px;
   border:none;border-right:0px; border-top:0px; border-left:0px; 
  border-bottom : 1px solid;
  outline: none;
}

input::placeholder {
  color: rgb(209, 209, 209);
  
}

textarea::placeholder {
  color: rgb(209, 209, 209);
  
}
.message_botton{
  width : 100%;
  height: 40px;
  border-radius: 10px;
  border:0px;
  background-color:rgb(222, 222, 222);
  color:white;
  margin-bottom:20px;
  font-weight: bold;
}

</style>

