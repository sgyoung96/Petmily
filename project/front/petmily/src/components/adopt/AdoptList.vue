<template>
   <div style="position: relative">
    <img
      src="../../assets/images/고양.png"
      alt="컨택트 이미지"
      style="width: 100%; padding-bottom: 10px; position: relative"
    />
    <div class="wrapper1">ADOPTPETS
</div>
    <div class="wrapper2" sytle="font-size:50px;display:block">
      about petmily
    </div>
  </div>
<div class="d-title">
  <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
      &nbsp;<span style="color:rgb(244, 191, 79);">ADOPT</span></strong></h4>
</div>
<div class="d-all">
  <div class="cnt-list">
    <div v-if="list.length === 0">
    검색된 게시글이 없습니다.
  </div>
    <div v-else v-for="(adopt, index) in list2" :key="adopt.num">
    <div v-if="index < 16" class="list-all" v-on:click="$event => detail(adopt.num)" @mouseover="zoomIn" @mouseleave="zoomOut">
      <a><img class="list-img" :src="'http://localhost:8082/adopt/imgs/' + adopt.num + '/1'"></a>
      <div class="b-txt">
        <div class="b-title">
          {{ adopt.category }}
        </div>
        <div class="b-id">
          <span>
            분양지역: {{adopt.address}}
          </span>
          <span>
            <img class="l-img" src="../../assets/images/heart.png" style="width: 15px; height: 15px;">{{ adopt.likecnt }}
          </span>
        </div>
      </div>
    </div>
    <div v-if="(index + 1) % 8 === 0 && (index + 1) < 16">
      <br>
    </div>
  </div>
</div>
<div class="banner">
        <img style="  height:100px;
  width: 900px;  z-index: 0;" src="../../assets/images/배너.png">&nbsp;
      </div>
    <div>
      <div v-if="list.length === 0">
    검색된 게시글이 없습니다.
  </div>
      <table v-else class="table">
  <thead>
    <tr>
      <th style="width:10%">분양중</th>
      <th style="width:10%">사진</th>
      <th style="width:10%">동물종류</th>
      <th style="width:30%">상세설명</th>
      <th style="width:8%">구분</th>
      <th style="width:10%">분양지역</th>
      <th style="width:15%">작성일</th>
      <th style="width:7%">조회수</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr class="b-list" v-for="(adopt) in paginatedList" :key="adopt.num" v-on:click="$event => detail(adopt.num)">
      <td><span class="adopt" v-if="adopt.ischeck==0">분양중</span>
        <span class="adopted" v-else>분양완료</span></td>
      <td><img class="b-img" :src="'http://localhost:8082/adopt/imgs/' +adopt.num+ '/1'"></td>
      <td>{{ adopt.category }}</td>
      <td style="text-align: left;">{{ adopt.title }}</td>
      <td>{{ adopt.gender }}</td>
      <td>{{adopt.address}}</td>
      <td>{{ formatDateTime(adopt.w_date)}}</td>
      <td>{{ adopt.cnt }}</td>
    </tr>
  </tbody>
</table>
      
    </div>
  <div class="list-box">
    <span>
      <button @click="goToFullList">전체목록</button>
    </span>
    <span>
      <button @click="$router.push('/adopt/addform')" v-if="loginId != null">글쓰기</button>
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
    <input type="text" id="searchKeyword" v-model="searchKeyword" placeholder="주소를 입력해주세요">
    <button id="search_btn" @click="searchByAddress()">검색</button>
  </div>
</div>

</template>

<script>

export default {
name: 'adoptList',
data () {
  return {
    loginId: sessionStorage.getItem('loginId'),
    list:[],
    list2:[],
    itemsPerRow: 4,
    searchKeyword: '',
    currentPage: 1,
    pageSize: 15
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
created: function () {
  this.loginId = sessionStorage.getItem('loginId');
  const self = this;
  self.$axios.get('http://localhost:8082/adopt').then(function (res) {
    if (res.status == 200) {
      self.list = res.data.list;
    } else {
      alert('에러');
    }
  });
  self.$axios.get('http://localhost:8082/adopt/ol').then(function (res) {
      if (res.status == 200) {
        self.list2 = res.data.list;
      } else {
        alert('에러');
      }
    });
},
methods: {
  searchByAddress() {
      const self = this;
      let address = self.searchKeyword;
  
  if (!address) {
    alert('검색어를 입력해주세요.');
    return;
  }
      self.$axios
        .get('http://localhost:8082/adopt/getByAddress/' + self.searchKeyword)
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
  zoomIn(event) {
    event.currentTarget.style.transform = 'scale(1.1)';
  },
  zoomOut(event) {
    event.currentTarget.style.transform = 'scale(1.0)';
  },
  formatDateTime(dateTime) {
  const dateObj = new Date(dateTime);
  const year = dateObj.getFullYear();
  const month = ("0" + (dateObj.getMonth() + 1)).slice(-2);
  const day = ("0" + dateObj.getDate()).slice(-2);

  return `${year}년 ${month}월 ${day}일`;
},
detail(num) {
  this.$router.push({ name: 'AdoptDetail', query: { num: num } });
  const self = this;
  self.$axios.get('http://localhost:8082/adopt/cnt/' + num).then(function (res) {
    if (res.status === 200) {
      console.log(res.data.flag);
      // 윈도우 팝업 창 열기
    } else {
      alert('에러');
    }
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
},
components: {

}
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
.banner{
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-bottom: 50px;
  float:center;
  background-color:white;
  padding:24px 63px;
  display:flex;
  justify-content: center;
}

.banner img{
  height:100px;
  width: 900px;
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
.t-img {
width: 100%;
height: 600px;
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
font-family: 'IBMPlexSansKR-Bold';
}

.cnt-list{
  display:flex;
  flex-wrap: wrap;
  border: 1px solid silver;
  padding:5px;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
  cursor:pointer;
  margin-bottom:20px;
}

.list-all{
  margin:4px;
  border:1px solid silver;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
  
}

.list-img{
  width: 140px;
  height:100px;
  border-radius: 10px 10px 0px 0px;
}
.b-list{
  cursor:pointer;
  vertical-align:middle;
}

.adopt{
background-color:blue;
width: 70px;
    color: white;
    height: 30px;
font-weight: bold;
    border:blue;
    border-radius: 10px;
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    padding-top: 2px;
    margin-right: 5px;
    display:inline-block;
}

.adopted{
  background-color:silver;
  width: 70px;
    color: white;
    height: 30px;
font-weight: bold;
border:silver;
    border-radius: 10px;
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    padding-top: 2px;
    margin-right: 5px;
    display:inline-block;
}

.b-img {
  width: 100px;
  height:80px;
  border-radius: 10px;
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
font-size: small;
color:grey;
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