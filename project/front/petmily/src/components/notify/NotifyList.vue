<template>
    <div id="notify_container" >
        
        <div class="table-container">
          <table>
            <tr v-for="(list, index) in this.list" :key="index">
              <td>
                {{list.header}}
              </td>
              <td width="400px;">
                {{list.title}}
              </td>
              <td class="content">
                {{list.content}}
              </td>
              <td width="130px;">
                <div :id="index">{{this.dateList[index]}}</div>
              </td>
            </tr>
          </table>
        </div>
        
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
      dateList: [],
      numList: [],
      isClickNotifyBoxOpen: false,
      isclicked: '0'
    }
  },
  created: function () {
    this.getNewNotification();
    this.isclicked = this.isBedgeClicked;
    console.log('clicked : ' + this.isclicked);
  },
  mounted: function() {
    
  },
  methods:{
    getNewNotification() {
      const self = this;
      self.$axios.get('http://localhost:8082/notify/new/' + self.id)
      .then(function(res){
          //console.log(res);
          self.list = res.data.list;
          console.log(self.list)
          console.log(self.list.length);
          for (let i = 0; i < self.list.length; i++) {
            self.dateList.push(self.list[i].tr_date.split('T')[0]);
            self.numList.push(self.list[i].num);
          }
          self.$emit('childEvent', self.numList);
      })
    },
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#notify_container {
  width: 800px;
  height: 500px;
  background: white;
  justify-content: center;
  cursor: pointer;
}

.table-container {
  width: 800px;
  height: 500px;
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  display: block;
  position: relative;
  overflow-y: scroll;
  overflow: auto;
  margin-bottom: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.table-container::-webkit-scrollbar {
  width: 0.5em;
}

.table-container::-webkit-scrollbar-track {
  background-color: #00000000;
}

.table-container::-webkit-scrollbar-track {
  background-color: #00000000;
}

table {
  margin-top: 50px;
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
}

.content {
  font-family: 'IBMPlexSansKR-Regular';
  margin-right: 20px;
}

.lbl-go-btn {
  padding-top: 5px;
  background: rgb(244, 191, 79);
  color: white;
  width: 100px;
  height: 30px;
  font-size: 13px;
  font-family: 'IBMPlexSansKR-Medium';
  border: 1px solid;
  border-radius: 10px;
  transform: translateY(100%);
  margin-bottom: 50px;
}
</style>
