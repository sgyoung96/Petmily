<template>
  <div>
    <div class="base-info-container">
        <p><label class="lbl-name"><span id="login_name" class="span-name">이름</span></label>님의 마이페이지</p>

        <div class="box-profile">
            <div class="box-border">
                <img class="img-profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/'+ id">
                <div class="box-txt-profile">
                    <p><span id="name">이름</span>님이 그동안 활동한 내역이에요.</p>
                    <br>
                    <p class="history"><span class="history-header">관심 목록 : </span><span class="history-num"> {{ watch }} 개</span></p>
                    <p class="history"><span class="history-header">좋아요 갯수 : </span><span class="history-num"> {{ likecnt2 }} 개</span> </p>
                    <p class="history"><span class="history-header">글 작성 수 : </span><span class="history-num"> {{ writecnt3 }} 개</span></p>
                    <p class="history"><span class="history-header">댓글 작성 수 : </span><span class="history-num"> {{ cocnt2 }} 개</span></p>
                    <p class="history"><span class="history-header">입양 신청 횟수 : </span><span class="history-num"> {{ appcnt }} 개</span></p>
                    <p class="history"><span class="history-header">받은 쪽지 : </span><span class="history-num"> {{ msgcnt }} 개</span></p>
                </div>
                <img class="deco" src="../../assets/images/bg_dog_mypage.png" />
            </div>
        </div>

    </div>

    <div class="wrap">
      <div class="tab_menu">
        <ul class="list">
          <li @click="initTabs()" class="is_on li-tab01 tab-li">
            <div class="tab01"><a href="#tab1" class="btn"><span class="tab-a">MY INFO</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab02 tab-li">
            <div class="tab02"><a href="#tab2" class="btn"><span class="tab-a">COLLECTED</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab03 tab-li">
            <div class="tab03"><a href="#tab3" class="btn"><span class="tab-a">APPLY PET</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab04 tab-li">
            <div class="tab04"><a href="#tab4" class="btn"><span class="tab-a">MESSAGE BOX</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab05 tab-li">
            <div class="tab05"><a href="#tab5" class="btn"><span class="tab-a">ALERT BOX</span></a></div>
          </li>
          <li @click="initTabs()" class="li-tab06 tab-li">
            <div class="tab06"><a href="#tab6" class="btn"><span class="tab-a">EXIT</span></a></div>
          </li>
        </ul>
        
        <div class="cont_area">
          <div class="line"></div>
          <div id="tab1" class="cont">
            <div class="tab1-content content"><MyInfo /></div>
          </div>
          <div id="tab2" class="cont cont2">
            <div class="tab2-content content"><CollectedList /></div>
          </div>
          <div id="tab3" class="cont cont3">
            <div class="tab2-content content"><AppliedList /></div>
          </div>
          <div id="tab4" class="cont cont4">
            <div class="tab2-content content"><MessageBox /></div>
          </div>
          <div id="tab5" class="cont cont5">
            <div class="tab2-content content"><AlertBox /></div>
          </div>
          <div id="tab6" class="cont cont6">
            <div class="tab2-content content"><ExitService /></div>
          </div>
        </div>
      </div>

     
    </div>
  </div>
</template>

<script>
import MyInfo from '../mypage/MyInfo.vue'
import CollectedList from '../mypage/SavedList.vue'
import AppliedList from '../mypage/AppliedList.vue'
import MessageBox from '../mypage/MessageBox.vue'
import AlertBox from '../mypage/AlertBox.vue'
import ExitService from '../mypage/ExitService.vue'


