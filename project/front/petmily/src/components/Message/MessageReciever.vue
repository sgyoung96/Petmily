
<template>

  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

  <!-- 쪽지 보내기 모달창(보낸사람 프로필 클릭하면) -->
  

    <div id="messagereciever">

      <div class="message-list">
        <div class="menu">
          
          <span class="material-symbols-outlined" @click="read" :class="{ active: activeMenu ==='read' }"  >drafts<span class="messagemenu">읽은쪽지</span></span>
          <span class="material-symbols-outlined" @click="unread" :class="{ active: activeMenu ==='unread' }">mail<span class="messagemenu">안읽은쪽지</span></span>
          <span class="material-symbols-outlined" @click="all" :class="{ active: activeMenu ==='all' }"><span class="messagemenu">전체</span></span>
      
        </div>

        <div class="search">
          <select v-model="select" >
            <option value = "title">제목</option>
            <option value = "sender">보낸이</option>
          </select>


        
          <input type="text" v-model="find"  @keyup.enter="findbtn"><button @click="findbtn">검색</button>
        </div>
      </div>
       
        
          

          <!-- 쪽지목록 -->
        
            
           <div class="message" v-for="message in paginatedList " :key="message.num">

            <div class="message-header">
              <div class="message-profile">
                <div class="box-profile" style="background: #black">
                  <img class="profile" 
                  @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + message.sender.id"
                  v-on:click="modal(message.sender.id)">
                </div>
               
                <div class="message-id">{{ message.sender.id }}</div>
              </div>  

                <div class="readcheck" v-if="message.check == 0">
                  <span class="material-symbols-outlined">mail</span>
                </div>
                <div v-else class="readcheck">
                  <span class="material-symbols-outlined">drafts</span>
                </div>
            </div>

            <div class="message-body">  
              <div class="message-body-top">  
                <div class="message-title">   
                    
                  <a v-on:click="($event) =>detail(
                              message.num,
                              message.sender.id,
                              message.send_dt,
                              message.title,
                              message.content
                      )">{{ message.title }}</a>
                </div> 
                <div class="message-date">         
                      {{ message.send_dt }}<br />      
                </div> 
              </div>
              <div class="message-body-bottom">
                  <button class="message-delete" @click="del(message.num)">delete</button>
              </div>    
            </div>
           </div>

        <div class="nomessage" v-if="nomessage !==''">{{nomessage}}</div>

        <!-- 페이징 -->
        <div>
          <ul class="pagination" style="display: inline-block">
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Previous" @click="previousPage">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
         

            <li class="page-item" v-for="blocks in blockList[blocknum]" :key="blocks.num"
              :class="{ active: blocks === pageNum }">
              <a class="page-link" href="#" @click="goToPage(blocks)">{{ blocks }}</a>
            </li>

            

            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next" @click="nextPage">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        
      </div>


      <!-- 쪽지 내용 읽기 모달창 -->
      <div class="black-bg" v-if="modalOpen === true">
        <div class="white-bg">
          
            <div class="modal-box-profile" style="background: #black">
              <img
                class="modal-profile" @error="replaceImg"
                :src="'http://localhost:8082/members/imgs/' + sender"/>
            </div>
            
            <div class="modal-box">
              <div class="modal-box-title">{{ title }}</div>
              <div class="modal-box-content"> {{ content }}</div>
              <div class="modal-box-sender">From. {{ sender }}</div>
             
            </div>
            <div class="modal-box-bottom">
              <button v-on:click="readcheck(num)" class="modal-exit-btn">확인</button>
              <div class="modal-box-date">{{ senddt }}</div>
            </div>
        </div>
      </div>
    </div>
    <MessageModal :resender=resender v-if="displayDetail" @close="displayDetail=false"/>
</template>


<script>
import img from "@/assets/imgs/mypage_sample.jpg";

import MessageModal from "@/components/Message/MessageModal";

