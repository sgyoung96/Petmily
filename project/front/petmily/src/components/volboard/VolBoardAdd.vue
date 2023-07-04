<template>
    <div id="app">
        <h3>봉사모집게시판 등록</h3>
        <div class="container">
    <div class="form-container">
      <h2>{{writer}}봉사모집게시판 추가</h2>
        <div class="form-group">
          <label for="volboard-writer">작성자</label>
          <input type="text" class="form-control" v-model="writer" required>
        </div>
        <div class="form-group">
          <label for="volboard-title">제목</label>
          <input type="text" class="form-control" v-model="title" required>
        </div>
        <div class="form-group">
          <label for="volboard-content">내용</label>
          <input type="textarea" class="form-control" v-model="content" required>
        </div>
        <div class="form-group">
          <label for="volboard-count">모집인원</label>
          <input type="number" class="form-control" v-model="vol_number">
        </div>
        <div class="form-group">
          <label for="volboard-count">봉사모집기관</label>
          <input type="text" class="form-control" v-model="place">
        </div>
        <div class="form-group">
          <label for="volboard-count">모집마감일</label>
          <input type="date" class="form-control" v-model="deadline">
        </div>
        <div class="form-group">
          <label for="volboard-address">봉사장소주소</label>
          <input  type="text" v-model="postcode" placeholder="우편번호" readonly>
          <button id="postcode" @click="execDaumPostcode">검색</button><br/>
          <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
          <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
          <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
          <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
          <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" v-on:click="foldDaumPostcode()" alt="접기 버튼">
        </div>
        </div>
        <div class="form-group">
          <label for="volboard-voldate">봉사일자</label>
          <input type="date" class="form-control" v-model="vol_date" required>
        </div>
        <div class="form-group">
          <label for="volboard-img1">게시판이미지1</label>
          <input type="file" class="form-control" id="f1">
        </div>
        <div class="form-group">
          <label for="volboard-img2">게시판이미지2</label>
          <input type="file" class="form-control" id="f2">
        </div>
        
        <button type="button" class="btn btn-primary" v-on:click="add">추가</button>
        <button type="button" class="btn btn-secondary" v-on:click="cancel">취소</button>
    </div>
  </div>
    </div>
</template>
  
  <script>
  export default {
    name: 'VolBoardAdd',
    data () {
      return {
        writer: sessionStorage.getItem('loginId'),
        title: '',
        content: '',
        vol_number: 0,
        vol_date:'',
        postcode:'',
        roadAddress:'',
        detailAddress:'',
        extraAddress:'',
        address: '',
        deadline: '',
        place: '',
        count: 0,
        cnt: 0
      }
    },
    methods:{
      add(){
        const self = this
        const moment = require('moment');
        const voldate = moment(self.vol_date).format('L');
        const deadline = moment(self.deadline).format('L');

        var address = self.roadAddress
        address += self.detailAddress 
        if(self.extraAddress != ''){
        address += "," + self.extraAddress
        }

        let formData = new FormData();
        formData.append('writer', this.writer)
        formData.append('title', this.title)
        formData.append('content', this.content)
        formData.append('vol_number', this.vol_number)
        formData.append('w_date', new Date())
        formData.append('vol_date', voldate)
        formData.append('deadline', deadline)
        formData.append('place', this.place)
        formData.append('address', address)
        formData.append('count', 0)
        formData.append('cnt', 0)
        const file1 = document.getElementById('f1')
        const file2 = document.getElementById('f2')
        
        formData.append('f[0]', file1.files[0]);
        formData.append('f[1]', file2.files[0]);
        
        
        self.$axios.post('http://localhost:8082/volboard', formData, {headers:{"Content-Type":"multipart/form-data"}})
        .then(function(res){
          if(res.status == 200){
            self.$router.push('/volboardhome')
          }else{
            alert("에러코드:" + res.status)
          }
        })
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
      },
    created:function(){//이 컴포넌트가 시작될때 실행되는 함수
    }
  }
  </script>
  <style scoped>
  img{
      width:100px;
      height: 100px;
  }
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.form-container {
      margin-top: 20px;
      max-width: 500px;
    }
</style>