export default {
  name: 'MyPageHome',
  data () {
    return {
        id: sessionStorage.getItem('loginId'),
        name: '',
        email: '',
        birth: '',
        gender: '',
        phone: '',
        address: '',
        token: '',
        watch:0,
        likecnt2:0,
        writecnt3:0,
        cocnt2:0,
        appcnt:0,
        msgcnt:0
    }
  },
  created: function () {
    this.initTabs();
    this.getUserInfo();
    
  },
  mounted: function() {
    this.getWatch();
    this.getLike();
    this.getBoards();
    this.getComment();
    this.getApply();
    this.getMessage();
  },
  methods: {
    initTabs() {
      const tabList = document.querySelectorAll('.tab_menu .list .tab-li');
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
    getUserInfo() {
        const moment = require('moment');
        //let token = sessionStorage.getItem('token');
        const self = this;
        this.$axios.get('http://localhost:8082/members/' + this.id)
        .then(function(res){
            if (res.status == 200) {
                let dto = res.data.dto
                if (dto != null) { 
                    console.log(dto);
                    //this.id = dto.id
                    self.name = dto.name
                    self.email = dto.email
                    self.birth = moment(dto.birth).format('L');
                    self.gender = dto.gender
                    self.phone = dto.phone
                    self.address = dto.address

                    self.setBaseInfo();
                } else {
                    alert('없는 아이디 이거나 만료된 토큰')
                }
            } else {
                alert('에러코드 :' + res.status)
            }
        });
    },
    setBaseInfo() {
        document.getElementById('login_name').innerText = this.name;
        document.getElementById('name').innerText = this.name;
    },
    getWatch(){
      const self = this
      self.$axios.get('http://localhost:8082/watchlist/id/' + this.id)
      .then(function(res){
        if(res.status == 200){
          self.watch = res.data.dto
        }
      })
    },
    getLike(){
      const self = this
      let likecnt = 0;
      
      self.$axios.get('http://localhost:8082/liketable/id/' + this.id)
      .then(function(res){
        if(res.status == 200){
          likecnt = res.data.dto
          self.$axios.get('http://localhost:8082/adoptliketable/id/' + self.id)
          .then(function(res){
            if(res.status == 200){
              self.likecnt2 = likecnt + res.data.dto
            }
          })
        }
      })
    },
    getBoards(){
      const self = this
      let writecnt = 0;
      let writecnt2 = 0;
      self.$axios.get('http://localhost:8082/adopt/id/' + this.id)
      .then(function(res){
        if(res.status == 200){
          writecnt = res.data.dto
          self.$axios.get('http://localhost:8082/dboard/id2/' + self.id)
          .then(function(res){
            if(res.status == 200){
              writecnt2 = writecnt + res.data.dto
              self.$axios.get('http://localhost:8082/volboard/id/' + self.id)
              .then(function(res){
                if(res.status == 200){
                  self.writecnt3 = writecnt2 + res.data.dto
                }
              })
            }
          })
        }
      })
    },
    getComment(){
      const self = this
      let cocnt = 0;
      
      self.$axios.get('http://localhost:8082/adoptcomment/id/' + this.id)
      .then(function(res){
        if(res.status == 200){
          cocnt = res.data.dto
          self.$axios.get('http://localhost:8082/dcomment/id/' + self.id)
          .then(function(res){
            if(res.status == 200){
              self.cocnt2 = cocnt + res.data.dto
            }
          })
        }
      })
    },
    getApply(){
      const self = this
      self.$axios.get('http://localhost:8082/Applyform/getbyid/' + this.id)
      .then(function(res){
        if(res.status == 200){
          self.appcnt = res.data.dto
        }
      })
    },
    getMessage(){
      const self = this
      self.$axios.get('http://localhost:8082/message/id/' + this.id)
      .then(function(res){
        if(res.status == 200){
          self.msgcnt = res.data.dto
        }
      })
    },

  },
  components: {
      MyInfo, 
      CollectedList,
      AppliedList,
      MessageBox,
      AlertBox,
      ExitService
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

p {
    font-family: 'IBMPlexSansKR-Medium';
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


.lbl-name {
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
.span-name {
    color: white;
}

.img-profile {
  display: block;
  width: 50%;
  height: auto;
  border-radius: 70%;
  overflow: hidden;
}

.box-border {
    height: 240px;
    width: 800px;
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
    padding-top: 20px;
    margin-left: 70px;
    display: block;
    justify-content: space-evenly;
    width: 400px;
    height: 200px;
    text-align: left;
    padding-bottom: 20px;
}

.box-txt-profile p {
    font-family: 'IBMPlexSansKR-Regular';
    font-size: 15px;    
}

#name {
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;   
}

.history > .history-num {
    font-family: 'IBMPlexSansKR-Medium';
    font-size: 11px;
}

.history-header {
    font-family: 'IBMPlexSansKR-SemiBold';
    font-size: 13px;
}

.deco {
    width: 90px;
    height: 50px;
    transform: translateX(50px) translateY(150px);
}

.li-tab01, .li-tab02 {
  list-style-type: none;
}

.tab01, .tab02, .tab03, .tab04, .tab05, .tab06 {
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



.content {
  display:block;
  position: relative;
}
</style>