export default {
  name: "MessageReciever",

  emits:['new-cnt'],
  props:{
    message:Array
   
  },
  components:{
  
    MessageModal
  },
  data() {
    return {
      loginId: null,
      list: [],
      modalOpen: false,
      num: 0,
      title: "",
      sender: "",
      senddt: "",
      content: "",
      isModalViewed:false,
      MessageModal:false,
      displayDetail:false,
      resender:'',
      value:'',
     select:'title',
      find:'',
      pageNum:1,
      pageSize:8,
      block:5,
      blockArray :[],
      blocknum:0,
      cnt:0,
      activeMenu: "",
      nomessage:'',
      checknum:0
      
      
    };
  },




  computed: {
      totalPages() {
        return Math.ceil(this.list.length / this.pageSize);
      },
      paginatedList() {
        const start= (this.pageNum-1) * this.pageSize;
        const end = start + this.pageSize;
        console.log('end : ' + end)
        return this.list.slice(start, end);
      },
      blockList(){
      
        const blockArray = [];
      
        for(let i=0; i<=this.totalPages; i += this.block){
          const temp = [];
          for(let j=i+1; j<=i + this.block && j<= this.totalPages; j++){
           
            temp.push(j);
            
          }blockArray.push(temp);
        }

        if(this.totalPages % this.block === 0 && blockArray.length > 0){
          const lastTemp = blockArray[blockArray.length-1];
          lastTemp.pop();
          if(lastTemp.length ===0){
            blockArray.pop();
          }
        }
        const blockSize = blockArray.length;
        console.log('this.list.length : ' + this.list.length)
        console.log('blockArray.length : ' + blockArray.length)
        console.log('blockArray : ' + blockArray)
        console.log('blockSize : ' + blockSize);
        
        return blockArray;
      }
    },

  created: function () {
    this.loginId = sessionStorage.getItem("loginId");
    
    const self = this;
    self.$axios
      .get("http://localhost:8082/message/reciever/" + self.loginId)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
          
        } else {
          alert("에러코드 :" + res.status);
        }
      });
  },
  methods: {
    del(num) {
      
      this.loginId = sessionStorage.getItem("loginId");
      const self = this;
      
      self.$axios
        .delete("http://localhost:8082/message/reciever/" + num)
        .then(function (res) {
          if (res.status == 200) {
            
            self.$axios
              .get("http://localhost:8082/message/reciever/" + self.loginId)
              .then(function (res) {
                if (res.status == 200) {
                  self.list = res.data.list;
                } else {
                  alert("에러코드 :" + res.status);
                }
              });
          } else {
            alert("에러코드 :" + res.status);
          }
        });
    },
    detail(num, sender, senddt, title, content) {
      this.num = num;
      this.sender = sender;
      this.title = title;
      this.senddt = senddt;
      this.content = content;
      this.modalOpen = true;
    },
    readcheck(num) {
      const self = this;
      
      self.$axios
        .patch("http://localhost:8082/message/" + num)
        .then(function (res) {
          if (res.status == 200) {
            
            self.cntcheck()
            if(self.checknum === 0){
            self.$axios
              .get("http://localhost:8082/message/reciever/" + self.loginId)
              .then(function (res) {
                if (res.status == 200) {
                  self.list = res.data.list;
                  
                } else {
                  alert("에러코드 :" + res.status);
                }
              });
            }else if(self.checknum === 1){
              self.read()
            }else{
              self.unread()
            }
          } else {
            alert("에러코드 :" + res.status);
          }
        });
      this.modalOpen = false;
    },

    cntcheck(){
       const self = this;
       self.$axios.get('http://localhost:8082/message/cnt/' + self.loginId)
      .then(function(res){
        if(res.status == 200){
         self.cnt = res.data.cnt
          self.$emit('new-cnt',self.cnt)
        }else{
          alert('에러코드 :' + res.status)
        }
      });  
    },
     read(){
      const self = this;
      self.$axios
      .get("http://localhost:8082/message/sendcheck/" + self.loginId + "/" + 1)
      .then(function (res) {
        if (res.status == 200) {
          if(res.data.list.length === 0){
            self.nomessage= '메세지가 없습니다'
            
          }else{
            self.nomessage= ''
          }
          self.list = res.data.list;
          self.activeMenu = "read";
          self.checknum=1;
        } else {
          alert("에러코드 :" + res.status);
        }
      });
    },

    unread(){
       const self = this;
      self.$axios
      .get("http://localhost:8082/message/sendcheck/" + self.loginId + "/" + 0)
      .then(function (res) {
        if (res.status == 200) {
          if(res.data.list.length === 0){
            self.nomessage= '메세지가 없습니다'
            
          }else{
            self.nomessage= ''
          }
          self.list = res.data.list;
          self.activeMenu = "unread";
          self.checknum=2;
        } else {
          alert("에러코드 :" + res.status);
        }
      });

    },


    findbtn(){
      const self = this;
    
      if(self.find==''){
        alert('검색어를 입력하세요')
      }else{
       if(self.select=="title"){
        self.$axios
      .get("http://localhost:8082/message/r_title/" + self.find + "/" + self.loginId)
      .then(function (res) {
        if (res.status == 200) {
        
          if(res.data.list.length === 0){
            self.nomessage= '메세지가 없습니다'
           
          }else{
            self.nomessage= ''
          }
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
        self.find='';
      });

      }else{
        self.$axios
      .get("http://localhost:8082/message/sender/" + self.find)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
      });
        

      }
      }

    },

    all(){
       //this.$router.go();
       this.loginId = sessionStorage.getItem("loginId");
      const self = this;
      self.$axios
      .get("http://localhost:8082/message/reciever/" + self.loginId)
      .then(function (res) {
        if (res.status == 200) {
          if(res.data.list.length === 0){
            self.nomessage= '메세지가 없습니다'
           
          }else{
            self.nomessage= ''
          }
          self.list = res.data.list;
          self.activeMenu = "all";
           self.checknum=0;
          
        } else {
          alert("에러코드 :" + res.status);
        }
      });
    },
    replaceImg(e) {
            e.target.src = img;
        },

    modal(sender){
      const self = this;
      this.resender = sender
      
      self.displayDetail=true

    },

     previousPage() {
      if (this.blocknum > 0) {
        this.blocknum--;
        this.pageNum -= this.block
        console.log(this.pageNum)
      }
    },
    nextPage() {
      if (this.blocknum < this.blockList.length-1) {
        this.blocknum++;
        console.log(this.blockArray)
        this.pageNum += this.block
        console.log(this.pageNum)
      }
    },
    goToPage(blocks) {
      this.pageNum = blocks;
    }
  
   
  },
};
</script>

