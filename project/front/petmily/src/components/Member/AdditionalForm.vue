<template>
  <div class="additional-form-container">
    <div class="form-header">
        <div><span class="form-header-title-msg">서비스 이용을 위해 추가 정보를 기입해 주세요.</span></div>
        <span class="form-header-sub-msg">SNS 로그인시 원활한 서비스 이용을 위해 최초 1회만 작성합니다.</span>
    </div>
    




    <h3>서비스 이용을 위해 추가 정보를 기입해 주세요.</h3>
        KAKAO NICKNAME: <input class="input-item" type="text" v-model="name" placeholder="NAME" on:click="" readonly><br/>

        BIRTH : <input  type="date" v-model="birth" ><br/>  
        <input name="g" type="radio" v-model="gender" value="m" >남 / 
            <input name="g" type="radio" v-model="gender" value="f">여 <br/>
        PHONE : <input class="input-item" type="text" v-model="phone" placeholder="예)010-1234-5678" ><br/>
         <span class ="font_id_red" v-show="isPhoneCheck">전화번호 형식을 확인해주세요</span><br/> 
        
        
        <input  type="text" v-model="postcode" placeholder="우편번호" readonly>
        <button id="postcode" @click="execDaumPostcode">검색</button><br/>
        <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
        <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
        <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
       
       <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
          <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" v-on:click="foldDaumPostcode()" alt="접기 버튼">
        </div>
        <button v-on:click="join">회원정보수정</button>
    </div>

</template>

  <script>

export default {
  name: 'KakaoAdditionalForm',
  data () {
    return {
      
      name:'',
      
      birth:'',
      gender:'m',
      phone:'',
      postcode:'',
      roadAddress:'',
      detailAddress:'',
      extraAddress:'',
      address:'',
      msg:''
    }
  },
  created: function () {
      if (sessionStorage.getItem('loginFlag') == 'kakao') {
        this.name = sessionStorage.getItem('kakao_name');
      }
  },
  watch : {
      
     
    'name': function(){
        this.checkName()
        },
    'email': function(){
        this.checkEmail()
        },
    'pwd' : function(){
        this.checkPwd()
        },
    'phone' : function(){
        this.checkPhone()
         
    }
  },  
  methods:{
    checkName(){
            const validateName = /^[가-힣a-zA-Z]+$/;
            if(!validateName.test(this.name)){
        
            this.isNameCheck = true;
        }else{
            this.isNameCheck = false;
        }
    },
        checkPhone(){
        const validatePhone = /^\d{3}-\d{3,4}-\d{4}$/;
        if(!validatePhone.test(this.phone)){
            this.isPhoneCheck = true;
        }else{
            this.isPhoneCheck = false;
        }
    },
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
    join(){
      const self = this;

      const moment = require('moment');
      const mybirth = moment(self.birth).format('L');

     
      var address = self.roadAddress
      address += self.detailAddress 
      if(self.extraAddress != ''){
      address += "," + self.extraAddress
      }

      let formdata = new FormData();
      formdata.append('id', sessionStorage.getItem('loginId'))
      formdata.append('name',self.name)
      formdata.append('gender',self.gender)
      formdata.append('birth',mybirth)
      formdata.append('phone',self.phone)
      formdata.append('address', address)
      
      self.$axios.post('http://localhost:8082/members', formdata) 
      .then(function(res){ 
        if(res.status == 200){
          let dto = res.data.dto
        alert(dto.name +'님 회원가입 완료')
         window.location.href = "/"
        }else{
          alert('에러코드:' + res.status)
        }
      });
    },
  }
  
}
</script>

<style scoped>

.additional-form-container {
    font-family: 'IBMPlexSansKR-Regular';
}

.form-header {
    
}

.form-header-title-msg {
    font-family: 'IBMPlexSansKR-Bold';    
    font-size: 20px;
}

.form-header-sub-msg {
    font-family: 'IBMPlexSansKR-ExtraLight';    
    font-size: 12px;
    color: black;
}

</style>
