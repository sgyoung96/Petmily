<template>
  <div>
      <h3>회원 목록 조회</h3>
      
    <div class="member-list">
        <br>id: <input id="id" v-model="dto.id">
        <br>token: <input id="token" v-model="dto.token">
        <br>pwd: <input id="pwd" v-model="dto.pwd">
        <br>name: <input id="name" v-model="dto.name">
        <br>email: <input id="email" v-model="dto.email">
        <br>birth: <input id="birth" v-model="dto.birth">
        <br>gender: <input id="gender" v-model="dto.gender">
        <br>phone: <input id="phone" v-model="dto.phone">
        <br>address: <input id="addrss" v-model="dto.address">
    </div>
  </div>
</template>

<script>

export default {
  name: 'meberList',
  data () {
    return {
        id: '',
        token: '',
        pwd: '',
        name: '',
        email: '',
        birth: '',
        gender: '',
        phone: '',
        address: '',
        dto: {}
    }
  },
  created: function () {
    const self = this;

    self.$axios.get('http://localhost:8082/members/1').then (function(rs) {
        self.dto = rs.data.dto;
    })
  },
  mounted() {
    window.scrollTo({ top: 0, behavior: 'auto' });
  },
  methods: {
    addPost: function() {
      //alert('test') // 테스트용 알럿
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .member-list {
        text-align: left;
    }
</style>
