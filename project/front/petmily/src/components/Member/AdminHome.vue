<template>
  <div>
    <div class="wrap">
      <div class="tab_menu">
        <ul class="list">
          <li @click="initTabs()" class="is_on li-tab01">
            <div class="tab01"><a href="#tab1" class="btn"><span class="tab-a">PETMILY</span><span class="tab-b">VOLUNTEER</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab02">
            <div class="tab02"><a href="#tab2" class="btn"><span class="tab-a">PETMILY</span><span class="tab-b">ADOPTFORM</span></a></div>
          </li>
        </ul>
        
        <div class="cont_area">
          <div class="line"></div>
          <div id="tab1" class="cont">
            <div class="tab1-content content"><LoginForm /></div>
          </div>
          <div id="tab2" class="cont cont2">
            <div class="tab2-content content"><ApplyListVue /></div>
          </div>
        </div>

      </div>

     
    </div>
  </div>
</template>

<script>
import LoginForm from '../Member/MemLogin.vue';
import ApplyListVue from '../admin/ApplyList.vue';

export default {
  name: 'AdminHome',
  data () {
    return {
        
    }
  },
  created: function () {
    this.initTabs();
  },
  methods: {
    initTabs() {
      const tabList = document.querySelectorAll('.tab_menu .list li');
      const contents = document.querySelectorAll('.tab_menu .cont_area .cont')
      let activeCont = 'tab1'; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

      for(var i = 0; i < tabList.length; i++){
        tabList[i].querySelector('.btn').addEventListener('click', function(e){
          e.preventDefault();
          for(var j = 0; j < tabList.length; j++){
            // 나머지 버튼 클래스 제거
            tabList[j].classList.remove('is_on');

            // 나머지 컨텐츠 display:none 처리
            contents[j].style.display = 'none';
          }

          // 버튼 관련 이벤트
          this.parentNode.classList.add('is_on');

          // 버튼 클릭시 컨텐츠 전환
          activeCont = this.getAttribute('href');
          console.log(this.getAttribute('href'));
          document.querySelector(activeCont).style.display = 'block';
        });
      }
    },

  },
  components: {
    ApplyListVue,
    LoginForm
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.li-tab01, .li-tab02 {
  list-style-type: none;
}

.tab01, .tab02 {
  border: 2px solid #eee;
  border-radius: 15px;
  border-bottom-color: #fff;
  border-right-color: #fff;
}

.tab-a {
  color: rgb(156, 156, 39);
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 20px;
}

.tab-b {
  color: rgb(244, 191, 79);
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 20px;
}

.line {
  border: 1px solid #eee;
  transform: translateY(-18px);
}


*{margin:0; padding:0;}
ul{list-style:none;}
a{text-decoration:none; color:#333;}
.wrap{padding:15px; letter-spacing:-0.5px;}
.tab_menu{position:relative;}
.tab_menu .list{overflow:hidden; padding-left: 50px; padding-right: 50px;}
.tab_menu .list li{float:left; margin-right:-5px;}
.tab_menu .list li.is_on .btn{font-weight:bold; color:green;}
.tab_menu .list .btn{font-size:13px;}
.tab_menu .cont_area{margin-top:10px;}
.tab_menu .cont_area .cont2{
  display: none;
}


.content {
  display:block;
  position: relative;
}
</style>