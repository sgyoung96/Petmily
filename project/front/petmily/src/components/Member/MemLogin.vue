<template>
    <div id="mylogin">
        <h3>login form</h3>
        id:<input type="text" v-model="id"><br/>
        pwd:<input type="password" v-model="pwd"><br/>
        <button v-on:click="login">로그인</button>
    </div>
  </template>

  <script>
export default {
  name: 'MemLogin',
  data () {
    return {
      id:'',
      pwd:''
    }
  },
  methods:{
    login(){
      const self = this;
      const form = new FormData();
      form.append('id', self.id) // form.append('name이 id인것(폼양식의 이름)', self.id(id의 값))
      form.append('pwd', self.pwd)

      //post(url, 폼데이터)
      self.$axios.post('http://localhost:8082/members/login', form)
      .then(function(res){ 
        if(res.status == 200){
        
          if(res.data.flag){
            sessionStorage.setItem('token', res.data.token)
            sessionStorage.setItem('loginId', self.id)
            sessionStorage.setItem('type', res.data.type)
            // self.$router.push('/') location.href와 비슷 강사님 코드
           window.location.href = "/"
            alert('로그인')
          }else{
             alert('로그인실패')
          }
        }else{
          alert('에러코드 :' + res.status)
        }
      });
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
