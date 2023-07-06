<template>
  <div>
    <div class="container">
        <div v-for="(list, index) in this.allList" :key="index">
            <div>
                <ul class="list-starter">
                    <li>
                        {{list.num}}
                    </li>
                    <li>
                        {{list.header}}
                    </li>
                    <li>
                        {{list.content}}
                    </li>
                    <li>
                        {{list.row_num}}
                    </li>
                    <li>
                        {{list.title}}
                    </li>
                    <li>
                        {{list.writer}}
                    </li>
                    <li>
                        {{list.commenter}}
                    </li>
                    <li>
                        {{list.notify_type}}
                    </li>
                    <li>
                        {{list.is_clicked}}
                    </li>
                    <li>
                        {{list.tr_date}}
                    </li>
                </ul>
            </div>
        </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'AlertBox',
  data () {
    return {
        id: sessionStorage.getItem('loginId'),
        allList: [],
        adoptList: [],
        diaryList: [],
        messageList: [],

        num: '',
        header: '',
        content: '',
        row_num: '',
        title: '',
        writer: '',
        commenter: '',
        notify_type: '',
        is_clicked: '',
        tr_date: '',
    }
  },
  created: function () {
      this.getBox();
  },
  methods: {
    getBox() { // 전체 데이터 통신  
        this.getAllList();
        this.getAdoptList();
        this.getDiaryList();
        this.getMessageList();
    },
    getAllList() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/all/' + self.id)
        .then(function(res){
            //console.log(res);
            self.allList = res.data.list;
        })
    },
    getAdoptList() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/adopt/' + self.id)
        .then(function(res){
            //console.log(res);
            self.adoptList = res.data.list;
        })
    },
    getDiaryList() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/diary/' + self.id)
        .then(function(res){
            //console.log(res);
            self.diaryList = res.data.list;
        })
    },
    getMessageList() {
        const self = this;
        self.$axios.get('http://localhost:8082/notify/message/' + self.id)
        .then(function(res){
            //console.log(res);
            self.diaryList = res.data.list;
        })
    }
  },
  components: {
     
  }
}
</script>
  
<style scoped>
.container {
    padding-top: 50px;
    padding-bottom: 100px;
    display: grid;
    position: relative;
}

.ist-starter {
    display: grid;
}

</style>
