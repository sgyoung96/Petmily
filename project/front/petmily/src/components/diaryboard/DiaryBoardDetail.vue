<template>
    <div>
        <h3>{{ dto.name }} 상품 정보</h3>
        <table>
            <tr>
                <td><img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/1'"></td>
                <td><img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/2'"></td>
            </tr>
            </table>
        <table border="1">
            <tr><th>title</th><td><input type="text" v-model="dto.title" readonly></td></tr>
            <tr><th>content</th><td><input type="text" v-model="dto.content" readonly></td></tr>
            <tr><th>w_date</th><td><input type="text" v-model="dto.w_date" readonly></td></tr>
            <!-- <tr v-if="dto.id"> dto.id가 있을 경우에만 랜더링 / 랜더링이 너무 빨리되서 일어나는 오류 -->
            <tr><th>id</th><td><input type="text" v-model="dto.id.id" readonly></td>
            </tr>
        </table>
    </div>
  </template>

<script>
export default {
    name: 'DiaryBoardDetail',
    data () {
      return {
        num:this.$route.query.num,
        dto:{}
      }
    },
    created:function(){
        const self = this;
        console.log("self",self.num)
        self.$axios.get('http://localhost:8082/dboard/'+self.num)
        .then(function(res){
          console.log(res)
            if(res.status == 200){
                self.dto = res.data.dto
            }else{
                alert('에러코드'+res.status)
            }
        });
    },
    methods:{
      detail(num){
        // alert(num)
        this.$router.push({name:'DiaryBoardDetail', params:{num:num}})
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
  