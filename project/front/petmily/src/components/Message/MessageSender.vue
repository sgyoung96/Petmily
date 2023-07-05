<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

  <div id="messagesender">

    <div class="message-list">
      <h2>{{ loginId }}가 보낸 쪽지 목록</h2>

      <button @click="read">읽은 메일</button>
      <button @click="unread">읽지 않은 메일</button>
      <button @click="all">전체</button>
        <select v-model="select" >
          <option value = "title">제목</option>
          <option value = "reciever">받는이</option>
        </select>

        <input type="text" v-model="find"><button @click="findbtn">검색</button>
    </div> 

    <!-- 쪽지목록 -->
    <div class="message" v-for="message in list" :key="message.num">

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
    };
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
       this.$router.go();
    },

    replaceImg(e) {
      e.target.src = img;
    },
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
  width:30%;
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



</style>


