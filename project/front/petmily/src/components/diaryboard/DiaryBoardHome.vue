<template>
  <div>
    <img src="../../assets/images/dboardpic2.jpg" style="width: 85%; height: 500px; margin-bottom: 20px;">
  </div>
  <div style="float:center; margin:50px"><h1>입양일지</h1></div>
  <div>
    <img src="../../assets/images/dboardpic.png" style="width: 1200px; height: 160px; margin-bottom: 20px;">
  </div>
  <div class="container text-center">
  <div class="row" style="margin-bottom: 40px;">
    <div class="col-1">
    </div>
    <div class="col-10">
      <div class='box-body' style="float:right">
      <select id='search_type'>
        <option value="1">제목</option>
        <option value="2">작성자</option>
      </select>
      <input type="text" id="searchKeyword" v-model="searchKeyword">
      <button id="search_btn" @click="search()">검색</button>
      <button @click="$router.push('/diaryboardadd')">글쓰기</button>
</div>
    </div>
    <div class="col-1">
    </div>
  </div>
</div>
<div class="container text-center">
  <div class="row">
    <div class="col-1"></div>
    <div class="col-10">
      <div id="slider">
        <div
            v-for="(dboard, index) in paginatedList"
            :key="dboard.num"
            class="slider-item"
            :class="{ 'new-row': index % itemsPerRow === 0 }"
          >      <div style="border: 1px solid silver">
      <div>
      <a v-on:click="$event=>detail(dboard.num)"><img :src="'http://localhost:8082/dboard/imgs/' + dboard.num + '/1'"></a>
    </div>
    <div style="float:left">
    {{ dboard.title }}
    </div><br/>
    <div style="color: grey; float:left; font-size:small">
    작성자 : {{ dboard.id.id }}
     </div><br/>
     </div>
    </div>
  </div>
    </div>
    <div class="col-1">
    </div>
  </div>
  </div>

  <ul class="pagination" style="display: inline-block">
    <!-- 이전 페이지 버튼 -->
    <li class="page-item">
      <a
        class="page-link"
        href="#"
        aria-label="Previous"
        @click="previousPage"
      >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <!-- 페이지 번호 -->
    <li
      class="page-item"
      v-for="pageNumber in totalPages"
      :key="pageNumber"
      :class="{ active: pageNumber === currentPage }"
    >
      <a class="page-link" href="#" @click="goToPage(pageNumber)">{{ pageNumber }}</a>
    </li>
    <!-- 다음 페이지 버튼 -->
    <li class="page-item">
      <a
        class="page-link"
        href="#"
        aria-label="Next"
        @click="nextPage"
      >
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>

</template>

<script>
export default {
  name: 'DiaryboardHome',
  data () {
    return {
      loginId:null,
      list:[],
      itemsPerRow: 4,
      searchKeyword: '',
      currentPage: 1,
      pageSize: 12
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.list.length / this.pageSize);
    },
    paginatedList() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.list.slice(startIndex, endIndex);
    }
  },
  created:function(){
      this.loginId = sessionStorage.getItem('loginId')
      const self = this;
      self.$axios.get('http://localhost:8082/dboard')//+self.loginId
      .then(function(res){
          if(res.status == 200){
              self.list = res.data.list
          }else{
              alert('에러코드'+res.status)
          }
      });
  },
  methods:{
    detail(num){
      // alert(num)
      this.$router.push({name:'DiaryBoardDetail', query:{num:num}})
    },
    search() {
      const searchType = document.getElementById('search_type').value;
      if (searchType === '1') {
        this.searchByTitle();
      } else if (searchType === '2') {
        this.searchById();
      }
    },
    searchByTitle() {
      const self = this;
      alert(self.searchKeyword)
      self.$axios
        .get('http://localhost:8082/dboard/getByTitle/' + self.searchKeyword)
        .then(function(res) {
          if (res.status === 200) {
            self.list = res.data;
          } else {
            alert('에러코드' + res.status);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    searchById() {
      const self = this;
      self.$axios
        .get('http://localhost:8082/dboard/getById', {
          params: {
            id: self.searchKeyword
          }
        })
        .then(function(res) {
          if (res.status === 200) {
            self.list = res.data;
          } else {
            alert('에러코드' + res.status);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    goToPage(pageNumber) {
      this.currentPage = pageNumber;
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img{
  width:256px;
  height:200px;
}
#slider {
  display: flex;
  flex-wrap: wrap;
}
.slider-item {
  flex: 0 0 calc(25% - 10px);
  margin-right: 10px;
  margin-bottom: 10px;
}

.new-row {
  clear: both;
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
  }
</style>