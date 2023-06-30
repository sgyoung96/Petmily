<template>
  <div>
    <h3>입양 신청 목록</h3>
    <table border="1">
      <tr>
        <th>id</th>
        <th>wdate</th>
        <th>reason</th>
        <th>feeding</th>
        <th>petCd</th>
      </tr>
      <tr v-for="apply in list" :key="apply.num">
        <td>{{ apply.id.id }}</td>
        <td>{{ apply.wdate }}</td>
        <td>{{ apply.reason }}</td>
        <td>{{ apply.feeding }}</td>
        <td>{{ apply.petCd }}</td>
        <td>
          <button v-on:click="apply" style="background-color:#FFD65B; border-radius:10px;">승인</button>
          <button v-on:click="refuse" style="background-color:#FFD65B; border-radius:10px;">거부</button>
        </td>
      </tr>
    </table>

  </div>
</template>

<script>

export default {
  name: 'AdminAppliedList',
  data() {
    return {
      list: []
    }
  },
  created: function () {
    const self = this;
    self.$axios.get('http://localhost:8082/Applyform')
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list
        } else {
          alert('에러코드' + res.status)
        }
      });
  },
  methods: {
    methods: {
      apply() {
        const self = this;
        const applyNum = this.apply.num; // apply.num 값을 가져옴
        self.$axios.patch('http://localhost:8082/Applyform/apply/' + applyNum)
          .then(function (res) {
            if (res.status == 200) {
              self.list = res.data.list;
            } else {
              alert('에러코드' + res.status);
            }
          });
      },
      refuse() {
        const self = this;
        const applyNum = this.apply.num; // apply.num 값을 가져옴
        self.$axios.patch('http://localhost:8082/Applyform/refuse/' + applyNum)
          .then(function (res) {
            if (res.status == 200) {
              self.list = res.data.list;
            } else {
              alert('에러코드' + res.status);
            }
          });
      }
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
