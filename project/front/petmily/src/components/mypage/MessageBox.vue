<template>
  <div>
    <div class="container">
      <div class="msgbox_area">
     
        
        <div class="mbtns">
         
         <button class="mbtn" :class="{ active: showMessageReciever }"  @click="reciever">받은 쪽지<span v-if="cnt !== 0"  class="badge">{{cnt}}</span></button>
         <button class="mbtn" :class="{ active: showMessageSender }"  @click="sender">보낸쪽지</button>
         <button class="mbtn" :class="{ active: showMessageWrite }" @click="send">쪽지보내기</button>
        </div>
        <div v-if="showMessageReciever">
         <MessageReciever @new-cnt ="newcnt" id="reciever"/> 
         
        </div>
        <div v-if="showMessageSender">
          <MessageSender id="sender"/> 
        </div>

         
          <MessageWrite id="send" v-if="showMessageWrite" @close="showMessageWrite=false"/> 
        
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
   
    },
    sender(){
      this.showMessageReciever = false;
      this.showMessageSender = true;
    },
    send(){
      this.showMessageWrite = true;
     
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
.black-bg {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.668);
  position: fixed;
  top: 0;
  left: 0;
  padding: 20px;
  z-index: 9999;
 
 
}
.container {
    padding-top: 50px;
    padding-bottom: 100px;
}

.mbtns {
  width: 400px;
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  margin-bottom: 60px;
  margin-left: auto;
  margin-right: auto;
 
}

.mbtn{
  position:relative;
  border:1px solid  #cfcfcf;
  border-radius: 20px;
  padding:6px;
  color:#7a7a7a;
  background-color: white;
  font-family: "IBMPlexSansKR-Medium";
}

.mbtn.active{
  color:  rgb(156, 156, 39);
  font-weight: bold;
  background-color: white;
  border:0px;
  font-family: "IBMPlexSansKR-bold";
 
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
