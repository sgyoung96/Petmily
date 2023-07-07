<template>
  <div class="d-all">
    <div class="d-title">
      <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
          &nbsp;<span style="color:rgb(244, 191, 79);">DIARY</span></strong></h4>
    </div>
    <div class="d-box">
      <div class="box-title">
        <input type="text" id="id" v-model="id" readonly style="margin-bottom:5px"><br/>
        <input type="text" id="title" v-model="title" size="80" maxlength="20" placeholder="제목을 입력해 주세요.">
      </div>
      <div class="box-warning">
      ※ 양식에 맞지 않거나 게시판과 관계없는 글일 경우 작성자의 동의 없이 삭제될 수 있습니다.<br />
      ※ 음란물, 차별, 비하, 혐오 및 초상권, 저작권 침해 게시물은 민, 형사상의 책임을 질 수 있습니다.<br/>
    </div>
      <div class="box-content">
        <textarea id="content" v-model="content" rows="25" cols="130" style="resize: none;">
        </textarea>
      </div>
      <tr>
        <th>이미지1</th>
        <td><input type="file" id="pic1"></td>
      </tr>
      <tr>
        <th>이미지2</th>
        <td><input type="file" id="pic2"></td>
      </tr>
      <tr>
        <th>등록</th>
        <td><button v-on:click="add">등록</button></td>
      </tr>
    </div>
  </div>
</template>
<style scoped>
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

.box-title{
  text-align: left;
}

.box-warning{
  margin-top:10px;
  margin-bottom:10px;
  font-size:small;
}

.box-content{
  resize: none;
}
</style>
<script>
export default {
  name: 'DiaryboardAdd',
  data() {
    return {
      id: sessionStorage.getItem('loginId'),
      title: '',
      content: '이름:\n\n' +
              '나이:\n\n' +
              '입양일수:\n',
      likecnt: 0
    }
  },
  methods: {
    add() {
      let formData = new FormData();
      formData.append('title', this.title);
      formData.append('content', this.content);
      formData.append('id', this.id);
      formData.append('w_date', new Date());
      formData.append('likent', 0);
      const file1 = document.getElementById('pic1');
      const file2 = document.getElementById('pic2');
      formData.append('f[0]', file1.files[0]);
      formData.append('f[1]', file2.files[0]);
      const self = this;
      self.$axios
        .post('http://localhost:8082/dboard', formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(function (response){
          if(response.status == 200){
            alert('추가성공')
            self.$router.push('/diaryboardhome');
          }
        })
    }
  }
}

</script>