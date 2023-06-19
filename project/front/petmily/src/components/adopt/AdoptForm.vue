<template>
  <div>
      <h3>분양게시판 글쓰기</h3>
      <br>개인 분양 게시판 고유 번호 : <input id="num" v-model="num" type="text" >
      <br>작성자 : <input id="id" v-model="id" type="text" >
      <br>제목 : <input id="title" v-model="title" type="text" >
      <br>내용 : <textarea id="content" v-model="content" />
      <br>날짜 : <input id="date" v-model="date" type="text">
      <br>주소 : <input id="address" v-model="address" type="text" >
      <br>첫번째 사진 경로 : <input id="pic1" v-model="pic1" type="text" >
      <br>두번째 사진 경로 : <input id="pic2" v-model="pic2" type="text" >
      <br><br>
      <span v-on:click="addform">작성하기</span>
  </div>
</template>

<script>
export default {
  name: 'adoptForm',
  data () {
    return {
        num: 0,
        IDBObjectStore: '',
        title: '',
        content: '',
        date: '',
        address: '',
        pic1: '',
        pic2: ''
    }
  },
  created: function () {

  },
  methods: {
    addform: function() {
        this.num = document.getElementById('num').value;
        this.id = document.getElementById('id').value;
        this.title = document.getElementById('title').value;
        this.content = document.getElementById('content').value;
        this.date = document.getElementById('date').value;
        this.address = document.getElementById('address').value;
        this.pic1 = document.getElementById('pic1').value;
        this.pic2 = document.getElementById('pic2').value;

        let formData = new FormData();
        //formData.append('num', this.num);
        formData.append('id', this.id);
        formData.append('title', this.title);
        formData.append('contnet', this.content);
        //formData.append('date', this.date);
        formData.append('address', this.address);
        formData.append('pic1', this.pic1);
        formData.append('pic2', this.pic2);

        const self = this;
        self.$axios.post('http://localhost:8082/adopt', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(function(rs) {
            //self.$router.push('/adopt')
            console.log(rs);
        });
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    div {
        text-align: left;
    }
</style>
