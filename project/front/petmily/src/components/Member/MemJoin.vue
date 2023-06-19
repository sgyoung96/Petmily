<template>
    <div id="myjoin">
        <h3>join form</h3>
        <input class="input-item" type="text" v-model="id" placeholder="ID" @blur="idHealthCheck"><button v-on:click="idcheck">중복체크</button><br/>
        {{msg}}<br/>
        <!-- <span class ="font_id_red" v-show="isIDNullCheck">ID를 입력 해 주세요</span>
        <span class ="font_id_red" v-show="isIDSpaceCheck">공백을 입력할 수 없습니다</span>
        <span class ="font_id_red" v-show="isIDKoeanCheck">한글은 입력할 수 없습니다</span>
        <span class ="font_id_red" v-show="isIDSpecialCheck">특수문자는 입력할 수 없습니다</span>
        <span class ="font_id_red" v-show="isIDGoodCheck">중복 확인 필수</span>
        <span class ="font_id_blue" v-show="isIDAvailable">사용가능</span> -->


        <input class="input-item" type="text" v-model="name" placeholder="NAME"><br/>
        
        <input class="input-item" type="password" id="pwd" v-model="pwd" placeholder="PWD" v-on:click="check"><br/>
        <input class="input-item" type="password" v-model="pwdcheck" placeholder="PWD 확인"><br/>
        
        <input class="input-item" type="text" v-model="email" placeholder="예)petmily@petmily.co.kr" @blur="EmailCheck" ><br/>
        <!-- <span class ="font_id_red" v-show="isEmailCheck">이메일 형식 확인해주세요</span> -->
       
        
        
        birth : <input  type="date" v-model="birth" ><br/>  
        <input name="g" type="radio" v-model="gender" value="m">남 / 
            <input name="g" type="radio" v-model="gender" value="f">여 <br/>
        <input class="input-item" type="text" v-model="phone" placeholder="PHONE"><br/>
        
        <input  type="text" v-model="postcode" placeholder="우편번호" readonly>
        <button id="postcode" @click="execDaumPostcode">검색</button><br/>
        <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
        <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
        <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
       
       <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
          <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" v-on:click="foldDaumPostcode()" alt="접기 버튼">
        </div>
        <button v-on:click="join">가입</button>
    </div>
  </template>
 
 <script>

export default {
   
  name: 'MemJoin',
  data () {
   
    return {
      
      id:'',
      pwd:'',
      name:'',
      email:'',
      birth:'',
      gender:'',
      phone:'',
      postcode:'',
      roadAddress:'',
      detailAddress:'',
      extraAddress:'',
      address:'',
      msg:''
    }

    
   
  },


 
  methods:{
    

    join(){
      const self = this;

      const moment = require('moment');
      const mybirth = moment(self.birth).format('L');

      alert(this.id + "/" + this.pwd + "/" + this.name + "/" + this.email + "/" + mybirth)

      var address = self.roadAddress
      address += self.detailAddress 
      if(self.extraAddress != ''){
      address += "," + self.extraAddress
      }

      let formdata = new FormData();
      formdata.append('id',self.id)
      formdata.append('pwd',self.pwd)
      formdata.append('name',self.name)
      formdata.append('email',self.email)
      formdata.append('gender',self.gender)
      formdata.append('birth',mybirth)
      formdata.append('phone',self.phone)
      formdata.append('address', address)
      
      self.$axios.post('http://localhost:8082/members', formdata) 
      .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
        alert(dto.id +'님 회원가입 완료')
         window.location.href = "/"
        }else{
          alert('에러코드:' + res.status)
        }
      });
    },
  
  idcheck(){
    const self = this;
     
    self.$axios.get('http://localhost:8082/members/'+self.id)
    .then(function(res){ //
        if(res.status == 200){
        
          if(res.data.dto == null){
            self.msg='사용가능한 아이디'
          
          }else{
             self.msg='중복된 아이디'
             self.id=''
          }
        }else{
          alert('에러코드 :' + res.status)
        }
  });
  },

  /*EmailCheck(){
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/
     
    if(validateEmail.test(this.email)){
        this.isEmailAvailable = true;
    }else{
        this.isEmailCheck = true;
    }
  
  },*/
  
  execDaumPostcode(){
    const element_wrap =  this.$refs.wrap;
    

    // 현재 scroll 위치를 저장해놓는다.
    const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);

    new window.daum.Postcode({
        oncomplete:(data) =>{

            var addr='';
            var extraAddr='';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
            // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

            // 조합된 참고항목을 해당 필드에 넣는다.
                    this.extraAddress = extraAddr;
                
                } else {
                    this.extraAddress = '';
                }    
                
          
            this.postcode = data.zonecode;
            this.roadAddress = addr;

            element_wrap.style.display = 'none';
            document.body.scrollTop = currentScroll;
        },
         // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
    }).embed(element_wrap)
    element_wrap.style.display = 'block';
    },

      foldDaumPostcode () {
    const element_wrap =  this.$refs.wrap;
    
      element_wrap.style.display = 'none';
    },

 
}
}


</script>