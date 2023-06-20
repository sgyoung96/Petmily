<template>
  <div>
    <div class="container">
        <LoginForm class="form-login"/>
    </div>
  </div>
</template>

<script>
import LoginForm from '../components/Member/MemLogin.vue'

export default {
  name: 'MemberView',
  data () {
    return {
        
    }
  },
  created: function () {

  },
  methods: {
    
  },
  components: {
      LoginForm
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 50px;
    padding-bottom: 100px;
}

</style>
