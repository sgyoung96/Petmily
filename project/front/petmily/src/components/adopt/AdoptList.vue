<template>
  <div>
      <h3>분양게시판 목록</h3>
      <router-link to="/adopt/addform">글쓰기</router-link>
      
  </div>
</template>

<script>

export default {
  name: 'adoptList',
  data () {
    return {
        
    }
  },
  created: function () {

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

</style>
