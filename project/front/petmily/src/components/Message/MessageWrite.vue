<template>
  <div id="messagewrite">
    <h2>쪽지보내기</h2>
    보내는 사람 : <input type="text" v-model="sender" readonly /><br />
    받는 사람 : <input type="text" v-model="reciever" /><br />
    제목 : <input type="text" v-model="title" /><br />
    내용 : <input type="text" v-model="content" /><br />

    <button @click="send">쪽지보내기</button><br />
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
    };
  },

  created: function () {
    const self = this;
    this.loginId = sessionStorage.getItem("loginId");
    self.sender = this.loginId;
  },

  methods: {
    send() {
      //쪽지보내기
      const self = this;
      const form = new FormData();
      form.append("sender", self.sender);
      form.append("reciever", self.reciever);
      form.append("title", self.title);
      form.append("content", self.content);

      self.$axios
        .post("http://localhost:8082/message", form)
        .then(function (res) {
          if (res.status == 200) {
            if (res.data.flag) {
              alert("쪽지 보내기 완료");
              self.title = "";
              self.content = "";
              self.reciever = "";
            } else {
              alert("쪽지 보내기 실패");
            }
          } else {
            alert("에러코드 :" + res.status);
          }
        });
    },
  },
};
</script>
 