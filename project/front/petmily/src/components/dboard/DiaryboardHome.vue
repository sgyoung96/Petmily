<template>
  <div>
      <h3>hi</h3>
      <!-- <router-link to="/dboardadd">게시글등록</router-link>
     <table border="1">
          <tr><th>img</th><th>num</th><th>name</th><th>price</th><th>seller</th></tr>
          <tr v-for="prod in list" :key="prod.num">
          <td><img :src="'/products/imgs/' + prod.num + '/1'"></td>
          <td>{{ prod.num }}</td>
          <td><a v-on:click="$event=>detail(prod.num)">{{ prod.name }}</a></td>
          <td>{{ prod.price }}</td>
          <td>{{ prod.seller.id }}</td>
          </tr>
     </table> -->
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
  // },
  // created:function(){
  //     this.loginId = sessionStorage.getItem('loginId')
  //     const self = this;
  //     self.$axios.get('/products/sellers/'+self.loginId)
  //     .then(function(res){
  //         if(res.status == 200){
  //             self.list = res.data.list
  //         }else{
  //             alert('에러코드'+res.status)
  //         }
  //     });
  // },
  // methods:{
  //   detail(num){
  //     // alert(num)
  //     this.$router.push({name:'ShopDetail', query:{num:num}})
  //   }
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
