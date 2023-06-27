<template>
  <div>
      <h3>분양게시판 목록</h3>
      <router-link to="/adopt/addform">글쓰기</router-link>
      <table border="1">
      <tr>
        <th>img1</th>
        <th>img2</th>
        <th>분양게시판 작성자ID</th>
        <th>제목</th>
        <th>내용</th>
        <th>작성일</th>
        <th>주소</th>
      </tr>
      <tr v-for="adopt in list" :key="adopt.num">
        <td><img :src="'http://localhost:8082/adopt/imgs/' +adopt.num+ '/1'"></td>
        <td><img :src="'http://localhost:8082/adopt/imgs/' + adopt.num + '/2'"></td>
        <td>{{ adopt.id.id }}</td>
        <td v-on:click="detail(adopt.num)">{{ adopt.title }}</td>
        <td>{{ adopt.content }}</td>
        <td>{{ adopt.w_date }}</td>
        <td>{{ adopt.address }}</td>
      </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'adoptList',
  data () {
    return {
      loginId: sessionStorage.getItem('loginId'),
      list:[]
    }
  },
  created: function () {
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/adopt').then(function (res) {
      if (res.status == 200) {
        self.list = res.data.list
      } else {
        alert('에러')
      }
    })
  },
  methods: {
    detail: function(num) {
      this.$router.push({name: 'AdoptDetail', query: {num: num}})
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  img {
  width: 100px;
  height: 100px;
}
</style>
