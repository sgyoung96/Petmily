<template>
  <div>
    <img src="../../assets/images/vboard2.jpg" style="width: 85%; height: 500px; margin-bottom: 20px;">
  </div>
    <h3>봉사모집게시판 전체목록</h3>
    <div class="container text-center">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-10">
    <router-link to="/volboardadd">봉사게시판 작성</router-link>
    <input type="text" placeholder="검색어를 입력하시오">
    <button v-on:click="search(title)">검색</button>
      <div class="vbody" v-for="vboard in list" :key="vboard.num">
        <div style="text-align: left;">
  <span class="badge text-bg-primary" style="font-size: 17px;">
    <span v-if="calculateDateDifference(vboard.deadline).difference < 0">모집마감</span>
    <span v-else>마감 D-{{ calculateDateDifference(vboard.deadline).days }}</span>
  </span>&nbsp;
  <strong>모집기간</strong> {{ formatDate(vboard.deadline) }}까지
  <br/>
  <div class=vtitle v-on:click="detail(vboard.num, vboard.address)">{{ vboard.title }}</div>
  <div style="margin-bottom: 10px;">
  <strong>[모집기관]</strong> {{ vboard.place }} &nbsp;
  <strong>[봉사날짜]</strong> {{ formatDate(vboard.vol_date) }} &nbsp;<br/>
  <strong>[봉사장소]</strong> {{ vboard.address }}
</div>
</div>
        <div style class="vcount">
          모집인원<br/>({{ vboard.count }} / {{ vboard.vol_number }})
      </div>
      </div>
    </div>
    <div class="col">
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
  name: 'VolBoardHome',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      list: [],
      count: 0,
      state: '',
      sysdate: new Date(),
      currentPage: 1,
      pageSize: 8
    }
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
    detail(num, address) {
      this.$router.push({
        name: 'VolBoardDetail',
        query: { num: num, address: address },
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
.image {
  width: 100%;
  height: 500px;
  display:inline-block;
  z-index: -1;
  padding-left: -50px;
  padding-right: -50px;
  opacity: 0.3;
  border-radius: 30px;
  filter: blur(5px);
}
.vbody{
  border-bottom: 1px solid #666666;
  display: flex;
  justify-content: space-between;
  margin-bottom:10px;
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
  margin-top:20px;
}
.vtitle{
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