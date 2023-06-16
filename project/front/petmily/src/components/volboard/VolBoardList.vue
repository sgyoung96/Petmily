<template>
    <div>
        <router-link to="/orderlist">구매목록</router-link><br/>
        <router-link to="/orderlist2">장바구니목록</router-link>
        <h3>내 구매목록</h3>
        <table border="1">
            <tr><th>num</th><th>상품명</th><th>상품가격</th><th>결제금액</th><th>seller</th></tr>
            <tr v-for="order in list" :key="order.num">
            <td>{{ order.num }}</td>
            <td v-on:click="detail(order.prod.num)">{{ order.prod.name }}</td>
            <td>{{ order.prod.price }}</td>
            <td>{{ order.payment }}</td>
            <td>{{ order.prod.seller.id }}</td>
            </tr>
        </table>
    </div>
</template>
  
  <script>
  export default {
    name: 'OrderList',
    data () {
      return {
        list:[]
      }
    },
    methods:{
      detail(num){
        this.$router.push({ name: 'OrderDetail', query: { num: num }});
      }
    },
    created:function(){//이 컴포넌트가 시작될때 실행되는 함수
        let loginId = sessionStorage.getItem('loginId')
        const self = this
        self.$axios.get('/order/'+loginId+"/"+true)
        .then(function(res){
            if(res.status == 200){
                self.list = res.data.list
            }else{
                alert('에러')
            }
        })
    }
  }
  </script>
  <style scoped>
  img{
      width:100px;
      height: 100px;
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