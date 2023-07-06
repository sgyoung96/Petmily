<template>
  <img class="t-img" src="../../assets/images/배경.png">
  <div class="d-title">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">DIARY</span></strong></h4>
  </div>
  <!-- <img class="m-img" src="../../assets/images/dboardpic.png"> -->
  <div class="d-all">
    <div id="slider">
      <div v-for="dboard in paginatedList" :key="dboard.num" class="slider-item">
        <div class="img-box" @mouseover="zoomIn" @mouseleave="zoomOut" v-on:click="$event => detail(dboard.num)">
          <a><img class="b-img" :src="'http://localhost:8082/dboard/imgs/' + dboard.num + '/1'"></a>
          <div class="b-txt">
            <div class="b-title">
              <strong>{{ dboard.title }}</strong>
            </div>
            <div class="b-content">
                {{ dboard.content.length > 35 ? dboard.content.substring(0, 35) + '...' : dboard.content }}
              </div>
              <div style="font-size:small; display:flex; justify-content: space-between;">
                <span>
                <img class=l-img src="../../assets/images/heart.png" style="width: 15px; height: 15px;">
                {{ dboard.likecnt }}
              </span>
              <span style="color:rgb(156, 156, 39);">
                +더보기
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
      <a class="page-link" href="#" aria-label="Previous" @click.prevent="previousPage">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item" v-for="pageNumber in totalPages" :key="pageNumber" :class="{ active: pageNumber === currentPage }">
      <a class="page-link" href="#" @click.prevent="goToPage(pageNumber)">{{ pageNumber }}</a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next" @click.prevent="nextPage">
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
@import url('https://fonts.googleapis.com/css2?family=Single+Day&display=swap');
button{
  width: 100px;
    color: white;
    height: 30px;
    border: 1px solid rgb(244, 191, 79);
    border-radius: 20px;
    background-color: rgb(244, 191, 79);
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    padding-top: 2px;
    margin-right: 5px;
}
button:hover {
  background-color: rgb(235, 156, 39);
  cursor: pointer;
}
.pagination {
  display: inline-block;
  margin-top: 20px;
  margin-bottom: 20px;
}

.page-item {
  display: inline-block;
  margin-right: 5px;
}

.page-item a {
  color: black;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  text-decoration: none;
}

.page-item a:hover {
  background-color: #f2f2f2;
}

.page-item.active a {
  background-color: rgb(244, 191, 79);
  color: white;
}

.search-box {
  margin-bottom: 30px;
  text-align: center;
}

.search-box input[type="text"] {
  width: 300px;
  height: 30px;
  padding: 5px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.search-box button {
  width: 80px;
  height: 30px;
  color: white;
  border: none;
  border-radius: 10px;
  background-color: rgb(244, 191, 79);
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 15px;
  margin-left: 5px;
}

.search-box button:hover {
  background-color: rgb(235, 156, 39);
  cursor: pointer;
}
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
  border-radius: 10px;
  margin-right:10px;
}

.d-all {
  padding-left: 150px;
  padding-right: 150px;
}

.img-box {
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  border-radius: 10px;
  cursor:pointer;
  height:316px;
  width:288px;
}
.b-img {
  border-radius: 10px 10px 0px 0px;
  width: 288px;
  height:214px;
  border-bottom:1px solid silver;
}

.b-txt {
  text-align: left;

  padding:7px 10px;
}

.b-title {
  font-size: large;
  font-family:'Single Day', cursive;
}

.b-content {
  font-size: small;
  color:silver;
  width: 276px;
  height: 39px;
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
    zoomIn(event) {
    event.currentTarget.style.transform = 'scale(1.05)';
  },
  zoomOut(event) {
    event.currentTarget.style.transform = 'scale(1)';
  },
    detail(num) {
      // alert(num)
      this.$router.push({ name: 'DiaryBoardDetail', query: { num: num } })
    },
  goToFullList() {
  this.searchKeyword = ''; // Clear the search keyword
  this.$axios.get('http://localhost:8082/dboard') //+ self.loginId
    .then((res) => {
      if (res.status === 200) {
        this.list = res.data.list;
        this.currentPage = 1; // Reset the current page to the first page
      } else {
        alert('에러코드' + res.status);
      }
    })
    .catch((error) => {
      console.log(error);
    });

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

