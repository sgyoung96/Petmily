<template>
  <div>
    <div class="container">
        <h1>message box</h1>
        
        <div>
         
         <button class="mbtn" @click="reciever">받은 쪽지<span v-if="cnt !== 0"  class="badge">{{cnt}}</span></button>
         <button class="mbtn" @click="sender">보낸쪽지</button>
         <button class="mbtn" @click="send">쪽지보내기</button>
        </div>
        <div v-if="showMessageReciever">
         <MessageReciever @new-cnt ="newcnt" id="reciever"/> 
         
        </div>
        <div v-if="showMessageSender">
          <MessageSender id="sender"/> 
        </div>

         <div v-if="showMessageWrite">
          <MessageWrite id="send"/> 
        </div>

    </div>
  </div>
</template>

<script>
import MessageReciever from '../Message/MessageReciever.vue'
import MessageSender from '../Message/MessageSender.vue'
import MessageWrite from '../Message/MessageWrite.vue'

export default {
  name: 'MessageBox',
  data () {
    return {
        loginId : sessionStorage.getItem('loginId'),
        showMessageReciever: true,
        showMessageSender:false,
        showMessageWrite:false,
        cnt:0
    }
  },
 
  
  methods: {
    reciever(){
      this.showMessageReciever = true;
      this.showMessageSender = false;
      this.showMessageWrite = false;
    },
    sender(){
      this.showMessageReciever = false;
      this.showMessageWrite = false;
      this.showMessageSender = true;
    },
    send(){
      this.showMessageWrite = true;
      this.showMessageSender = false;
      this.showMessageReciever = false;
    },
    cntcheck(){
       const self = this;
       self.$axios.get('http://localhost:8082/message/cnt/' + self.loginId)
      .then(function(res){
        if(res.status == 200){
         self.cnt = res.data.cnt
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
    },
    newcnt(cnt){
      this.cnt = cnt;
    }
  },
   created: function () {  
    this.cntcheck()
  },
  components: {
    MessageReciever,
    MessageSender,
    MessageWrite
     
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 50px;
    padding-bottom: 100px;
}

.mbtn{
  position:relative;
  border:1.5px solid  rgb(156, 156, 39);
  border-radius: 20px;
  padding:8px;
  color: rgb(156, 156, 39);
  background-color: white;

}

.badge{
  position:absolute;
  top:-12px;
  right:0px;
  color:white;
  background-color: red;
  border-radius: 30px;
}

</style>
