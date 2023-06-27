<template>
  <div class="container text-center">
  <div class="row" style="margin-bottom: 40px;">
    <div class="col">
      <h2>입양일지</h2>
    </div>
    <div class="col">
    </div>
    <div class="col">
</div>
</div>
</div>
<div class="container text-center">
  <div class="row">
    <div class="col-1">
    </div>
    <div class="col-10">
      <div v-if="dto.id" style="border-top: 2px solid black; border-bottom: 2px solid lightgrey; display: flex; justify-content: space-between; padding: 10px;">
  <div style="text-align: left;">{{dto.title}}</div>
  <div style="text-align: right;">{{ dto.id.id }}{{ formatDate(dto.w_date) }}</div>
</div>
      <div>
      <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/1'">
      <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/2'">
      </div>
      <div style="padding:50px; border-bottom:solid 2px lightgrey">
        {{ dto.content }}
      </div>  
      <div style="float:right">
        <router-link to="/diaryboardedit">수정하기</router-link>
     <button v-on:click="boarddelete">삭제하기</button>
    </div><br/>
     <div>
        <table border="1">
        <tr><th>댓글작성</th></tr>
        <tr><th><input type="text" v-model="content" id="content">
          <button v-on:click="commentadd">등록하기</button></th></tr>
      </table>
      <div style="float:left">
  <div v-for="comment in comment" :key="comment.db_num">
  <div>
    {{ comment.content }} {{ comment.w_date }} {{ comment.id.id }} {{ comment.db_num }}
    <button @click="showEditForm(comment)">수정하기</button>
    <button @click="commentdelete(comment.db_num)">삭제하기</button>
  </div>
  <div v-if="comment.editMode">
    <textarea v-model="comment.editContent"></textarea>
    <button @click="saveComment(comment)">저장</button>
    <button @click="cancelEdit(comment)">취소</button>
  </div>
</div>
</div><br/>
    </div>  
    </div>
    <div class="col-1">
    </div>
  </div>
  </div>
    <div>      
    </div>
  </template>

<script>
export default {
  name: 'DiaryBoardDetail',
  data() {
    return {
      num: this.$route.query.num,
      dto: {},
      comment: [],
      content: '',
      id: sessionStorage.getItem('loginId'),
      editContent:''
    };
  },
  created() {
    this.boarddetail();
    this.commentlist();
  },
  methods: {
    formatDate(date) {
  const options = { year: '2-digit', month: '2-digit', day: '2-digit', hour12: false };
  return new Date(date).toLocaleString('ko-KR', options);
},
    boarddetail() {
      this.$axios
        .get(`http://localhost:8082/dboard/${this.num}`)
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
    boarddelete() {
      this.$axios
        .delete(`http://localhost:8082/dboard/${this.num}`)
        .then(response => {
          if (response.status === 200) {
            alert('삭제완료');
            this.$router.push({ name: 'DiaryBoardHome' });
          } else {
            alert('삭제오류.');
          }
        })
        .catch(error => {
          console.error(error);
          alert('삭제오류.');
        });
    },
    commentlist() {
      this.$axios
        .get(`http://localhost:8082/dcomment/${this.num}`)
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
    commentadd() {
      const formData = new FormData();
      formData.append('num', this.num);
      formData.append('id', this.id);
      formData.append('content', this.content);
      formData.append('w_date', new Date());

      this.$axios
        .post('http://localhost:8082/dcomment', formData)
        .then(response => {
          if (response.status === 200) {
            
            const dto = response.data.dto;
            alert(dto.id + ' 댓글 작성 완료');
            this.comment.push(dto);
            this.content = ''; // 댓글 작성 완료 후 입력 필드 초기화
          } else {
            alert('에러코드:' + response.status);
          }
        });
    },
    showEditForm(comment) {
    comment.editMode = !comment.editMode;
    comment.editContent = comment.content;
  },
  saveComment(comment) {
  const formData = new FormData();
  formData.append('db_num', comment.db_num);
  formData.append('content', comment.editContent);
  formData.append('w_date', new Date());

  this.$axios
    .put('http://localhost:8082/dcomment', formData)
    .then(response => {
      if (response.status === 200) {
        alert(response.data.dto.content);
        this.commentlist();
      } else {
        alert('에러코드:' + response.status);
      }
    });
},
  cancelEdit(comment) {
    comment.editMode = false;
  },
    commentdelete(db_num) {
      this.$axios
        .delete(`http://localhost:8082/dcomment/${db_num}`)
        .then(response => {
          if (response.status === 200) {
            alert('삭제완료');
            // 댓글 목록을 갱신하기 위해 API 호출 또는 데이터를 직접 업데이트하는 작업을 수행해야 합니다.
            this.commentlist(); // 댓글 목록을 다시 불러오는 작업 예시
          } else {
            alert('삭제오류.');
          }
        })
        .catch(error => {
          console.error(error);
          alert('삭제오류.');
        });
    }
  }
};
</script>
    // <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  img{
    width: 300px;
    height:300px;
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
  