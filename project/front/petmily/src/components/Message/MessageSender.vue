<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

  <div id="messagesender">

    <div class="message-list">
      

      <button @click="read">읽은 메일</button>
      <button @click="unread">읽지 않은 메일</button>
      <button @click="all">전체</button><br/>
      <div class="search">
        <select v-model="select" >
          <option value = "title">제목</option>
          <option value = "reciever">받는이</option>
        </select>

        
        <input type="text" v-model="find"><button @click="findbtn">검색</button>
      </div>
    </div> 

    <!-- 쪽지목록 -->
    <div class="message" v-for="message in paginatedList" :key="message.num">

      <div class="message-header">
        <div class="message-profile">
          <div class="box-profile" style="background: #black">
            <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + message.reciever.id" />
          </div>
          
          <div class="message-id">{{ message.reciever.id }}</div>
        </div>

        <div class="readcheck" v-if="message.check == 0">
          <span class="material-symbols-outlined">mail</span><br />
        </div>
        <div v-else  class="readcheck">
          <span class="material-symbols-outlined">drafts</span>
        </div>
        
        
      </div>

      <div class="message-body">
        <div class="message-body-top">
          <div class="message-title">   
    
            <a v-on:click="($event) => detail(
                    message.num,
                    message.reciever.id,
                    message.send_dt,
                    message.title,
                    message.content
                  )">{{ message.title }}</a><br />
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
        <h3>{{ title }}</h3>
        <div class="modal-box-profile" style="background: #black">
          <img class="modal-profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + reciever"/>
        </div>
        <div class="modal-box-content">
          <div class="modal-box-reciever">받는사람 : {{ reciever }}</div>
          <div class="modal-box-content">내용 : {{ content }}</div>
          <div class="modal-box-date">{{ senddt }}</div>
        </div>
       
        <button v-on:click="modalOpen = false" class="modal-exit-btn">확인</button>
      </div>
    </div>  
  </div>
</template>

<script>
import img from "@/assets/imgs/mypage_sample.jpg";

export default {
  name: "MessageSender",
  data() {
    return {
      loginId: null,
      list: [],
      modalOpen: false,
      num: 0,
      title: "",
      reciever: "",
      senddt: "",
      content: "",
      value:'',
      select:'title',
      find:'',
      pageNum:1,
      pageSize:8,
      block:5,
      blockArray :[],
      blocknum:0,
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
        console.log('this.list : ' + this.list)
        console.log('this.list.length : ' + this.list.length)
        console.log('blockArray.length : ' + blockArray.length)
        console.log('blockArray : ' + blockArray)
        console.log('blockSize : ' + blockSize);
        
        return blockArray;
      }
    },



  created: function () {
    //컴포넌트 실행될 때 한번 실행

    this.loginId = sessionStorage.getItem("loginId");
    const self = this;
    self.$axios
      .get("http://localhost:8082/message/sender/" + self.loginId)
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
      alert("삭제버튼 클릭");
      const self = this;
      alert(num);
      self.$axios
        .delete("http://localhost:8082/message/sender/" + num)
        .then(function (res) {
          if (res.status == 200) {
            alert("삭제완료");
            self.$axios
              .get("http://localhost:8082/message/sender/" + self.loginId)
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
    detail(num, reciever, senddt, title, content) {
      this.num = num;
      this.reciever = reciever;
      this.title = title;
      this.senddt = senddt;
      this.content = content;
      this.modalOpen = true;
    },
    
    read(){
      const self = this;
      self.$axios
      .get("http://localhost:8082/message/recievecheck/" + self.loginId + "/" + 1)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
      });
    },

    unread(){
       const self = this;
      self.$axios
      .get("http://localhost:8082/message/recievecheck/" + self.loginId + "/" + 0)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
      });

    },

    findbtn(){
      const self = this;
      alert(self.loginId)
      if(self.find==''){
        alert('검색어를 입력하세요')
      }else{
       if(self.select=="title"){
        self.$axios
      .get("http://localhost:8082/message/s_title/" + self.find + "/" + self.loginId)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
      });

      }else{
        self.$axios
      .get("http://localhost:8082/message/reciever/" + self.find)
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
      // this.$router.go();
      this.loginId = sessionStorage.getItem("loginId");
      const self = this;
      self.$axios
      .get("http://localhost:8082/message/sender/" + self.loginId)
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
        } else {
          alert("에러코드 :" + res.status);
        }
      });
    },

    replaceImg(e) {
      e.target.src = img;
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
      console.log(blocks)
      this.pageNum = blocks;
    }
  
  },
};
</script>


<style scoped>
/* .message-bg {
  background-color: white;
  align-items: center;
  margin: 20px;
} */
.black-bg {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.432);
  position: fixed;
  top: 0;
  left: 0;
  padding: 20px;
}

.white-bg {
  width: 40%;
  background-color: #fbf8f4;
  padding: 20px;
  border-radius: 40px;
}

.modal-exit-btn {
  margin-top: 30px;
}

.modal-exit-btn:hover {
  cursor: pointer;
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

.modal-box-profile {
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

.modal-box-content{
  margin-left:160px;
}
.modal-box-date{
  display:flex;
  float: right;

}

.message-list{
  margin-bottom: 40px;
}
.message{
  width:40%;
  display: block;
  margin:auto;
  border:2px solid #F0F0F0;
  margin-bottom: 30px;
  border-radius: 20px;

}
.message:hover {
  transform: scale( 1.01 )
}
.message-header{
  /* position:relative; */
  display: flex;
  width: 100%;
  background-color: #F0F0F0;
  justify-content: space-between;
  padding:5px;
   border-radius: 20px 20px 0px 0px;

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


}

.readcheck{
  display:flex;
  text-align:right;
  float:right;
  justify-content: right;
  margin-right:10px;
  align-items : center;
  color:rgb(244, 191, 79)
  

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
}
.message-date{
 
  float:right;
  display:flex;
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
  background-color:rgb(244, 191, 79)
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

.search{
  margin-top:30px;
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


</style>


