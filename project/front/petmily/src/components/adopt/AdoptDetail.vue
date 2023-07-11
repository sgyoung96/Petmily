<template>
  <div class="box-container">
    <img class="t-img" src="../../assets/images/강고.png">
    <div class="d-title">
      <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
          &nbsp;<span style="color:rgb(244, 191, 79);">ADOPT</span></strong></h4>
    </div>
    <div class="d-all">
      <table class="table table-bordered" style="margin-bottom:0px">
        <tbody div class="tbody" v-if="dto.id">
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
      <img class="box-img" :src="'http://localhost:8082/adopt/imgs/' + dto.num + '/1'">
      <img class="box-img" :src="'http://localhost:8082/adopt/imgs/' + dto.num + '/2'">
      <div class="box-content">
        <span>{{ dto.content }}</span>
        <span class="box-warning">
          ★사랑하는 반려동물이 좋은 주인을 만나 안전하게 살 수 있도록 아래의 사항을 꼭 지켜 주세요!!<br /><Br />
          1. 무료분양 계약서를 꼭 주고받으시기 바랍니다.<br />
          2. 반려동물 분양시, 분양자와 분양하시는분의 신분을 필히 확인하여 기억해두거나 신분증 사본을 받도록 하세요.<br />
          3. 본 무료분양글의 내용 및 사진은 저작권이 있으므로 재배포 및 무단전재를 금지합니다.<br />
          ※ 분양받을 목적 이외의 사항으로 분양글 등록 회원에게 연락,문자 등을 보내실 경우 법적 조치를 받으실 수 있습니다.<br />
        </span>
      </div>
      <div class="likebnt" @click="likebtn(dto.num)">
        <img class="likeimg" src="../../assets/images/찬하트.png">
        {{ dto.likecnt }}
      </div>
      <div>
      </div>
      <div v-if="showModal" class="edit-form">
        <div>
          <table>
            <tr>
              <th>제목</th>
              <td><input style="text; width:300px" v-model="dto.title" id="editTitle"></td>
              <th>사진1</th>
              <td><input type="file" id="f1"></td>
              <th>사진2</th>
              <td><input type="file" id="f2"></td>
            </tr>
            <tr>
              <th>내용</th>
              <td colspan="5"><textarea v-model="dto.content" id="editContent"
                  style="width:100%; height:400px; resize:none"></textarea></td>
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
          <button style="font-size: 17px;" @click="$router.push('/adopt')">목록으로</button>
        </div>
        <div v-if="dto.id">
          <span v-if="isAuthor">
            <button @click="adoptbtn(dto.num)" v-if="dto.ischeck == 1">분양완료</button>
            <button @click="adoptbtn(dto.num)" v-else>분양중</button>
            <button @click="edit()" style="font-size: 17px;">수정하기</button>
            <button v-on:click="boarddelete">삭제하기</button>
          </span>
        </div>
      </div><br />
      <div class="cbox-add">
        <span class="comment-profile">
          <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + id">
        </span>
        <textarea style="width:900px; resize:none;" v-model="content" id="content"></textarea>
        <button v-on:click="commentadd">등록하기</button>
      </div>
      <MessageModal :resender=resender v-if="displayDetail" @close="displayDetail = false" />
      <div v-for="comment in comment" :key="comment.id">
        <div class="comment-list">
          <div class="list-content">
            <div class="comment-profile">
              <img class="profile" style="cursor:pointer" @error="replaceImg"
                :src="'http://localhost:8082/members/imgs/' + comment.id.id" v-on:click="modal(comment.id.id)">
            </div>
            <div style="width:900px">
              <span>{{ comment.id.id }}</span>&nbsp;
              <span style="font-size: small; color:grey">{{ formatDate(comment.w_date) }}</span><br />
              <div v-if="!comment.editMode">{{ comment.content }}</div>
              <div v-if="comment.editMode" class="c-editForm">
                <textarea style="width:900px;  resize:none;" v-model="comment.editContent"
                  :cols="comment.editContent.length"></textarea>
                <button @click="saveComment(comment)">저장</button>
                <button @click="cancelEdit(comment)">취소</button>
              </div>
            </div>
          </div>
          <div style="float:right;" v-if="comment.id.id === id">
            <button @click="showEditForm(comment)">수정하기</button>
            <button @click="commentdelete(db_num)">삭제하기</button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
