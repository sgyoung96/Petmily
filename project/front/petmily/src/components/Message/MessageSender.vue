<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

  <div id="messagesender">
    <h2>{{ loginId }}가 보낸 쪽지 목록</h2>

    <button @click="read">읽은 메일</button>
    <button @click="unread">읽지 않은 메일</button>
    <button @click="all">전체</button>
      <select v-model="select" >
        <option value = "title">제목</option>
        <option value = "reciever">받는이</option>
      </select>

      <input type="text" v-model="find"><button @click="findbtn">검색</button>
       
    <div class="message-bg" v-for="message in list" :key="message.num">
      <span class="box-profile" style="background: #black">
       
        <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + message.reciever.id" />
      </span>
      받는사람 : {{ message.reciever.id }}<br />
      보낸날짜 : {{ message.send_dt }}<br />
      제목 :
      <a v-on:click="($event) => detail(
              message.num,
              message.reciever.id,
              message.send_dt,
              message.title,
              message.content
            )">{{ message.title }}</a><br />
      <div v-if="message.check == 0">
        <span class="material-symbols-outlined">mail</span><br />
      </div>
      <div v-else>
        <span class="material-symbols-outlined">drafts</span>
      </div>
      <span class="material-symbols-outlined" @click="del(message.num)"
        >delete</span
>
    </div>

    <!-- 모달창 -->
    <div class="black-bg" v-if="modalOpen === true">
      <div class="white-bg">
        <h3>{{ num }} title : {{ title }}</h3>
        <span class="box-profile" style="background: #black">
          <img class="profile" @error="replaceImg" :src="'http://localhost:8082/members/imgs/' + reciever"/>
        </span>
        <p>받는사람 : {{ reciever }}</p>
        <p>보낸날짜 : {{ senddt }}</p>
        <p>내용 : {{ content }}</p>
        <button v-on:click="modalOpen = false" class="modal-exit-btn">
          확인
        </button>
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
.message-bg {
  background-color: white;
  align-items: center;
  margin: 20px;
}
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
  width: 50%;
  background-color: white;
  padding: 20px;
  border-radius: 8px;
}

.modal-exit-btn {
  margin-top: 30px;
}

.modal-exit-btn:hover {
  cursor: pointer;
}



</style>
<style>
 .box-profile {
  display: block;
  width: 35px;
  height: 35px; 
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
</style>

