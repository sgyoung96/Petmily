<template>
  <div class="box-container">
    <img class="t-img" src="../../assets/images/배경.png">
    <div class="d-title">
      <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
          &nbsp;<span style="color:rgb(244, 191, 79);">ADOPT</span></strong></h4>
    </div>
    <div class="d-all">
      <table class="table table-bordered" style="margin-bottom:0px">
      <tbody div class="tbody"  v-if="dto.id">
        <tr>
          <th>제목</th>
          <td colspan="3">{{ dto.title }}</td>
        </tr>
        <tr>
          <th>등록인</th>
          <td colspan="3">{{ dto.id.id }}</td>
        </tr>
        <tr>
          <th>분양동물</th>
          <td colspan="3">{{ dto.category }}</td>
        </tr>
        <tr>
          <th>분양지역</th>
          <td>{{ dto.address }}</td>
          <th>암수구분</th>
          <td>{{ dto.gender }}</td>
        </tr>
        <tr>
          <th>연락처</th>
          <td>{{ dto.id.phone }}</td>
          <th>E-mail</th>
          <td>{{ dto.id.email }}</td>
        </tr>
      </tbody>
    </table>
    <img class="a-img" src="../../assets/images/jinjin.png">
      <div class="box-title" v-if="dto.id">
        <span>{{ dto.title }}</span>
        <span>{{ dto.id.id }}{{ formatDate(dto.w_date) }}</span>
      </div>
      <img class="box-img" :src="'http://localhost:8082/adopt/imgs/' + dto.num + '/1'">
      <img class="box-img" :src="'http://localhost:8082/adopt/imgs/' + dto.num + '/2'">
      <div class="box-content">
        {{ dto.content }}
      </div>
      <div v-if="showModal" class="edit-form">
        <div>
          <table>
            <tr>
              <th>사진1</th>
              <td><input type="file" id="f1"></td>
            </tr>
            <tr>
              <th>사진2</th>
              <td><input type="file" id="f2"></td>
            </tr>
            <tr>
              <th>Title</th>
              <td><input type="text" v-model="dto.title" id="editTitle"></td>
            </tr>
            <tr>
              <th>Content</th>
              <td><input type="text" v-model="dto.content" id="editContent"></td>
            </tr>
          </table>
          <div class="edit-buttons">
            <button v-on:click="editfunc(dto.num)">수정</button>
            <button v-on:click="editcancle()">취소</button>
          </div>
        </div>
      </div>
      <div class="d-btn">
        <div>
          <router-link to="/diaryboardhome" class="badge text-bg-secondary" style="font-size: 17px;">목록으로</router-link>
        </div>
        <div>
          <button @click="likebtn(dto.id.id, dto.num)">좋아요</button>
          <span v-on:click="edit()" class="badge text-bg-secondary" style="font-size: 17px;">수정하기</span>
          <button v-on:click="boarddelete">삭제하기</button>
        </div>
      </div><br />
      <div class="cbox-add">
        <span class="comment-profile">
          <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + id">
        </span>
        <textarea style="width:900px;" v-model="content" id="content"></textarea>
        <button v-on:click="commentadd">등록하기</button>
      </div>
      <div v-for="comment in comment" :key="comment.id">
        <div class="comment-list">
          <div class="list-content">
            <div class="comment-profile">
              <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + comment.id.id">
            </div>
            <div style="width:900px">
              <span>{{ comment.id.id }}</span>&nbsp;<span style="font-size: small; color:grey">{{
                formatDate(comment.w_date) }}</span><br />
              <div v-if="!comment.editMode">{{ comment.content }}</div>
              <div v-if="comment.editMode" class="c-editForm">
                <textarea style="width:900px;" v-model="comment.editContent"
                  :cols="comment.editContent.length"></textarea>
                <button @click="saveComment(comment)">저장</button>
                <button @click="cancelEdit(comment)">취소</button>
              </div>
            </div>
          </div>
          <div style="float:right;">
            <button @click="showEditForm(comment)">수정하기</button>
            <button @click="commentdelete(comment.db_num)">삭제하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.edit-form {
  background-color: #f9f9f9;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  max-width: 500px;
  margin: 0 auto;
}

.edit-form table {
  width: 100%;
  border-collapse: collapse;
}

.edit-form th,
.edit-form td {
  padding: 10px;
  text-align: left;
}

.edit-buttons {
  margin-top: 20px;
  text-align: right;
}

.edit-buttons button {
  padding: 8px 16px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.edit-buttons button+button {
  margin-left: 10px;
}

.box-container {
  position: relative;
  padding-bottom: 100px;
}

.t-img {
  width: 85%;
  height: 500px;
  margin-bottom: 20px;
}

.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}

