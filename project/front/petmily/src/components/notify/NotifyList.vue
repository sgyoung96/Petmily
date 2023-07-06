<template>
    <div id="notify_container" >
        <h1>HERE IT IS!!</h1>
        <div v-for="(list, index) in this.list" :key="index">
          <table>
            <tr>
              <td>
                {{list.header}} {{list.content}} {{list.tr_date}}
              </td>
              <td>
                {{list.title}}
              </td>
              <td>
                {{list.content}}
              </td>
              <td>
                {{list.tr_date}}
              </td>
            </tr>
          </table>
        </div>
        <label @click="goNotifyBox">알림함으로 이동</label>
    </div>
</template>

  <script>

export default {
  name: 'NotifyList',
  props: {
    isBedgeClicked: String
  },
  data () {
    return {
      id: sessionStorage.getItem('loginId'),
      list: [],
      listStr: '',
      isClickNotifyBoxOpen: false,
      isclicked: '0'
    }
  },
  created: function () {
    this.getNewNotification();
    this.isclicked = this.isBedgeClicked;
    console.log('clicked : ' + this.isclicked);
  },
  methods:{
    getNewNotification() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/new/' + self.id)
        .then(function(res){
            //console.log(res);
            self.list = res.data.list;
        })
    },
    goNotifyBox() {
      this.isClickNotifyBoxOpen = true;
      this.$emit('childEvent', this.isClickNotifyBoxOpen);
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#notify_container {
  width: 800px;
  height: 500px;
  background: white;
  border: 1px solid black;
}

</style>
