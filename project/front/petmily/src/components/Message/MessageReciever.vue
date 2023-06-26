<template>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

  <div id="messagereciever">
        <h2>{{loginId}}가 받은 쪽지 목록</h2>
    <div v-for="message in list" :key="message.num">
        보낸사람 : {{message.sender.id}}<br/>
        보낸날짜 : {{message.send_dt}}<br/>
        제목 : <a v-on:click="$event => detail(message.num,message.sender.id,message.send_dt,message.title,message.content)">{{message.title}}</a><br/>
      <div v-if="message.check == 0">
        <span class="material-symbols-outlined">mail</span><br/>
      </div>
      <div v-else>
        <span class="material-symbols-outlined" >drafts</span>
      </div>
        <span class="material-symbols-outlined" @click="del(message.num)">delete</span>
    </div>

    <!-- 모달창 -->
    <div class="black-bg" v-if="modalOpen === true">
        <div class="white-bg">
            <h3>{{num}} title : {{title}}</h3>
            <p>보낸사람 : {{sender}}</p>
            <p>받은날짜 : {{senddt}}</p>
            <p>내용 : {{content}}</p>
            <button v-on:click="read(num)" class="modal-exit-btn">
            확인
            </button>
        </div>
    </div>
  
     
  </div>
</template>

<script>
export default {
  name: 'MessageReciever',
  data () {
    return { 
     loginId:null,
     list:[],
     modalOpen:false,
     num:0,
     title:'',
     sender:'',
     senddt:'',
     content:''
    
    }
  },
  
  created:function(){ 
    
     this.loginId = sessionStorage.getItem('loginId')
     const self = this;
     self.$axios.get('http://localhost:8082/message/reciever/' + self.loginId)
      .then(function(res){
        if(res.status == 200){
         self.list = res.data.list
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
  },
  methods:{
    del(num){
        alert('삭제버튼 클릭')
        this.loginId = sessionStorage.getItem('loginId')
        const self = this;
            alert(num)
        self.$axios.delete('http://localhost:8082/message/reciever/' + num)
      .then(function(res){
        if(res.status == 200){
         alert('삭제완료')
         self.$axios.get('http://localhost:8082/message/reciever/'+ self.loginId).then(function(res){
        if(res.status == 200){
         self.list = res.data.list
        }else{
          alert('에러코드 :' + res.status)
        }
      });   
         
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
    
    },
    detail(num,sender, senddt, title, content){
        this.num = num;
        this.sender = sender;
        this.title = title;
        this. senddt = senddt;
        this.content = content;
        this.modalOpen = true;

        
    
    },
    read(num){
        const self = this; 
        alert(num)
        self.$axios.patch('http://localhost:8082/message/' + num)
        .then(function(res){
        if(res.status == 200){
         alert('읽음')
         self.$axios.get('http://localhost:8082/message/reciever/'+ self.loginId).then(function(res){
        if(res.status == 200){
         self.list = res.data.list
        }else{
          alert('에러코드 :' + res.status)
        }
      });   
        }else{
          alert('에러코드 :' + res.status)
        }
      }); 
        this.modalOpen = false;

    }
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
  background-color: rgba(0, 0, 0, 0.432);
  position: fixed;
  top: 0;
  left: 0;
  padding: 20px;
}

.white-bg {
  width: 50%;
  background-color: white;
  padding: 20px;
  border-radius: 8px;
}

.modal-exit-btn {
  margin-top: 30px;
}

.modal-exit-btn:hover {
  cursor: pointer;
}
</style>