.table th {
  background-color: rgb(156, 156, 39);
}
.a-img {
 text-align: left;
  margin-top: 10px;
  margin-bottom: 10px;
  width:1000px;
}

.d-all {
  padding-left: 150px;
  padding-right: 150px;
}

.box-title {
  border-top: 2px solid black;
  border-bottom: 2px solid lightgrey;
  display: flex;
  justify-content: space-between;
  padding: 10px;
}

.box-img {
  padding: 20px;
  width: 400px;
  height: 400px;
  margin-top: 20px;
}

.box-content {
  padding: 50px;
  border-bottom: solid 2px lightgrey;
  text-align: left;
}

.d-btn {
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}

.cbox-add {
  display: flex;
}

.comment-profile {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  margin-right: 10px;
}

.comment-profile img {
  width: 100%;
  height: 100%;
  object-fit: cover;

}

.comment-list {
  display: flex;
  margin-top: 10px;
  margin-bottom: 10px;
}

.list-content {
  display: flex;
  text-align: left;
}

.c-editForm {
  display: block;
  float: left;
}
</style>
<script>
import img from "@/assets/imgs/mypage_sample.jpg";
export default {
  name: 'AdoptDetail',
  data() {
    return {
      num: this.$route.query.num,
      dto: {},
      comment: [],
      content: '',
      id: sessionStorage.getItem('loginId'),
      editContent: '',
      showModal: false,
    };
  },
  created() {
    this.boarddetail();
    this.commentlist();
  },
  methods: {
    editcancle() {
      this.showModal = false;
    },
    edit() {
      this.showModal = true;
    },
    replaceImg(e) {
      e.target.src = img;
    },
    likebtn(id, num) {
      if(this.id == null){
        alert('로그인 후 이용가능합니다.')
      }else{
      this.$axios.get('http://localhost:8082/adoptliketable/' + id + '/' + num)
        .then(response => {
          if (response.status == 200) {
            if (response.data.flag) {
              let formData = new FormData();
              formData.append('id', id)
              formData.append('num', num)
              this.$axios.post('http://localhost:8082/adoptliketable', formData)
                .then(response => {
                  if (response.status == 200) {
                    this.$axios.get('http://localhost:8082/adopt/likeup/' + num)
                      .then(response => {
                        if (response.status == 200) {
                          alert('좋아요 수 1 추가')
                        }
                      })
                  } else {
                    alert('에러페이지')
                  }
                })
            } else {
              let formData = new FormData();
              formData.append('id', id)
              formData.append('num', num)
              this.$axios.delete('http://localhost:8082/adoptliketable', {
                data: formData
              })
                .then(response => {
                  if (response.status === 200) {
                    this.$axios.get('http://localhost:8082/adopt/likedown/' + num)
                      .then(response => {
                        if (response.status === 200) {
                          alert('좋아요 수 1 감소');
                        }
                      })
                      .catch(error => {
                        console.error(error);
                        alert('좋아요 수 감소 중에 오류가 발생했습니다.');
                      });
                  }
                })
                .catch(error => {
                  console.error(error);
                  alert('삭제 요청 중에 오류가 발생했습니다.');
                });

            }
          }
        })
      }
    },
    editfunc(num) {
      let formData = new FormData();
      const self = this
      const file1 = document.getElementById('f1').files[0];
      const file2 = document.getElementById('f2').files[0]
      const editTitle = document.getElementById('editTitle')
      const editContent = document.getElementById('editContent')

      formData.append('title', editTitle.value)
      formData.append('content', editContent.value)
      formData.append('num', num)
      if (file1 != null && file2 != null) {
        formData.append('f[0]', file1)
        formData.append('f[1]', file2)
      }
      self.$axios.put('http://localhost:8082/adopt', formData, {
        headers: { "Content-Type": "multipart/form-data" },
      })
        .then(function (res) {
          if (res.status == 200) {
            alert('수정완료')
            self.dto = res.data.dto
          }
        })
    },
    formatDate(date) {
      const options = { year: '2-digit', month: '2-digit', day: '2-digit', hour12: false };
      return new Date(date).toLocaleString('ko-KR', options);
    },
    boarddetail() {
      this.$axios
        .get(`http://localhost:8082/adopt/${this.num}`)
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
        .delete(`http://localhost:8082/adopt/${this.num}`)
        .then(response => {
          if (response.status === 200) {
            alert('삭제완료');
            this.$router.push({ name: 'AdoptList' });
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
        .get(`http://localhost:8082/adoptcomment/${this.num}`)
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
        .post('http://localhost:8082/adoptcomment', formData)
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
        .put('http://localhost:8082/adoptcomment', formData)
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
        .delete(`http://localhost:8082/adoptcomment/${db_num}`)
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