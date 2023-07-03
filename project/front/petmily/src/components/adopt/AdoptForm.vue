<template>
  <div>
      <h3>분양게시판 글쓰기</h3>
  
      <br>작성자 : <input id="id" v-model="id" type="text" >
      <br>제목 : <input id="title" v-model="title" type="text" >
      <br>내용 : <textarea id="content" v-model="content" ></textarea>
      <br>Pet 종류: <input id="category" v-model="category" type="text" >
      <div>
            <input type="radio" name="radioBtn" id="r1" value="첫번째 버튼" v-model="gender">
            <label for="r1">암컷</label>
            <input type="radio" name="radioBtn" id="r2" value="두번째 버튼" v-model="gender">
            <label for="r2">수컷</label>
      </div>
      <br>주소 : <input id="address" v-model="address" type="text" >
      <br>첫번째 사진 경로 : <input id="f1" type="file" >
      <br>두번째 사진 경로 : <input id="f2" type="file" >
      <br><br>
      <button type="button" v-on:click="addform">추가</button>
  </div>
</template>

<script>
export default {
  name: 'adoptForm',
  data () {
    return {
        id: sessionStorage.getItem('loginId'),
        num: 0,
        IDBObjectStore: '',
        title: '',
        content: '',
        date: '',
        address: '',
        pic1: '',
        pic2: '',
        gender: '',
        category: '',
        radioValues:''
    }
  },
  created: function () {

  },
  methods: {


    addform: function() {


        const file1 = document.getElementById('f1')
        const file2 = document.getElementById('f2')
        let formData = new FormData();
        alert(this.content)
        formData.append('id', this.id);
        formData.append('title', this.title);
        formData.append('content', this.content);
        formData.append('category', this.category);
        formData.append('gender', this.gender);
        formData.append('address', this.address);
        formData.append('w_date', new Date())
        formData.append('f[0]', file1.files[0]);
        formData.append('f[1]', file2.files[0]);

        const self = this;
        self.$axios.post('http://localhost:8082/adopt', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function(rs) {
          if(rs.status == 200){
            self.$router.push('/adopt')
          }else{
            console.log(rs);
          }
        });
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    div {
        text-align: left;
    }
</style>
