<template>
  <div>
    
  </div>
    <div class="d-all">
    <div class="d-title">
      
      <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
          &nbsp;<span style="color:rgb(244, 191, 79);">VOLUNTEER</span></strong></h4>
    </div>
    <div class="d-box" style="text-align: center;">
      <img src="https://media.tenor.com/acgNzAvdOKIAAAAC/thank-you-volunteers-poll-workers.gif" style="width:360px; height: 270px; margin-bottom: 20px;">
      <div style="border-bottom: solid black 2px; margin-bottom:15px; width: 80%; text-align: left; font-weight: 900; font-size:36px; font-family: 'Jua', sans-serif;">봉사게시판 글쓰기</div>
      <table class="box-title">
  <tr>
    <td>작성자:</td>
    <td><input type="text" id="writer" v-model="writer" readonly style="margin-bottom:5px"></td>
  </tr>
  <tr>
    <td>제목:</td>
    <td><input type="text" id="title" v-model="title" size="80" maxlength="20" placeholder="제목을 입력해 주세요."></td>
  </tr>
  <tr>
    <td>모집인원:</td>
    <td><input type="number" v-model="vol_number"></td>
  </tr>
  <tr>
    <td>봉사모집기관:</td>
    <td><input type="text" id="place" v-model="place" style="margin-bottom:5px"></td>
  </tr>
  <tr>
    <td>모집마감일:</td>
    <td><input type="date" class="form-control" v-model="deadline"></td>
  </tr>
  <tr>
    <td>봉사일자:</td>
    <td><input type="date" class="form-control" v-model="vol_date" required></td>
  </tr>
  <tr>
    <td>봉사장소주소:</td>
    <td>
      <input type="text" v-model="postcode" placeholder="우편번호" readonly>
      <button id="postcode" @click="execDaumPostcode">검색</button><br/>
      <input type="text" v-model="roadAddress" placeholder="주소" readonly><br/>
      <input type="text" v-model="detailAddress" placeholder="상세주소"><br/>
      <input type="text" v-model="extraAddress" placeholder="참고항목"><br/>
      <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
        <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" v-on:click="foldDaumPostcode()" alt="접기 버튼">
      </div>
    </td>
  </tr>
</table>

      <div class="box-content">
        <textarea id="content" v-model="content" rows="25" cols="130" style="resize: none;">
        </textarea>
      </div>
      <tr>
        <th>이미지1</th>
        <td><input type="file" id="f1"></td>
      </tr>
      <tr>
        <th>이미지2</th>
        <td><input type="file" id="f2"></td>
      </tr>
      <tr>
        <th>등록</th>
        <td><button v-on:click="add">등록</button></td>
      </tr>
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
        content: '1. 내용: \n\n' +
        '2. 일시: \n\n' +
        '3. 장소: \n\n' +
        '4. 모집인원: \n\n' +
        '5. 자격요건: \n\n' +
        '6. 관련문의:',
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
      chkKakaoValidatoion() {
        if (sessionStorage.getItem('loginFlag') == 'kakao') {
          const self = this;
          self.$axios.get('http://localhost:8082/members/' + self.writer).then (function(rs) {
            console.log(rs.data.dto);
          
            if (rs.data.dto == null) {
              self.$router.push({name:'KakaoAdditionalForm', query:{kakaoId: sessionStorage.getItem('loginId'), kakaoName: sessionStorage.getItem('kakaoName')}});
            }
          });
        }
      },
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
      this.chkKakaoValidatoion();
    }
  }
  </script>
  <style scoped>
.d-all {
  margin-left: 150px;
  margin-right: 150px;
  margin-bottom: 150px;
}

.d-title {
  flex-direction: column;
  display: flex;
  margin-top: 120px;
  margin-bottom: 120px;
}

.d-box {
  border: 1px solid grey;
  border-top: 2px solid black;
  padding: 80px;
  text-align: left;
}

.box-title{
  text-align: left;
}

.box-warning{
  margin-top:10px;
  margin-bottom:10px;
  font-size:small;
}

.box-content{
  resize: none;
}
</style>