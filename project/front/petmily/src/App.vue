<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/diaryboardhome">입양일기게시판</router-link> |
    
    <div v-if="loginId==null">
      <router-link to="/join">회원가입</router-link> |
      <router-link to="/login">로그인</router-link>
    </div>
    <div v-else>
      <button v-on:click="logout">로그아웃</button>
    </div>
  </nav>
  <router-view/>
</template>


<script>
export default {
  
  data () {
    return {
      loginId:null,
    }
  },
  created:function(){ // 이 컴포넌트가 시작될때 실행되는 함수
     
      this.loginId = sessionStorage.getItem('loginId')
      
      if(this.loginId == 'admin'){
          this.$router.push('/adminhome')
        }else {
          this.$router.push('/') //router로 이동하면 페이지가 전체 이동하는게 아니라 <router-view/>만 바뀐다
        
     }
  },
  methods:{
    logout(){
      
      sessionStorage.clear()
      window.location.href = "/" //리디렉션

      
    },
    out(){
      
      const self = this;

      let token = sessionStorage.getItem('token')
      self.$axios.delete('http://localhost:8082/members/' + self.loginId, {headers:{'token':token}}) 
      .then(function(res){ 
        if(res.status == 200){
          if(res.data.flag){
          self.logout()
          alert('탈퇴완료')
          }
        }else{
          alert('에러코드:' + res.status)
        }
      });
    }
  }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
