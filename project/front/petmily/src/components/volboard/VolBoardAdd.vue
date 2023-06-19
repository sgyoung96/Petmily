<template>
    <div id="app">
        <h3>봉사모집게시판 등록</h3>
        <div class="container">
    <div class="form-container">
      <h2>봉사모집게시판 추가</h2>
        <div class="form-group">
          <label for="product-name">작성자</label>
          <input type="text" class="form-control" id="product-name" v-model="writer" required>
        </div>
        <div class="form-group">
          <label for="product-description">제목</label>
          <input type="text" class="form-control" id="product-name" v-model="title" required>
        </div>
        <div class="form-group">
          <label for="product-price">내용</label>
          <input type="text" class="form-control" id="product-price" v-model="content" required>
        </div>
        <div class="form-group">
          <label for="product-seller">모집인원</label>
          <input type="number" class="form-control" id="product-seller" v-model="vol_number">
        </div>
        <div class="form-group">
          <label for="product-seller">봉사장소주소</label>
          <input type="text" class="form-control" id="product-seller" v-model="address">
        </div>
        <div class="form-group">
          <label for="product-seller">등록여부</label>
          <input type="number" class="form-control" id="product-seller" v-model="count">
        </div>
        <div class="form-group">
          <label for="product-quantity">게시판이미지1</label>
          <input type="file" class="form-control" id="f1">
        </div>
        <div class="form-group">
          <label for="product-quantity">게시판이미지2</label>
          <input type="file" class="form-control" id="f2">
        </div>
    
        <button type="button" class="btn btn-primary" v-on:click="add">추가</button>
        <button type="button" class="btn btn-secondary" v-on:click="cancel">취소</button>
    </div>
  </div>
    </div>
</template>
  
  <script>
  export default {
    name: 'VolBoardAdd',
    data () {
      return {
        writer: 'aaa',
        title: '',
        content: '',
        vol_number: 0,
        address: '',
        count: 0
      }
    },
    methods:{
      add(){
        let formData = new FormData();
        formData.append('writer', this.writer)
        formData.append('title', this.title)
        formData.append('content', this.content)
        formData.append('vol_number', this.vol_number)
        formData.append('address', this.address)
        formData.append('count', this.count)
        const file1 = document.getElementById('f1')
        const file2 = document.getElementById('f2')
        
        formData.append('f[0]', file1.files[0]);
        formData.append('f[1]', file2.files[0]);
        
        const self = this
        self.$axios.post('http://localhost:8082/volboard', formData, {headers:{"Content-Type":"multipart/form-data"}})
        .then(function(res){
          if(res.status == 200){
            self.$router.push('/volboardhome')
          }else{
            alert("에러코드:" + res.status)
          }
        })
      }
      },
    created:function(){//이 컴포넌트가 시작될때 실행되는 함수
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
.form-container {
      margin-top: 20px;
      max-width: 500px;
    }
</style>