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
            <tr v-if="dto.id"> <!-- dto.id가 있을 경우에만 랜더링 / 랜더링이 너무 빨리되서 일어나는 오류 -->
            <th>id</th><td><input type="text" v-model="dto.id.id" readonly></td>
            </tr>
        </table>
    </div>
    <div>
        <table border="1">
        <tr><th>댓글내용</th></tr>
        <tr><th><input type="text" v-model="content" id="content"><button v-on:click="add">등록하기</button></th></tr>
        <input type="text" v-model="id" id="id">
        <input type="text" v-model="num" id="num">
      </table>
    </div>  
    <h2>댓글</h2>
    <ul>
      <li v-for="comment in comments" :key="comment.id">
        {{ comment.content }}
        {{ comment.id.id }}
      </li>
    </ul>
  </template>

<script>
export default {
    name: 'DiaryBoardDetail',
    data() {
  return {
    num: this.$route.query.num,
    dto: {},
    comments: [],
    content: ''
  }
},
created() {
  this.fetchPostDetails();
  this.fetchComments();
},
methods: {
  fetchPostDetails() {
    this.$axios.get(`http://localhost:8082/dboard/${this.num}`)
      .then(response => {
        if (response.status === 200) {
          this.dto = response.data.dto;
        } else {
          alert('게시글을 불러오는 중에 오류가 발생했습니다.');
        }
      })
      .catch(error => {
        console.error(error);
        alert('게시글을 불러오는 중에 오류가 발생했습니다.');
      });
  },
  fetchComments() {
    this.$axios.get(`http://localhost:8082/dcomment/${this.num}`)
      .then(response => {
        if (response.status === 200) {
          this.comments = response.data.dto;
        } else {
          alert('댓글을 불러오는 중에 오류가 발생했습니다.');
        }
      })
      .catch(error => {
        console.error(error);
        alert('댓글을 불러오는 중에 오류가 발생했습니다.');
      });
  }
  // ...
},
      add() {
        const self = this;
        let formData = new FormData()
        formData.append('num', this.num);
        formData.append('id', this.id);
        formData.append('content', this.content);
        formData.append('w_date', new Date());
        self.$axios.post('http://localhost:8082/dcomment', formData)
        .then(function(res){ //요청 결과 받아옴. 파람res에 결과저장됨. res.data가 백단에서 전송한 데이터
        if(res.status == 200){
          let dto = res.data.dto
          alert(dto.id+"/"+dto.type)
        }else{
          alert('에러코드'+res.status)
        }
      });
    }
  }
    </script>
    // <!-- Add "scoped" attribute to limit CSS to this component only -->
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
  