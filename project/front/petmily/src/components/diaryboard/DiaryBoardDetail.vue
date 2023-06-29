<template>
  <div>
    <img class="t-img" src="../../assets/images/배경.png">
  </div>
  <div class="d-title">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">DIARY</span></strong></h4>
  </div>
  <div>
    <img src="../../assets/images/dboardpic.png" style="width: 1200px; height: 160px; margin-bottom: 20px;">
  </div>
  <div class="container text-center">
    <div class="row">
      <div class="col-1">
      </div>
      <div class="col-10">
        <div v-if="dto.id"
          style="border-top: 2px solid black; border-bottom: 2px solid lightgrey; display: flex; justify-content: space-between; padding: 10px;">
          <div>{{ dto.title }}</div>
          <div>{{ dto.id.id }}{{ formatDate(dto.w_date) }}</div>
        </div>
        <div>
          <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/1'">
          <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/2'">
        </div>
        <div style="padding:50px; border-bottom:solid 2px lightgrey">
          {{ dto.content }}
        </div>
        <div v-if="showModal">
          <div>
            <table border="1">
              <tr>
                <th>사진1</th>
                <td><input type="file" id="f1"></td>
              </tr>
              <tr>
                <th>사진2</th>
                <td><input type="file" id="f2"></td>
              </tr>
              <tr>
                <th>title</th>
                <td><input type="text" v-model="dto.title" id="editTitle"></td>
              </tr>
              <tr>
                <th>content</th>
                <td><input type="text" v-model="dto.content" id="editContent"></td>
              </tr>
              <button v-on:click="editfunc(dto.num)">수정</button>
            </table>
          </div>
        </div>
        <div class="d-btn">
          <div>
            <span router-link to="/diaryboardhome" class="badge text-bg-secondary" style="font-size: 17px;">목록으로</span>
          </div>
          <div>
            <button @click="likebtn(dto.id.id, dto.num)">조아요</button>
            <span v-on:click="edit()" class="badge text-bg-secondary" style="font-size: 17px;">수정하기</span>

            <button v-on:click="boarddelete">삭제하기</button>
          </div>
        </div><br />
        <div style="float:left; display:flex; flex-direction: column;">
          <div>
            <span class="box-profile" style="background: #black;">
              <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + id">
            </span>
            <textarea style="width:800px;" v-model="content" id="content"></textarea>
            <button v-on:click="commentadd">등록하기</button>
          </div>
          <div>
            <div v-for="comment in comment" :key="comment.db_num">
              <div style="display:flex; justify-content: space-between">
                <div style="float:left">
                  <span class="box-profile" style="background: #black;">
                    <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + comment.id.id">
                  </span>{{ comment.content }} {{ formatDate(comment.w_date) }} {{ comment.id.id }}
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
          </div>
        </div><br />
      </div>
    </div>
    <div class="col-1">
    </div>
  </div>
  <div>
  </div>
</template>
<style scoped>
.t-img{
  width: 85%;
  height: 500px;
  margin-bottom: 20px;
}
.d-all{
  padding-left:150px;
  padding-right:150px;
}
.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}
.m-img{
  width: 1210px;
  height: 160px;
  margin-top:10px;
  margin-bottom:10px;
}

img {
  width: 300px;
  height: 300px;
}

.box-profile {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
}

.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}

.d-btn {
  float: right;
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
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
      editContent: '',
      showModal: false,
    };
  },
  created() {
    this.boarddetail();
    this.commentlist();
  },
  methods: {
    edit() {
      this.showModal = true;
    },
    editfunc(num){
      let formData = new FormData();
      const self = this
      const file1 = document.getElementById('f1').files[0];
      const file2 = document.getElementById('f2').files[0]
      const editTitle = document.getElementById('editTitle')
      const editContent = document.getElementById('editContent')

      formData.append('title', editTitle.value)
      formData.append('content', editContent.value)
      formData.append('num', num)
      if(file1 != null && file2 != null){
        formData.append('f[0]', file1)
        formData.append('f[1]', file2)
      }
      self.$axios.put('http://localhost:8082/dboard', formData, {
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then(function(res){
        if(res.status == 200){
          alert('수정완료')
          self.dto = res.data.dto
        }
      })
    },
    formatDate(date) {
      const options = { year: '2-digit', month: '2-digit', day: '2-digit', hour12: false };
      return new Date(date).toLocaleString('ko-KR', options);
    },
    likebtn(id, num) {
      this.$axios.get('http://localhost:8082/liketable/' + id + '/' + num)
        .then(response => {
          if (response.status == 200) {
            if (response.data.flag) {
              let formData = new FormData();
              formData.append('id', id)
              formData.append('num', num)
              this.$axios.post('http://localhost:8082/liketable', formData)
                .then(response => {
                  if (response.status == 200) {
                    this.$axios.get('http://localhost:8082/dboard/likeup/' + num)
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
              this.$axios.delete('http://localhost:8082/liketable', {
                data: formData
              })
                .then(response => {
                  if (response.status === 200) {
                    this.$axios.get('http://localhost:8082/dboard/likedown/' + num)
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