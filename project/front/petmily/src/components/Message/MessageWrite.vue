<template>
  <div id="messagewrite">
    <div class="wrapper2">
      <div class="message_area">
        <div class="input_box">
          <p style="font-weight: bold">Send Message</p>
          <hr style="display: flex" />
        </div>

        <!-- 보내는 사람 -->

        <div class="input_box">
          <input type="hidden" v-model="sender" readonly /><br />
        </div>

        <div class="input_box" v-show="input_reciever">
          <input
            class="input_txt"
            type="text"
            v-model="reciever"
            @input="submitAutoComplete"
            placeholder="받는사람"
          /><br />
        </div>
        <div class="input_checkbox">
          <input type="checkbox" v-model="isAdmin" />관리자에게 쪽지 보내기
        </div>

        <div class="select" v-show="input_reciever">
          <ul class="autocomplete" :class="{ disabled: tf }">
            <li>
              <button
                class="autocompletebtn"
                @mousedown="searchSkillAdd(res)"
                style="cursor: pointer"
                :id="i"
                v-for="(res, i) in result"
                :key="i"
              >
                {{ res }}
              </button>
            </li>
          </ul>
        </div>

        <div class="input_box">
          <input
            class="input_txt"
            type="text"
            v-model="title"
            placeholder="TITLE"
          />
        </div>

        <div class="input_box">
          <textarea
            class="input_txtarea"
            v-model="content"
            cols="50"
            rows="5"
            @keyup.enter="send"
            placeholder="MESSAGE"
          ></textarea>
        </div>

        <button
          class="message_botton"
          style="background-color: rgb(255, 214, 91)"
          @click="send"
        >
          쪽지보내기</button
        ><br />
        <button class="message_botton" @click="$emit('close')">취소</button>
      </div>
    </div>
  </div>
</template>

 <script>
export default {
  name: "MessageWrite",
  data() {
    return {
      sender: "",
      reciever: "",
      title: "",
      content: "",
      loginId: null,
      tf: true,
      skills: [],
      result: [],
      isAdmin: false,
      input_reciever: true,
    };
  },

  watch: {
    isAdmin(value) {
      if (value) {
        this.reciever = "admin";
        this.input_reciever = false;
      } else {
        this.reciever = "";
        this.input_reciever = true;
      }
    },
  },

  created: function () {
    const self = this;
    this.loginId = sessionStorage.getItem("loginId");
    self.sender = this.loginId;
    this.userlist();
    document.addEventListener("click", this.handleClickOutside);
  },

  beforeUnmount() {
    document.removeEventListener("click", this.handleClickOutside);
  },

  methods: {
    userlist() {
      this.skills = [];

      this.$axios.get("http://localhost:8082/members/all").then((response) => {
        let registeredUsers = response.data.dto;
        this.skills = registeredUsers.map(
          (user) => user.name + "(" + user.id + ")"
        );
        console.log(this.skills);
      });
    },

    submitAutoComplete() {
      if (this.reciever) {
        self.tf = false;

        this.result = this.skills.filter((skill) => {
          return skill.match(new RegExp("^" + this.reciever, "i"));
        });
      } else {
        self.tf = true;
      }
    },

    handleClickOutside(event) {
      const self = this;
      const select = document.querySelector(".select");
      if (select && !select.contains(event.target)) {
        console.log("외부클릭");
        self.result = [];
      }
    },

    searchSkillAdd(res) {
      // result[i] = i 번째 값 (res)
      console.log("res :" + res);
      const self = this;
      for (let j = 0; j < self.result.length; j++) {
        let obj = document.getElementById(j);
        console.log("obj: " + obj);
        console.log("obj value : " + obj.innerText);

        for (
          let selectedIndex = 0;
          selectedIndex < self.result.length;
          selectedIndex++
        ) {
          if (obj.innerText == self.result[selectedIndex]) {
            console.log("same");
            self.result = [];
            console.log(self.result);
            self.reciever = res;
          }
        }
      }
    },

    send() {
      //쪽지보내기
      const self = this;
      const form = new FormData();
      this.loginId = sessionStorage.getItem("loginId");
      
      if(this.reciever !='admin'){
        const reciever = self.reciever.match(/\((.*?)\)/)[1];
        form.append("reciever", reciever);
      }else{
        form.append("reciever",'admin')
      }
      
      form.append("sender", self.sender);
      
      form.append("title", self.title);
      form.append("content", self.content);
      if (this.loginId === self.reciever) {
        alert("수신자를 확인해 주세요");
        self.reciever = "";
        return;
      }
      if (self.reciever === "" || self.title === "" || self.content === "") {
        alert("메세지를 작성해주세요");
        return;
      }
      self.$axios
        .post("http://localhost:8082/message", form)
        .then(function (res) {
          if (res.status == 200) {
            if (res.data.flag) {
              alert("쪽지 보내기 완료");
              self.title = "";
              self.content = "";
              self.reciever = "";
              self.isAdmin = false;
              self.$emit("close");
            } else {
              alert("쪽지 보내기 실패");
            }
          } else {
            alert("에러코드 :" + res.status);
          }
        });
    },

    adminmessage() {
      self.reciever = "admin";
    },
  },
};
</script>

<style scoped>
.wrapper2 {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99999;
}

.message_area {
  margin: 0 auto;
  padding: 20px 20px 60px 20px;
  width: 420px;
  background-color: white;
  border-radius: 20px;
}

.input_txt {
  width: 100%;
  height: 40px;
  border: 1px solid black;
  margin-top: 20px;
  outline-offset: 0;
  outline: none;
  color: black;
  border: none;
  border-right: 0px;
  border-top: 0px;
  border-left: 0px;
  border-bottom: 1px solid;
}

.input_txtarea {
  margin-top: 20px;
  width: 100%;
  border: 1px solid black;
  outline-offset: 0;
  outline: none;
  border: none;
  border-right: 0px;
  border-top: 0px;
  border-left: 0px;
  border-bottom: 1px solid;
}

.input_checkbox {
  float: right;
}
input::placeholder {
  color: rgb(209, 209, 209);
}

.input_txtarea::placeholder {
  color: rgb(209, 209, 209);
}

ul,
li {
  list-style: none;
  margin: 0;
  padding: 0;
  text-align: initial;
}
.autocomplete {
  position: absolute;
  overflow: hidden;
  outline: 1px solid #c4c4c4;
  border-radius: 10px;
  overflow: hidden;
  background-color: white;
  width: 380px;
}
.autocomplete.on {
  display: block;
}

.autocomplete li {
  width: 200px;
}

.autocomplete li button {
  display: block;
  width: 360px;
  background-color: #fff;
  border-top: none;
  margin: 8px;
  padding: 7px 10px 7px;
  border-radius: 10px;
  border: 0px;
}

.autocomplete li button:hover {
  background-color: rgb(244, 191, 79);
}

.message_botton {
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: 0px;
  background-color: rgb(222, 222, 222);
  color: white;
  margin-top: 20px;
  font-weight: bold;
}
</style>
 