button {
  width: 100px;
  color: white;
  height: 30px;
  border: 1px solid rgb(244, 191, 79);
  border-radius: 20px;
  background-color: rgb(244, 191, 79);
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 15px;
  padding-top: 2px;
  margin-right: 5px;
}

button:hover {
  background-color: rgb(235, 156, 39);
  cursor: pointer;
}

.edit-form {
  background-color: #f9f9f9;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  max-width: 100%;
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
  padding: 4px 16px;
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
  width: 100%;
  height: 600px;
  margin-bottom: 20px;
}

.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}

.table th {
  background-color: #f8f9fa;
}

.a-img {
  text-align: left;
  margin-top: 10px;
  margin-bottom: 10px;
  width: 1000px;
}

.d-all {
  padding-left: 150px;
  padding-right: 150px;
}

.box-title {
  border-top: 2px solid lightgrey;
  border-bottom: 2px solid #e3e4e6;
  display: flex;
  justify-content: space-between;
  padding: 10px;
}

.box-img {
  padding: 20px;
  width: 45%;
  height: 400px;
  margin-top: 20px;
}

.box-content {
  padding: 50px;
  text-align: left;
  display: flex;
  flex-direction: column;
}

.box-warning {
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-top: 50px;
  float: center;
  background-color: white;
  padding: 24px 63px;
  display: flex;
  justify-content: left;
}

.likeimg {
  width: 30px;
  height: 30px;
}

.likebnt {
  padding-bottom: 50px;
  border-bottom: solid 2px lightgrey;
  cursor: pointer;
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
import MessageModal from "@/components/Message/MessageModal";
export default {
  name: 'AdoptDetail',
  components: {
    MessageModal
  },
  data() {
    return {
      num: this.$route.query.num,
      dto: {},
      comment: [],
      content: '',
      id: sessionStorage.getItem('loginId'),
      editContent: '',
      showModal: false,
      loginId: null,
      list: [],
      modalOpen: false,
      isModalViewed: false,
      MessageModal: false,
      displayDetail: false,
      resender: '',
      value: '',
      select: 'title',
      find: '',
    };
  },
  computed: {
    showHeader() {
      console.log('showHeader:', false); // 콘솔에서 계산된 속성 값 확인
      return false;
    },
    isAuthor() {
      // 현재 로그인한 사용자와 글 작성자를 비교하여 일치하는지 확인합니다.
      // 예를 들어, 현재 로그인한 사용자의 ID와 글 작성자의 ID를 비교할 수 있습니다.
      // 글 작성자의 ID는 dto.id.id로 가정합니다.
      const loginId = sessionStorage.getItem('loginId');
      return loginId === this.dto.id.id;
    },
  },
  created() {
    this.boarddetail();
    this.commentlist();
  },
  methods: {
    convertNewlines(text) {
      return text.replace(/\n/g, '<br>');
    },
    adoptbtn(num) {
      const self = this
      self.$axios.get('http://localhost:8082/adopt/ischeck/' + num)
        .then(function (res) {
          if (res.status == 200) {
            alert(res.data.flag)
          }
        })
    },
    editcancle() {
      this.showModal = false;
    },
    edit() {
      this.showModal = true;
    },
    replaceImg(e) {
      e.target.src = img;
    },
    likebtn(num) {
      alert(this.id)
      if (this.id == null) {
        alert('로그인 후 이용가능합니다.')
      } else {
        this.$axios.get('http://localhost:8082/adoptliketable/' + this.id + '/' + num)
          .then(response => {
            if (response.status == 200) {
              if (response.data.flag) {
                let formData = new FormData();
                formData.append('id', this.id)
                formData.append('num', num)
                this.$axios.post('http://localhost:8082/adoptliketable', formData)
                  .then(response => {
                    if (response.status == 200) {
                      this.$axios.get('http://localhost:8082/adopt/likeup/' + num)
                        .then(response => {
                          if (response.status == 200) {
                            this.dto.likecnt++;
                            alert('좋아요 수 1 추가')
                          }
                        })
                    } else {
                      alert('에러페이지')
                    }
                  })
              } else {
                let formData = new FormData();
                formData.append('id', this.id)
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
                            this.dto.likecnt--;
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
      formData.append('ab_num', comment.ab_num);
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
    },
    modal(sender) {
      const self = this;
      this.resender = sender

      self.displayDetail = true

    }
  }
};
</script>