<style scoped>

.material-symbols-outlined{
  cursor: pointer;
  display:flex;
  align-items: center;
  margin-left:15px;

}

.active {
  color: rgb(244, 191, 79);
  font-weight: bold;
}

.menu{
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top:30px;
  margin-left:220px;
  color:#cfcfcf;
}
.messagemenu{
  
  font-size:15px;
  align-items: center;
  justify-content: center;

}
.black-bg {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.668);
  position: fixed;
  top: 0;
  left: 0;
  padding: 20px;
  z-index: 9999;
 
 
}

.white-bg {
  position:relative;
  width: 30%;
  background-color: #fbf8f4;
  padding: 20px;
  border-radius: 40px;

}



.box-profile {
  display: block;
  width: 35px;
  height: 35px; 
  border-radius: 70%;
  overflow: hidden;
  float: left
}
.profile {
  width: 100%;
  height: 100%;
  background-color: white;
  object-fit: cover;
  cursor: pointer;
}
.modal-box{
  display:flex;
  margin-left:90px;
   flex-direction: column;
}
.modal-box-title{
  display:block;
  width:100%;
  text-align: left;
  font-weight: bold;
  font-size:22px;

}
.modal-box-content{

 display:block;
 margin-top: 10px;
 width:100%;
 text-align: left;  
 font-family: "IBMPlexSansKR-Medium";
}
.modal-box-sender{
 display:block;
 margin-top: 10px;
 width:100%;
 text-align: right; 
 font-family: "IBMPlexSansKR-Medium";
}

