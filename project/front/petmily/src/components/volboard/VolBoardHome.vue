<template>
  <div style="position: relative">
    <img
      src="../../assets/images/강고.png"
      alt="컨택트 이미지"
      style="width: 100%; padding-bottom: 10px; position: relative"
    />
    <div class="wrapper1">VOLUNTEER
</div>
    <div class="wrapper2" sytle="font-size:50px;display:block">
      about petmily
    </div>
  </div>
  <div style="font-family: 'IBMPlexSansKR-Bold';">
  <div class="v-title" @click="goHome" style="cursor: pointer;">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">VOLUNTEER</span></strong></h4>
  </div>
  <div style="padding-left: 150px;padding-right:150px">
    <div class="search-box">
      <div>
        <button @click="goToFullList">전체목록</button>
      </div>
      <div>
      <input type="text" @keyup.enter="search()" v-model="searchKeyword" placeholder="주소를 입력해주세요">
      <button v-on:click="search()">검색</button>
    </div>
    </div>
    <div style="border-top:2px solid black; padding-top:10px; margin-top:10px">
      <div v-if="list.length === 0">
    검색된 게시글이 없습니다.
  </div>
      <div v-else class="vbody" v-for="vboard in paginatedList" :key="vboard.num" @click="detail(vboard.num, vboard.address)">
        <div style="text-align: left;">
          <span class="badge text-bg-danger" style="font-size: 17px;"
            v-if="calculateDateDifference(vboard.deadline).difference < 0">모집마감</span>
          <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
            calculateDateDifference(vboard.deadline).days }}</span>&nbsp;
          <strong>모집기간</strong> {{ formatDate(vboard.deadline) }}까지 <strong>조회수</strong> {{ vboard.cnt }}
          <br />
          <div class=vtitle>{{ vboard.title }}</div>
          <div style="margin-bottom: 10px;">
            <strong>[모집기관]</strong> {{ vboard.place }} &nbsp;
            <strong>[봉사날짜]</strong> {{ formatDate(vboard.vol_date) }} &nbsp;<br />
            <strong>[봉사장소]</strong> {{ vboard.address }}
          </div>
        </div>
        <div style class="vcount">
          모집인원<br />({{ vboard.count }} / {{ vboard.vol_number }})
        </div>
      </div>
</div>
    </div>
  </div>
  <ul class="pagination" style="display: inline-block">
    <!-- 이전 페이지 버튼 -->
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous" @click="previousPage">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <!-- 페이지 번호 -->
    <li class="page-item" v-for="pageNumber in totalPages" :key="pageNumber"
      :class="{ active: pageNumber === currentPage }">
      <a class="page-link" href="#" @click="goToPage(pageNumber)">{{ pageNumber }}</a>
    </li>
    <!-- 다음 페이지 버튼 -->
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next" @click="nextPage">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'VolBoardHome',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      list: [],
      count: 0,
      state: '',
      sysdate: new Date(),
      currentPage: 1,
      pageSize: 8,
      searchKeyword: ''
    }
  },
  mounted() {
    window.scrollTo({ top: 0, behavior: 'auto' });
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
  methods: {
    goToFullList() {
  this.searchKeyword = ''; // Clear the search keyword
  this.$axios.get('http://localhost:8082/volboard') //+ self.loginId
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

  this.$router.push('/volboardhome');
},
    goHome() {
      location.href = '/volboardhome';
    }, 
    search() {
  const self = this;
  let address = self.searchKeyword;
  
  if (!address) {
    alert('검색어를 입력해주세요.');
    return;
  }

  self.$axios.get('http://localhost:8082/volboard/address/' + address)
    .then(function (response) {
      if (response.status == 200) {
        self.list = response.data.list;
      } else {
        alert('에러');
      }
    });
},
    detail(num, address) {
      const self = this
      self.$axios.get('http://localhost:8082/volboard/cnt/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.$router.push({
              name: 'VolBoardDetail',
              query: { num: num, address: address },
            })
          }else{
            alert('디테일에러')
          }
        })
    },
    calculateDateDifference(deadline) {
      const deadlineDate = new Date(deadline)
      const difference = deadlineDate.getTime() - this.sysdate.getTime()
      const days = Math.ceil(difference / (1000 * 60 * 60 * 24))
      return { difference, days }
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
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
  },
  created: function () {
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/volboard').then(function (res) {
      if (res.status == 200) {
        self.list = res.data.list
      } else {
        alert('에러')
      }
    })
  },
}
</script>

<style scoped>
img {
  z-index: -1;
}
.wrapper1 {
  display: block;
  font-family: "IBMPlexSansKR-Bold";
  color: rgba(255, 255, 255, 0.374);
  position: absolute;
  font-size: 120px;
  width: 100%;
  top: 40px;
  text-align: left;
  left: 50px;
  z-index: 0;
}

.wrapper2 {
  display: block;
  font-family: "IBMPlexSansKR-Bold";
  color: rgba(255, 255, 255, 0.374);
  position: absolute;
  font-size: 60px;
  width: 100%;
  top: 160px;
  text-align: left;
  left: 50px;

  z-index: 0;
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
  display:flex;
  justify-content: space-between;
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
.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}

.vbody {
  border-bottom: 1px solid #666666;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  cursor: pointer;
}

.vcount {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 70px;
  height: 70px;
  border: 2px solid #666666;
  font-size: 15px;
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
}

.vtitle {
  font-size: 30px;
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