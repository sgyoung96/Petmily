<template>
  <div>
      <h3>hi</h3>
      <router-link to="/diaryboardadd">글쓰기</router-link>
     <table border="1">
          <tr><th>pic</th><th>num</th><th>title</th><th>content</th><th>w_date</th><th>id</th></tr>
          <tr v-for="prod in list" :key="prod.num">
          <td><img :src="'http://localhost:8082/dboard/imgs/' + prod.num + '/1'"></td>
          <td>{{ prod.num }}</td>
          <td><a v-on:click="$event=>detail(prod.num)">{{ prod.title }}</a></td>
          <td>{{ prod.content }}</td>
          <td>{{ prod.w_date }}</td>
          <td>{{ prod.id.id }}</td>
          </tr>
     </table>
  </div>
</template>

<script>
export default {
  name: 'DiaryboardHome',
  data () {
    return {
      loginId:null,
      list:[]
    }
  },
  created:function(){
      this.loginId = sessionStorage.getItem('loginId')
      const self = this;
      self.$axios.get('http://localhost:8082/dboard')//+self.loginId
      .then(function(res){
          if(res.status == 200){
              self.list = res.data.list
          }else{
              alert('에러코드'+res.status)
          }
      });
  },
  methods:{
    detail(num){
      // alert(num)
      this.$router.push({name:'DiaryBoardDetail', query:{num:num}})
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img{
  width:100px;
  height:100px;
}
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
