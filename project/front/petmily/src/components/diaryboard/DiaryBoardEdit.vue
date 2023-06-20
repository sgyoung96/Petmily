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
            <tr><th>title</th><td><input type="text" v-model="dto.title"></td></tr>
            <tr><th>content</th><td><input type="text" v-model="dto.content"></td></tr>
            <tr><th>w_date</th><td><input type="text" v-model="dto.w_date"></td></tr>
        </table>
    </div>
  </template>
  
  <script>
  export default {
    name: 'DiaryboardEdit',
    data() {
     return {
        num: this.$route.query.num, //현재 라우트 쿼리 매개변수로부터 num값 가져와 저장
        dto: {}, //초기에는 비어있는 객체로 초기화, 게시글 상세 정보 저장하는데 사용
      }
    },
    methods: {
      edit() {
        let formData = new FormData()
        formData.append('title', this.title);
        formData.append('content', this.content);
        formData.append('id', this.id);
        // formData.append('num', this.num);
        formData.append('w_date', new Date());
        const file1 = document.getElementById('pic1')
        const file2 = document.getElementById('pic2')
        formData.append('f[0]', file1.files[0]);
        formData.append('f[1]', file2.files[0]);
        const self = this;
        self.$axios.push('http://localhost:8082/dboard/edit', formData,
          { headers: { "Content-Type": "multipart/form-data" } })//비동기 요청
          .then(function(){
            self.$router.push('/diaryboardhome')
          }).catch(function(e){
            console.log(e)
            // self.$router.push('/diaryboardhome')
          })
      }
    }
  }
  </script>
    <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  img {
    width: 100px;
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