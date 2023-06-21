<template>


  <div id="messagesender">
        <h2>{{loginId}}가 보낸 쪽지 목록</h2>
    <div>
    
    </div>

  
     
  </div>
</template>

<script>
export default {
  name: 'MessageSender',
  data () {
    return { 
     loginId:null,
     list:[]
    }
  },
  
  created:function(){ //컴포넌트 실행될 때 한번 실행
    
     this.loginId = sessionStorage.getItem('loginId')
     const self = this;
     self.$axios.get('http://localhost:8082/sender/' + self.loginId)
      .then(function(res){
        if(res.status == 200){
         self.list = res.data.list
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
  },
  methods:{
    detail(num){
       this.$router.push({name:'ShopDetail', query:{num:num}})

    }
    
}
}
</script>
