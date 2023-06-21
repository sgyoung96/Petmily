<template>
  <div class="container text-center">
  <div class="row" style="margin-bottom: 40px;">
    <div class="col">
      <h2>입양일지</h2>
    </div>
    <div class="col">
    </div>
    <div class="col">
<div class='box-body'>
	<select id='search_type'>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	<input type="text" id="searchKeyword">
	<button id="search_btn">검색</button>
</div>
<router-link to="/diaryboardadd">글쓰기</router-link>
    </div>
  </div>
</div>
<div class="container text-center">
  <div class="row">
    <div class="col-1">
    </div>
    <div class="col-10">
      <table class="table">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">사진</th>
      <th scope="col">글제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성날짜</th>
      <th scope="col">조회수</th>
      <th scope="col">추천수</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr v-for="prod in list" :key="prod.num">
      <th scope="row">{{ prod.num }}</th>
      <td><img :src="'http://localhost:8082/dboard/imgs/' + prod.num + '/1'"></td>
      <td><a v-on:click="$event=>detail(prod.num)">{{ prod.title }}</a></td>
      <td>{{ prod.id.id }}</td>
      <td>{{ prod.w_date }}</td>
      <td>조회수</td>
      <td>추천수</td>
    </tr>
  </tbody>
</table>
    </div>
    <div class="col-1">
    </div>
  </div>
  </div>
  <nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</template>

<script>
export default {
  name: 'DiaryboardHome',
  data () {
    return {
      loginId:null,
      list:[]
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
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img{
  width:100px;
  height:100px;
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
