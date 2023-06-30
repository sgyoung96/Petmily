<template>
  <div>
    <h3>입양 신청 목록</h3>
    <table border="1">
      <tr>
        <th>num</th>
        <th>id</th>
        <th>wdate</th>
        <th>reason</th>
        <th>feeding</th>
        <th>petCd</th>
      </tr>
      <tr v-for="(apply, index) in list" :key="index">
        <td>{{apply.num}}</td>
        <td>{{ apply.id.id }}</td>
        <td>{{ apply.wdate }}</td>
        <td>{{ apply.reason }}</td>
        <td>{{ apply.feeding }}</td>
        <td>{{ apply.petCd }}</td>
        <td>{{apply.ischeck}}</td>
        <td>
          <button @click="apply_form(apply.num)" style="background-color:#FFD65B; border-radius:10px;">승인</button>
          <button @click="refuse_form(apply.num)" style="background-color:#FFD65B; border-radius:10px;">거부</button>
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
    apply_form(num) {
      console.log(num);
      const self = this;
      //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
      self.$axios.patch('http://localhost:8082/Applyform/apply/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.list = res.data.list;
          } else {
            alert('에러코드' + res.status);
          }
        });
    },
    refuse_form(num) {
      console.log(num);
      const self = this;
      //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
      self.$axios.patch('http://localhost:8082/Applyform/refuse/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.list = res.data.list;
          } else {
            alert('에러코드' + res.status);
          }
        });
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>

