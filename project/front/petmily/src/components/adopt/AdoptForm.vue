<template>
  <div>
      <h3>분양게시판 글쓰기</h3>
      <br>개인 분양 게시판 고유 번호 : <input v-model="num" type="text" >
      <br>작성자 : <input v-model="writer" type="text" >
      <br>제목 : <input v-model="title" type="text" >
      <br>내용 : <textarea v-model="content" />
      <br>날짜 : <input v-model="date" type="text" readonly>
      <br>주소 : <input v-model="address" type="text" >
      <br>첫번째 사진 경로 : <input v-model="pic1" type="text" >
      <br>두번째 사진 경로 : <input v-model="pic2" type="text" >
      <br><br>
      <span v-on:click="addform">작성하기</span>
  </div>
</template>

<script>
export default {
  name: 'adoptForm',
  data () {
    return {
        num: 1,
        writer: '',
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
