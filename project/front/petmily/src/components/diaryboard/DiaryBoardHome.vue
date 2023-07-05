<template>
  <img class="t-img" src="../../assets/images/배경.png">
  <div class="d-title">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">DIARY</span></strong></h4>
  </div>
  <img class="m-img" src="../../assets/images/dboardpic.png">
  <div class="d-all">
    <div id="slider">
      <div v-for="(dboard, index) in paginatedList" :key="dboard.num" class="slider-item"
        :class="{ 'new-row': index % itemsPerRow === 0 }">
        <div class="img-box" v-on:click="$event => detail(dboard.num)">
          <a><img class="b-img" :src="'http://localhost:8082/dboard/imgs/' + dboard.num + '/1'"></a>
          <div class="b-txt">
            <div class="b-title">
              {{ dboard.title }}
            </div>
            <div class="b-id">
              <span>
                작성자 : {{ dboard.id.id }}
              </span>
              <span>
                <img class=l-img src="../../assets/images/heart.png" style="width: 15px; height: 15px;">{{ dboard.likecnt
                }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="list-box">
      <span>
        <button @click="goToFullList">전체목록</button>
      </span>
      <span>
        <button @click="$router.push('/diaryboardadd')">글쓰기</button>
      </span>
    </div>
    <div>
      <ul class="pagination" style="display: inline-block">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous" @click="previousPage">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item" v-for="pageNumber in totalPages" :key="pageNumber"
          :class="{ active: pageNumber === currentPage }">
          <a class="page-link" href="#" @click="goToPage(pageNumber)">{{ pageNumber }}</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next" @click="nextPage">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="search-box">
      <input type="text" id="searchKeyword" v-model="searchKeyword" placeholder="제목을 입력해주세요">
      <button id="search_btn" @click="searchByTitle()">검색</button>
    </div>
  </div>
</template>
<style scoped>
.t-img {
  width: 85%;
  height: 500px;
  margin-bottom: 20px;
}

.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}
.m-img {
  width: 1210px;
  height: 160px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.d-all {
  padding-left: 150px;
  padding-right: 150px;
}

.img-box {
  border: 1px solid silver;
  cursor: pointer;
  width:293px;
  height:260px;
}

.b-img {
  width: 293px;
  height:200px;
}

.b-txt {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.b-title {
  font-size: large;
}

.b-id {
  font-size: medium;
  display: flex;
  justify-content: space-between;
}

.l-img {
  width: 15px;
  height: 15px;
}

.list-box {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  margin-bottom: 10px;
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

.search-box {
  margin-bottom: 30px;
  text-align: center;
}
</style>

<script>
export default {
  name: 'DiaryboardHome',
  data() {
    return {
      loginId: null,
      list: [],
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
  created: function () {
    this.loginId = sessionStorage.getItem('loginId')
    const self = this;
    self.$axios.get('http://localhost:8082/dboard')//+self.loginId
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list
        } else {
          alert('에러코드' + res.status)
        }
      });
  },
  methods: {
    detail(num) {
      // alert(num)
      this.$router.push({ name: 'DiaryBoardDetail', query: { num: num } })
    },
    goToFullList() {
      this.$router.push('/diaryboardhome');
    },
    searchByTitle() {
      const self = this;
      alert(self.searchKeyword)
      self.$axios
        .get('http://localhost:8082/dboard/getByTitle/' + self.searchKeyword)
        .then(function (res) {
          if (res.status === 200) {
            self.list = res.data;
          } else {
            alert('에러코드' + res.status);
          }
        })
        .catch(function (error) {
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
img {
  width: 256px;
  height: 200px;
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
}</style>

