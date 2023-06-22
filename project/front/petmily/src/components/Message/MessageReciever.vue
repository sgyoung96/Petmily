<template>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

  <div id="messagereciever">
        <h2>{{loginId}}가 받은 쪽지 목록</h2>
    <div v-for="message in list" :key="message.num">
        보낸사람 : {{message.sender.id}}<br/>
      보낸날짜 : {{message.send_dt}}<br/>
      제목 : {{message.title}}<br/>
      내용 : {{message.content}}<br/>
    <span class="material-symbols-outlined" @click="del(message.num)">delete</span>
    </div>

  
     
  </div>
</template>

<script>
export default {
  name: 'MessageReciever',
  data () {
    return { 
     loginId:null,
     list:[]
    }
  },
  
  created:function(){ //컴포넌트 실행될 때 한번 실행
    
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
        const self = this;
            alert(num)
        self.$axios.delete('http://localhost:8082/message/reciever/' + num)
      .then(function(res){
        if(res.status == 200){
         alert('삭제완료')
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
    
    }
  }
}
</script>
