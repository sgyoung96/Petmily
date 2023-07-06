<template>
  <div id="messagewrite">
    <div class="message_area">
      <h2>쪽지보내기</h2>

      <!-- 보내는 사람 -->
      <div class="input_box">
       <input type="hidden" v-model="sender" readonly /><br />
      </div>

      <div class="input_box">
        받는이 : <input class="input_txt" type="text" v-model="reciever" @input="submitAutoComplete" /><br />
      </div>

      <div class="select">
        <ul class="autocomplete " :class="{'disabled':tf}">
          <li><button class="autocompletebtn" @mousedown="searchSkillAdd(res)" style="cursor:pointer" :id="i" v-for="(res,i) in result" :key="i">{{res}}</button></li>
        </ul>
      </div>

      <div class="input_box">
      제목 : <input class="input_txt" type="text" v-model="title" />
      </div>

      <div class="input_box">
      내용 : <textarea class="input_txtarea" v-model="content" cols="50" rows="10"></textarea>
      </div>

      <button @click="send">쪽지보내기</button><br />
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
    document.addEventListener("click", this.handleClickOutside);
  },

  beforeUnmount() {
  document.removeEventListener("click", this.handleClickOutside);
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

    handleClickOutside(event) {
      const self = this;
       const select = document.querySelector(".select");
       if (select && !select.contains(event.target)) {
        console.log('외부클릭')
       self.result = [];
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

<style scoped>

/* .input_box {
  
    padding: 0 0 40px;
    position: relative;
} */
.message_area {
    margin: 0 auto;
    padding: 58px 0 160px;
    width: 400px;
   
}

.input_txt{
  width: 100%;
  height: 40px;
  border:1px solid black;
  outline-offset: 0;
  outline: none;
  color:black;
}

.input_txtarea{
  width: 100%;
  border:1px solid black;
  outline-offset: 0;
  outline: none;
  color:black;
}

ul, li{
    list-style: none;
    margin: 0;
    padding: 0;
    text-align: initial;
}
.autocomplete {
    position: absolute;
    overflow: hidden;
    outline: 1px solid #C4C4C4;
    border-radius: 10px;
    overflow: hidden;
    background-color: white;
    width: 400px;
    /* display: none; */

    
}
.autocomplete.on {
    display: block;
}

.autocomplete li {
    width: 200px;
}

.autocomplete li button {
    display: block;
    width: 380px;
    background-color: #fff;
    border-top: none;
    margin: 8px;
    padding: 7px 10px 7px;
    border-radius: 10px;
    border:0px
}

.autocomplete li button:hover {
    background-color: rgb(244, 191, 79);
}
/* .butautocompletebtnton{
    border: initial;
    border-radius: initial;
    background-color: initial;
    margin: 0;
    padding: 0;
    text-align: initial;
} */
</style>
 