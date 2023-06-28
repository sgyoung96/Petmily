<template>
    <div class="hello">
      <h1>입양 신청 양식폼</h1>
        <h3>Apply form</h3>
        id:<input type="text" v-model="id" readonly><br/>
        wdate:<input type="text" v-model="wdate" readonly><br/>
        agreement:<input name="age" type="radio" v-model="agreement" value="0">미동의 /
        <input name="ag" type="radio" v-model="agreement" value="1">동의<br/>
        another:<input type="text" v-model="another"><br/>
        reason:<input type="textarea" v-model="reason"><br/> 
        feeding:<input type="textarea" v-model="feeding"><br/>
        <button v-on:click="apply()">신청</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ApplyForm',
    data () {
    return {
      id: sessionStorage.getItem('loginId'),
      wdate: '',
      agreement:0,
      another:'',
      reason:'',
      feeding:'',
      ischeck:0,
      applyPetCd: this.$route.query.applyPetCd
    }
  },
  created: function() {
    this.nowTimes();
  },
  methods:{
    apply(){
      const self = this;

      let formdata = new FormData();

      const moment = require('moment');
      const today = moment(Date.now()).format('L');
      console.log(today);

      formdata.append('id', sessionStorage.getItem('loginId'));
      formdata.append('wdate', today);
      formdata.append('agreement', self.agreement);
      formdata.append('another', self.another);
      formdata.append('reason', self.reason);
      formdata.append('feeding', self.feeding);
      formdata.append('ischeck', self.ischeck);
      formdata.append('petCd', self.applyPetCd);

      self.$axios.post('http://localhost:8082/Applyform', formdata)//비동기 요청
      .then(function(res) {//요청 결과 받아옴. 파람 res에 결과저장됨. res.data가 백단에서 전송한 데이터
        if(res.status == 200){
          if(res.data.dto != null){
            self.msg = '신청되었습니다'
          } else {
            self.msg = '신청을 보낸 상태입니다'
          }
        }else{
          alert('에러코드:'+res.status)
        }
      });
    },
    setDate() {
          let year = new Date().getFullYear();
          let month =new Date().getMonth() + 1 < 10? "0" + (new Date().getMonth() + 1): new Date().getMonth() + 1;
          let date =new Date().getDate() < 10? "0" + new Date().getDate(): new Date().getDate();
          let hh =new Date().getHours() < 10? "0" + new Date().getHours(): new Date().getHours();
          let mm =new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
          let ss =new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
      return {
            'year' : year
          , 'month' : month
          , 'date' : date
          , 'hh' : hh
          , 'mm' : mm
          , 'ss' : ss
      }
    },
    nowTimes() {
      this.wdate = this.setDate().year + "-" + this.setDate().month + "-" + this.setDate().date + " " +
                   this.setDate().hh + ":" + this.setDate().mm + ":" + this.setDate().ss;
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
  