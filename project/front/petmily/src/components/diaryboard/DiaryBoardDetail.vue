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
    <button v-on:click="boardedit">수정하기</button>
    <button v-on:click="boarddelete">삭제하기</button>
    <div>
        <table border="1">
        <tr><th>댓글내용</th></tr>
        <tr><th><input type="text" v-model="content" id="content">
          <button v-on:click="commentadd">등록하기</button></th></tr>
        <input type="text" v-model="id" id="id">
        <input type="text" v-model="num" id="num">
      </table>
    </div>  
    <h2>댓글</h2>
    <ul>
      <li v-for="comment in comment" :key="comment.id">
        {{ comment.content }}{{ comment.w_date }}{{ comment.id.id }}
        <button v-on:click="commentedit">수정하기</button>
        <button v-on:click="commentdelete">삭제하기</button>
      </li>
    </ul>
  </template>

<script>
export default {
    name: 'DiaryBoardDetail', //뷰 컴포넌트 이름
    data() { //컴포넌트 데이터 옵션(num, dto, comment, content)
  return {
    num: this.$route.query.num, //현재 라우트 쿼리 매개변수로부터 num값 가져와 저장
    dto: {}, //초기에는 비어있는 객체로 초기화, 게시글 상세 정보 저장하는데 사용
    comment: {}, //초기에는 비어있는 배열로 초기화, 댓글목록 저장하는데 사용
    content: '', //댓글내용저장시사용
    id: sessionStorage.getItem('loginId')
  }
},
created() { //컴포넌트 생성시 호출되는 라이프사이클
  this.boarddetail(); //게시글디테일
  this.commentlist(); //댓글리스트
},
methods: {
  boarddetail() {//게시글디테일메소드
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
  boarddelete() {//게시글삭제메소드
    this.$axios.delete(`http://localhost:8082/dboard/${this.num}`)
      .then(response => {
        if (response.status === 200) {
          alert('삭제완료');
          this.$router.push({ name: 'DiaryBoardHome' }); // 게시글 홈으로 이동
        } else {
          alert('삭제오류.');
        }
      })
      .catch(error => {
        console.error(error);
        alert('삭제오류.');
      });
  },
  commentlist() {//댓글리스트메소드
    this.$axios.get(`http://localhost:8082/dcomment/${this.num}`)
      .then(response => {
        if (response.status === 200) {
          this.comment = response.data.dto;
        } else {
          alert('댓글을 불러오는 중에 오류가 발생했습니다.');
        }
      })
      .catch(error => {
        console.error(error);
        alert('댓글을 불러오는 중에 오류가 발생했습니다.');
      });
  },
    commentadd() {//댓글추가메소드
        const self = this;
        let formData = new FormData()
        formData.append('num', this.num);
        formData.append('id', this.id);
        formData.append('content', this.content);
        formData.append('w_date', new Date());
        self.$axios.post('http://localhost:8082/dcomment', formData)
        .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
        alert(dto.id +'댓글작성 완료')
        self.comment.push(dto);
        }else{
          alert('에러코드:' + res.status)
        }
      });
    },
    commentdelete() {//댓글삭제메소드
    this.$axios.delete(`http://localhost:8082/dcomment/${this.db_num}`)
    .then(function(res) {
      if (res.status === 200) {
        let dto = res.data.dto;
        alert(dto.id + ' 댓글 작성 완료');
        self.comment.push(dto); // 추가된 댓글을 comments 배열에 추가
      } else {
        alert('에러코드:' + res.status);
      }
    })
    .catch(function(error) {
      console.error(error);
      alert('댓글 작성 오류');
    });
  }
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
  