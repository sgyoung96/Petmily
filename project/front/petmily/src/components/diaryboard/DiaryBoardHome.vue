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
		<option value="1">제목</option>
		<option value="2">작성자</option>
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
      <div id="slider">
    <div v-for="(dboard, index) in list" :key="dboard.num" class="slider-item" :class="{ 'new-row': index % itemsPerRow === 0 }">
      <div style="border: 1px solid silver">
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
      list:[],
      itemsPerRow: 4
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
