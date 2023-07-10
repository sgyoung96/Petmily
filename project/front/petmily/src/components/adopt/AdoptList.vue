<template>
  <img class="t-img" src="../../assets/images/배경.png">
<div class="d-title">
  <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
      &nbsp;<span style="color:rgb(244, 191, 79);">ADOPT</span></strong></h4>
</div>
<div class="d-all">
  <div class="cnt-list">
  <div v-for="(adopt, index) in list" :key="adopt.num">
    <div v-if="index < 16" class="list-all" v-on:click="$event => detail(adopt.num)" @mouseover="zoomIn" @mouseleave="zoomOut">
      <a><img class="list-img" :src="'http://localhost:8082/adopt/imgs/' + adopt.num + '/1'"></a>
      <div class="b-txt">
        <div class="b-title">
          {{ adopt.title }}
        </div>
        <div class="b-id">
          <span>
            작성자: {{ adopt.id.id }}
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

  <div id="slider">
    <div v-for="(adopt, index) in paginatedList" :key="adopt.num" class="slider-item"
      :class="{ 'new-row': index % itemsPerRow === 0 }">
      <div class="img-box" v-on:click="$event => detail(adopt.num)">
        <a><img class="b-img" :src="'http://localhost:8082/adopt/imgs/' +adopt.num+ '/1'"></a>
        <div class="b-txt">
          <div class="b-title">
            {{ adopt.title }}
          </div>
          <div class="b-id">
            <span>
              작성자 : {{ adopt.id.id }}
            </span>
            <span>
              <img class=l-img src="../../assets/images/heart.png" style="width: 15px; height: 15px;">{{ adopt.likecnt }}
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
      <button @click="$router.push('/adopt/addform')">글쓰기</button>
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

<script>

export default {
name: 'adoptList',
data () {
  return {
    loginId: sessionStorage.getItem('loginId'),
    list:[],
    itemsPerRow: 4,
    searchKeyword: '',
    currentPage: 1,
    pageSize: 12
    
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
},
methods: {
  zoomIn(event) {
    event.currentTarget.style.transform = 'scale(1.1)';
  },
  zoomOut(event) {
    event.currentTarget.style.transform = 'scale(1.0)';
  },
  detail: function(num) {
    this.$router.push({name: 'AdoptDetail', query: {num: num}})
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

.img-box {
border: 1px solid silver;
cursor: pointer;
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