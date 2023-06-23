<template>
  <div>
    <h3>봉사모집게시판 전체목록</h3>
    <router-link to="/volboardadd">봉사게시판 작성</router-link>
    <input type="text" placeholder="검색어를 입력하시오">
    <button v-on:click="search(title)">검색</button>
    <table border="1">
      <tr>
        <th>img1</th>
        <th>img2</th>
        <th>봉사게시판 작성자ID</th>
        <th>제목</th>
        <th>모집기한</th>
        <th>봉사일자</th>
        <th>모집인원</th>
        <th>봉사장소주소</th>
        <th>모집상태</th>
      </tr>
      <tr v-for="vboard in list" :key="vboard.num">
        <td><img :src="'http://localhost:8082/volboard/imgs/' +vboard.num+ '/1'"></td>
        <td><img :src="'http://localhost:8082/volboard/imgs/' + vboard.num + '/2'"></td>
        <td>{{ vboard.writer.id }}</td>
        <td v-on:click="detail(vboard.num, vboard.address)">{{ vboard.title }}</td>
        <td>{{ formatDate(vboard.deadline) }}</td>
        <td>{{ formatDate(vboard.vol_date) }}</td>
        <td>{{ vboard.count }} / {{ vboard.vol_number }}</td>
        <td>{{ vboard.address }}</td>
        <td>
          <span v-if="calculateDateDifference(vboard.deadline).difference < 0">모집마감</span>
          <span v-else>
            D-{{ calculateDateDifference(vboard.deadline).days }}
          </span>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'VolBoardHome',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      list: [],
      count: 0,
      state: '',
      sysdate: new Date(),
    }
  },
  methods: {
    detail(num, address) {
      this.$router.push({
        name: 'VolBoardDetail',
        query: { num: num, address: address },
      })
    },
    calculateDateDifference(deadline) {
      const deadlineDate = new Date(deadline)
      const difference = deadlineDate.getTime() - this.sysdate.getTime()
      const days = Math.ceil(difference / (1000 * 60 * 60 * 24))
      return { difference, days }
    },
    formatDate(date) {
      return new Date(date).toLocaleString('ko-KR')
    },
  },
  created: function () {
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/volboard').then(function (res) {
      if (res.status == 200) {
        self.list = res.data.list
      } else {
        alert('에러')
      }
    })
  },
}
</script>

<style scoped>
img {
  width: 100px;
  height: 100px;
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