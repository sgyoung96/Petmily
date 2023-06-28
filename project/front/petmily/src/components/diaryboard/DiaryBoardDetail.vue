<template>
  <div class="d-title">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
      &nbsp;<span style="color:rgb(244, 191, 79);">DIARY</span></strong></h4>
<h6 style="text-align: center;">새로운 가족을 만나게 된 아이들의 소식을 남겨주세요.</h6></div>
  <div>
    <img src="../../assets/images/dboardpic.png" style="width: 1200px; height: 160px; margin-bottom: 20px;">
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
      <div style="float:left">
        <span router-link to="/diaryboardhome" class="badge text-bg-secondary" style="font-size: 17px;">목록으로</span>
      </div>
      <div style="float:right">
      <span router-link to="/diaryboardedit" class="badge text-bg-secondary" style="font-size: 17px;">수정하기</span>
     <span v-on:click="boarddelete" class="badge text-bg-secondary" style="font-size: 17px;">삭제하기</span>
    </div><br/>
     <div style="float:left; display:block;">
<textarea style="width:800px;" v-model="content" id="content"></textarea>
          <button v-on:click="commentadd">등록하기</button>
      <div>
  <div v-for="comment in comment" :key="comment.db_num">
  <div style="display:flex; justify-content: space-between">
    <div style="float:left">
    {{ comment.content }} {{ comment.w_date }} {{ comment.id.id }} {{ comment.db_num }}
  </div>
  <div>
    <button @click="showEditForm(comment)">수정하기</button>
    <button @click="commentdelete(comment.db_num)">삭제하기</button>
  </div>
  </div>
  <div v-if="comment.editMode" style="float:left">
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
 <style scoped>
 .d-title{
  flex-direction: column;
  display:flex;
  margin-top:120px;
  margin-bottom:120px;
 }
 img{
   width: 300px;
   height:300px;
 }
 </style>
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