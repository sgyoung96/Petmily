<template>
    <div>
        <h1>HERE IT IS!!</h1>
    </div>
</template>

  <script>

export default {
  name: 'NotifyList',
  data () {
    return {
      id: sessionStorage.getItem('loginId'),
      list: [],
      listStr: '',
    }
  },
  created: function () {
    this.getNewNotification();
  },
  methods:{
    getNewNotification() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/new/' + self.id)
        .then(function(res){
            //console.log(res);
            self.lsit = res.data.list;
            self.listStr = self.list.toString;
            console.log(self.listStr);
        })
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



</style>
