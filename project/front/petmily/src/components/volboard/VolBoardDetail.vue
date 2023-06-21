<template>
  <div id="app">
    <h3>{{ dto.title }} 봉사모집게시판</h3>
    <table border="1">
      <tr>
        <td><img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/1'"></td>
        <td><img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/2'"></td>
      </tr>
    </table>
    <table border="1">
      <tr>
        <th>num</th>
        <td><input type="text" v-model="dto.num" readonly></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><input type="text" v-model="dto.title" readonly></td>
      </tr>
      <tr>
        <th>봉사장소</th>
        <td><input type="text" v-model="dto.address" readonly></td>
      </tr>
      <tr>
        <th>봉사실시일자</th>
        <td><input type="text" v-model="dto.vol_date" readonly></td>
      </tr>
      <tr>
        <th>봉사인원</th>
        <td><input type="text" v-model="dto.vol_number" readonly></td>
      </tr>
    </table>
    <button type="button" v-on:click="apply()">봉사신청</button>
    <button type="button" v-on:click="list(dto.num)">봉사참여자목록</button>
    {{ count }} / {{ dto.vol_number }}
  </div>
</template>
  
<script>
export default {
  name: 'VolBoardDetail',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      num: this.$route.query.num,
      dto: {},
      count: 0
    }
  },
  methods: {
    detail(num) {
      this.$router.push({ name: 'VolBoardDetail', params: { num: num } })
    },
    apply() {
      const self = this
      self.$axios.get('http://localhost:8082/participants/' + self.loginId + '/' + self.num)
        .then(function (res) {
          if (res.status == 200) {
            alert(res.data.list)
            if (res.data.list.length === 0) {
              let formData = new FormData();
              formData.append('boardnum', self.num)
              formData.append('id', self.loginId)
              self.$axios.post('http://localhost:8082/participants', formData)
                .then(function (res) {
                  if (res.status == 200) {
                    alert(res.data.member.id + "님이 신청되셨습니다.")
                  } else {
                    alert("에러코드:" + res.status)
                  }
                })
            } else {
              alert('이미 신청되었습니다.')
              
              // let formData2 = new FormData();
              // formData2.append('boardnum', self.num)
              // formData2.append('id', self.loginId)
              
              self.$axios.delete('http://localhost:8082/participants?boardnum=' + self.num + '&id=' + self.loginId)
                .then(function (res) {
                  if (res.status == 200) {
                    alert("신청이 취소되었습니다.");
                  } else {
                    alert("신청 취소 처리 에러");
                  }
                });
            }
          }
        })
    },
    list(num) {
      this.$router.push({ name: 'ParticipantsList', params: { num: num } })
    }
  },
  created: function () {//이 컴포넌트가 시작될때 실행되는 함수
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/volboard/' + self.num)
      .then(function (res) {
        if (res.status == 200) {
          self.dto = res.data.dto
        } else {
          alert('에러')
        }
      })

    self.$axios.get('http://localhost:8082/participants/boardnum/' + self.num)
      .then(function (res) {
        if (res.status == 200) {
          self.count = res.data.count
        } else {
          alert('에러')
        }
      })
  }
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
}</style>
  