.modal-box-bottom{
  display:flex;
  align-items: center;
  justify-content: space-between;
}

.modal-exit-btn {
margin-left: 200px;
border:0px;
padding:5px 10px 5px 10px;
border-radius: 10px;
 
}

.modal-exit-btn:hover {
  cursor: pointer;
}
.modal-box-date{
  margin-left: auto;
   font-family: "IBMPlexSansKR-Medium";
 
}
.modal-box-profile {
  position: absolute;
  top:-60px;
  left:-60px;
  display: block;
  width: 150px;
  height: 150px; 
  border-radius: 70%;
  overflow: hidden;
  float: left;
  margin-left:10px ;

}
.modal-profile {
  width: 100%;
  height: 100%;
  background-color: white;
  object-fit: cover;
  cursor: pointer;
}

.nomessage{
  font-weight: bold;
  color: #a3a3a3;
  font-size:large;
  margin-bottom: 20px;
}
.message-list{
  margin-bottom: 40px;
}
.message{
  width:40%;
  display: block;
  margin:auto;
  border:2px solid  rgb(244, 191, 79);
  margin-bottom: 30px;
  border-radius: 20px;
  overflow: hidden;

}
.message:hover {
  transform: scale( 1.01 )
}
.message-header{
  /* position:relative; */
  display: flex;
  width: 100%;
  background-color: rgb(244, 191, 79);
  justify-content: space-between;
  padding:5px;
 

}
.message-profile{
  display:flex;
  justify-content: left;
  margin-left:10px;

}
.message-id{
  display:flex;
  justify-content: left;
  float:left; 
  margin-left:10px;
  align-items : center;
  font-size: 1.3em;
  font-family: "IBMPlexSansKR-Medium";
  

}

.readcheck{
  display:flex;
  text-align:right;
  float:right;
  justify-content: right;
  margin-right:10px;
  align-items : center;
  color:white

}

.message-body-top{
  display:flex;
  padding:15px 15px 5px 15px;
  
  justify-content: space-between;
}
.message-title{
  font-size: 1.2em;
  cursor: pointer;
  margin-left:10px;
  font-family: "IBMPlexSansKR-Medium";
}
.message-date{
 
  float:right;
  display:flex;
  font-family: "IBMPlexSansKR-Medium";
  /* text-align:right; */
  /* justify-content: right; */
}
.message-body-bottom{
  display:flex;

  justify-content: right;
}

.message-delete{
  display:flex;
  float:right;
  justify-content: right;
  margin-right:10px;
  margin-bottom:10px;
  border:0px;
  border-radius: 10px;
}

.page-item {
  position:relative;
  display: inline-block;
  margin-right: 5px;
  display: flex
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
  position:relative;
  background-color: rgb(244, 191, 79);
  color: white;
  z-index: 0;
}
.search{
  margin-top:10px;
}
input{
   width:250px;
   height: 35px;
   margin:20px;
   border:1px solid rgb(244, 191, 79);
   border-radius: 10px;
}

.search button{
  background-color: rgb(244, 191, 79);
  border-radius: 10px;
  color:white;
  border:0px;
  height:35px;
  width: 100px;
  padding: 5px;
  font-size: 18px;
  font-weight: bold;
}

select{
  width:120px;
  height: 35px;
  border-radius: 10px;

  border:0px;
 
  font-size: 18px;
  font-weight: bold;
}
MessageModal{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 99999;
}
</style>
