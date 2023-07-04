<template>
  <div>
    <div class="base-info-container">
        <p><label class="lbl-name"><span class="span-name">이름</span></label>님의 마이페이지</p>

        <div class="box-profile">
            <div class="box-border">
                <img class="img-profile" src="../../assets/profile-user.png" />
                <div class="box-txt-profile">
                    <span>기본정보1</span><br>
                    <span>기본정보2</span><br>
                    <span>기본정보3</span><br>
                    <span>기본정보4</span><br>
                    <span>기본정보5</span><br>
                </div>
            </div>
        </div>

    </div>

    <div class="wrap">
      <div class="tab_menu">
        <ul class="list">
          <li @click="initTabs()" class="is_on li-tab01">
            <div class="tab01"><a href="#tab1" class="btn"><span class="tab-a">MY INFO</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab02">
            <div class="tab02"><a href="#tab2" class="btn"><span class="tab-a">VOTE GOOD</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab03">
            <div class="tab03"><a href="#tab3" class="btn"><span class="tab-a">COLLECTED</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab04">
            <div class="tab04"><a href="#tab4" class="btn"><span class="tab-a">APPLY PET</span></a></div>
          </li>
          <li @click="initTabs()" class=" li-tab05">
            <div class="tab05"><a href="#tab5" class="btn"><span class="tab-a">APPROVAL STATE</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab06">
            <div class="tab06"><a href="#tab6" class="btn"><span class="tab-a">MESSAGE BOX</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab07">
            <div class="tab07"><a href="#tab7" class="btn"><span class="tab-a">EXIT</span></a></div>
          </li>
        </ul>
        
        <div class="cont_area">
          <div class="line"></div>
          <div id="tab1" class="cont">
            <div class="tab1-content content"><MyInfo /></div>
          </div>
          <div id="tab2" class="cont cont2">
            <div class="tab2-content content"><GoodList /></div>
          </div>
          <div id="tab3" class="cont cont3">
            <div class="tab2-content content"><CollectedList /></div>
          </div>
          <div id="tab4" class="cont cont4">
            <div class="tab2-content content"><AppliedList /></div>
          </div>
          <div id="tab5" class="cont cont5">
            <div class="tab2-content content"><StateList /></div>
          </div>
          <div id="tab6" class="cont cont6">
            <div class="tab2-content content"><MessageBox /></div>
          </div>
          <div id="tab7" class="cont cont7">
            <div class="tab2-content content"><ExitService /></div>
          </div>
        </div>
      </div>

     
    </div>
  </div>
</template>

<script>
import MyInfo from '../mypage/MyInfo.vue'
import GoodList from '../mypage/GoodList.vue'
import CollectedList from '../mypage/SavedList.vue'
import AppliedList from '../mypage/AppliedList.vue'
import StateList from '../mypage/StateList.vue'
import MessageBox from '../mypage/MessageBox.vue'
import ExitService from '../mypage/ExitService.vue'


export default {
  name: 'MyPageHome',
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
      MyInfo, 
      GoodList,
      CollectedList,
      AppliedList,
      StateList,
      MessageBox,
      ExitService
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

p {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;    
}

.box-profile {
    width: 100%;
    height: 240px;
    display: flex;
    justify-content: center;
    border-radius: 0;
    margin-top: 20px;
}

.box-border {
    height: 240px;
    padding-left: 100px;
    padding-right: 100px;
    padding-top: 20px;
    padding-bottom: 20px;
    border: 1px solid #eee;
    border-radius: 20px;
    display: flex;
    justify-content: center;
}

.box-txt-profile {
    padding-top: 50px;
    margin-left: 50px;
    display: block;
    justify-content: space-evenly;
    width: 400px;
    height: 200px;
    text-align: left;
}

.lbl-name {
    width: 100px;
    color: white;
    height: 30px;
    border: 1px solid rgb(244, 191, 79);
    border-radius: 20px;
    background-color: rgb(244, 191, 79);
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 15px;
    padding-top: 2px;
}
.span-name {
    color: white;
}


.li-tab01, .li-tab02 {
  list-style-type: none;
}

.tab01, .tab02, .tab03, .tab04, .tab05, .tab06, .tab07 {
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
.tab_menu .cont_area .cont3{
  display: none;
}
.tab_menu .cont_area .cont4{
  display: none;
}
.tab_menu .cont_area .cont5{
  display: none;
}
.tab_menu .cont_area .cont6{
  display: none;
}
.tab_menu .cont_area .cont7{
  display: none;
}


.content {
  display:block;
  position: relative;
}
</style>