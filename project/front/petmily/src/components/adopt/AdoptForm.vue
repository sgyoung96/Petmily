<template>
  <div class="d-all">
    <div class="d-title">
      <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
          &nbsp;<span style="color:rgb(244, 191, 79);">ADOPT</span></strong></h4>
    </div>
    <div class="d-box" style="text-align: center;">
      <img
        src="https://media4.giphy.com/media/F9Jq2l4665hNQxwumF/giphy.gif?cid=ecf05e47zaj6bkvkgje5m0239nrz09hswkfk4jm8xjjxpwa6&ep=v1_gifs_search&rid=giphy.gif&ct=g"
        style="width:360px; height: 270px; margin-bottom: 20px;">
      <div
        style="border-bottom: solid black 2px; margin-bottom:15px; width: 80%; text-align: left; font-weight: 900; font-size:36px; font-family: 'Jua', sans-serif;">
        분양게시판 글쓰기</div>
      <div class="box-title">
        <input type="text" id="id" v-model="id" readonly style="margin-bottom:5px"><br />
        <input type="text" id="title" v-model="title" size="80" maxlength="20" placeholder="제목을 입력해 주세요."><br />
        <div>
          <select id="category" v-model="category">
            <option value="" disabled selected hidden>동물종류</option>
            <option value="강아지">강아지</option>
            <option value="고양이">고양이</option>
            <option value="기타동물">기타동물</option>
          </select>&nbsp;
          <input type="radio" name="radioBtn" id="r1" value="암컷" v-model="gender">
          <label for="r1">암컷</label>
          <input type="radio" name="radioBtn" id="r2" value="수컷" v-model="gender">
          <label for="r2">수컷</label>
        </div>
        <div>
          <select id="address" v-model="address">
            <option value="" disabled selected hidden>주소</option>
            <option value="서울">서울시</option>
            <option value="경기,인천">경기,인천</option>
            <option value="충청도">충청도</option>
            <option value="전라도">전라도</option>
            <option value="경상도">경상도</option>
            <option value="제주도">제주도</option>
          </select>
        </div>
      </div>
      <div class="box-warning">
        ※ 양식에 맞지 않거나 게시판과 관계없는 글일 경우 작성자의 동의 없이 삭제될 수 있습니다.<br />
        ※ 음란물, 차별, 비하, 혐오 및 초상권, 저작권 침해 게시물은 민, 형사상의 책임을 질 수 있습니다.<br />
      </div>
      <div class="box-content">
        <textarea id="content" rows="25" cols="130" v-model="content" style="resize: none;"
          placeholder="내용을 입력해 주세요."></textarea>
      </div>
      <div style="display:flex; justify-content: space-between;">
        <div style="display:flex; flex-direction: column;">
          <input type="file" id="f1">
          <input type="file" id="f2">
        </div>
        <div>
          <button v-on:click="add">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'adoptForm',
  data() {
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
      radioValues: '',
      cnt: 0,
      ischeck: 0
    }
  },
  created: function () {
    this.chkKakaoValidatoion();
  },
  mounted() {
    window.scrollTo({ top: 0, behavior: 'auto' });
  },
  methods: {
    chkKakaoValidatoion() {
      if (sessionStorage.getItem('loginFlag') == 'kakao') {
        const self = this;
        self.$axios.get('http://localhost:8082/members/' + self.id).then(function (rs) {
          console.log(rs.data.dto);

          if (rs.data.dto == null) {
            self.$router.push({ name: 'KakaoAdditionalForm', query: { kakaoId: sessionStorage.getItem('loginId'), kakaoName: sessionStorage.getItem('kakaoName') } });
          }
        });
      }
    },


    add: function () {


      const file1 = document.getElementById('f1').files;
      const file2 = document.getElementById('f2').files;
      if (file1.length === 0 || file2.length === 0) {
        alert('사진을 2개 추가하여야 합니다.');
      } else {
        let formData = new FormData();
        formData.append('id', this.id);
        formData.append('title', this.title);
        formData.append('content', this.content);
        formData.append('category', this.category);
        formData.append('gender', this.gender);
        formData.append('address', this.address);
        formData.append('w_date', new Date())
        formData.append('cnt', 0)
        formData.append('isCheck', 0)
        formData.append('f[0]', file1[0]);
        formData.append('f[1]', file2[0]);

        const self = this;
        self.$axios.post('http://localhost:8082/adopt', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(function (rs) {
          if (rs.status == 200) {
            self.$router.push('/adopt')
          } else {
            console.log(rs);
          }
        });
      }
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
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

div {
  text-align: left;
}

.d-all {
  margin-left: 150px;
  margin-right: 150px;
  margin-bottom: 150px;
}

.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}

.d-box {
  border: 1px solid grey;
  border-top: 2px solid black;
  padding: 80px;
  text-align: left;
}

.box-title {
  text-align: left;
}

.box-warning {
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: small;
}

.box-content {
  resize: none;
}
</style>
