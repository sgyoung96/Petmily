<template>
    <div>
      <h3>글작성</h3>
  
      <table border="1">
        <tr>
          <th>제목</th>
          <td><input type="text" id="title" v-model="title"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td><textarea id="content" rows="5" cols="30" v-model="content"></textarea></td>
        </tr>
        <tr>
          <th>아이디</th>
          <td><input type="text" id="id" v-model="id"></td>
        </tr>
        <tr>
          <th>이미지1</th>
          <td><input type="file" id="pic1"></td>
        </tr>
        <tr>
          <th>이미지2</th>
          <td><input type="file" id="pic2"></td>
        </tr>
        <tr>
          <th>등록</th>
          <td><button v-on:click="add">등록</button></td>
        </tr>
      </table>
  
    </div>
  </template>
  
  <script>
  export default {
    name: 'DiaryboardAdd',
    data() {
      return {
        // id: sessionStorage.getItem('loginId'),
        id:'',
        title: '',
        content: '',
      }
    },
    methods: {
      add() {
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
        self.$axios.post('http://localhost:8082/dboard', formData,
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