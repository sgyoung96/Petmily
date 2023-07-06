<template>
  <div class="MessageModal">
    <div class="wrapper">
        <div class="container">
          
        
                <h4> {{reciever}}에게 답장 쪽지 보내기</h4>
                <input type="hidden" v-model="sender">
                <input type="hidden" v-model="reciever">
                제목:<input type="text" v-model="title">
                내용:<textarea class="input_txt" v-model="content" cols="30" rows="5"></textarea>
            
                <button @click="$emit('close')">취소</button>
                <button @click="send">쪽지보내기</button>
           
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

.container {
  background: #fff;
  width: 60%;
  border-radius: 5px;
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
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content{
    padding : 10px 20px;
}
.footer{
    background: #ccc;
    padding: 10px;
    text-align: right;

}
</style>

