<template>
    <div class="hello">
      <h1>입양 신청 양식폼</h1>
        <h3>Apply form</h3>
        id:<input type="text" v-model="id"><br/>
        wdate:<input type="text" v-model="wdate"><br/>
        agreement:<input name="age" type="radio" v-model="agreement" value="0">미동의 /
        <input name="ag" type="radio" v-model="agreement" value="1">동의<br/>
        another:<input type="text" v-model="another"><br/>
        reason:<input type="textarea" v-model="reason"><br/> 
        feeding:<input type="textarea" v-model="feeding"><br/>
        <button v-on:click="apply">신청</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ApplyForm',
    data () {
    return {
      id:'',
      wdate:'',
      agreement:0,
      another:'',
      reason:'',
      feeding:'',
      ischeck:0
    }
  },
  methods:{
    join(){
      const self = this;
      let formdata = new FormData();
      formdata.append('id', self.id)
      formdata.append('wdate', self.wdate)
      formdata.append('agreement', self.agreement)
      formdata.append('another', self.another)
      formdata.append('reason', self.reason)
      formdata.append('feeding', self.feeding)
      formdata.append('ischeck', self.ischeck)
      
      //self.$axios.post('http://localhost:8082/members', {id:self.id, pwd:self.pwd, name:self.name, email:self.email, type:self.type}, { headers: { 'Content-Type': 'application/json' } })
      self.$axios.post('http://localhost:8082/Applyform', formdata)//비동기 요청
      .then(function(res) {//요청 결과 받아옴. 파람 res에 결과저장됨. res.data가 백단에서 전송한 데이터
        if(res.status == 200){
          let dto = res.data.dto
          alert(dto.id)
        }else{
          alert('에러코드:'+res.status)
        }
      });
    },
    apply(){
      const self = this;
      self.$axios.post('http://localhost:8082/Applyform')//비동기 요청
      .then(function(res) {//요청 결과 받아옴. 파람 res에 결과저장됨. res.data가 백단에서 전송한 데이터
        if(res.status == 200){
          if(res.data.dto==null){
            self.msg = '신청되었습니다'
          }else{
            self.msg = '신청을 보낸 상태입니다'
            self.id = ''
          }
        }else{
          alert('에러코드:'+res.status)
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
  