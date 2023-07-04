<template>
  <div id="messagewrite">
    <h2>쪽지보내기</h2>
    보내는 사람 : <input type="text" v-model="sender" readonly /><br />
    받는 사람 : <input type="text" v-model="reciever" @input="submitAutoComplete" /><br />
    <ul class="autocomplete disabled" :class="{'disable':tf}">
      <li @mousedown="searchSkillAdd(res)" style="cursor:pointer" :id="i" v-for="(res,i) in result" :key="i">{{res}}</li>
    </ul>
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
      tf:true,
      skills: [],
      result: [],
      
    };
  },

  watch:{
   
  },

  created: function () {
    const self = this;
    this.loginId = sessionStorage.getItem("loginId");
    self.sender = this.loginId;
    this.userlist()
  },

  methods: {

      userlist(){
      
      this.skills=[];

      this.$axios.get("http://localhost:8082/members/all")
      .then(response =>{
        let registeredUsers = response.data.dto;
        this.skills = registeredUsers.map(user => user.id);
        console.log(this.skills)
      })
      },

     submitAutoComplete() {

     //const autocomplete = document.querySelector(".autocomplete");
      if (this.reciever) {
        self.tf=false;
       // autocomplete.classList.remove("disabled");
        this.result = this.skills.filter((skill) => {
          return skill.match(new RegExp("^" + this.reciever, "i"));
        });
      } else {
        self.tf=true;
        //autocomplete.classList.add("disabled");
      }
    },
    searchSkillAdd(res){ // result[i] = i 번째 값 (res)
      console.log('res :' + res)
      const self = this;
      for (let j = 0; j < self.result.length; j++) {
        let obj = document.getElementById(j);
        console.log('obj: ' +obj);
        console.log('obj value : ' + obj.innerText)

        for (let selectedIndex = 0; selectedIndex < self.result.length; selectedIndex++ ) {
          if (obj.innerText == self.result[selectedIndex]) {
            console.log('same');
            //self.result.pop(res);
            self.result = [];
            console.log(self.result);
            self.reciever=res
          }
        }
      }


      //const autocomplete = document.querySelector(".autocomplete");
        // self.reciever = res
        //    self.tf=true;
          //autocomplete.classList.add("disabled");
          
        
    },


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
 