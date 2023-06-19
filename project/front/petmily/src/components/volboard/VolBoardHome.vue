<template>
    <div>
        <h3>{{ loginId }}가 등록한 상품</h3>
        <router-link to="/volboardadd">봉사게시판 작성</router-link>
        <table border="1">
            <tr><th>img1</th><th>img2</th><th>num</th><th>writer</th><th>title</th><th>content</th><th>봉사일자</th><th>모집인원</th></tr>
            <tr v-for="vboard in list" :key="vboard.num">
            <td><img :src="'http://localhost:8082/volboard/imgs/' +vboard.num+ '/1'"></td>
            <td><img :src="'http://localhost:8082/volboard/imgs/' + vboard.num + '/2'"></td>
            <td>{{ vboard.num }}</td>
            <td>{{ vboard.writer.id }}</td>
            <td>{{ vboard.title }}</td>
            <td>{{ vboard.content }}</td>
            <td>{{ vboard.vol_date }}</td>
            <td>{{ vboard.vol_number }}</td>
            </tr>
        </table>
    </div>
</template>
  
  <script>
  export default {
    name: 'VolBoardHome',
    data () {
      return {
        loginId:null,
        list:[]
      }
    },
    methods:{
      
    },
    created:function(){//이 컴포넌트가 시작될때 실행되는 함수
        
        this.loginId = 'aaa' //sessionStorage.getItem('loginId')
        const self = this
        self.$axios.get('http://localhost:8082/volboard')
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

  