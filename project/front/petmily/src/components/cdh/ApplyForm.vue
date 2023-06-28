<template>
    <div>
      <div class="base-info-container">
        <div class="base-info-pet">
          <div>
            <p>입양하실 아가의 기본정보예요.</p>

            <input type="text" v-model="info.kindCd" />
          </div>
        </div>

        <div class="base-info-center">
          <div>
            <p>아가를 보호하고 있는 곳의 정보예요.</p>
          </div>
        </div>
      </div>

      <div class="applier-container">
        <p>입양 신청 양식을 작성해 주세요.</p>

        <div>
          <div>

            <textarea style="width: 1000px; height: 300px;" readonly>
개인정보 취급방침

펫밀리 (이하 '단체'는) 입양신청자의 개인정보를 중요시하며, "정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다.
단체는 개인정보취급방침을 통하여 입양신청자께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.
■ 개인정보 수집에 대한 동의

1) "단체"는 이용자의 개인정보를 수집하는 경우에는 [위의 개인정보취급방침에 동의합니다]의 체크박스에 체크하는 절차를 마련하고 있으며, [위의 개인정보취급방침에 동의합니다]의 체크박스에 체크하였을 경우 개인정보 수집에 대하여 동의한 것으로 봅니다.
2) "단체"는 다음 사항에 해당하는 경우에 이용자의 별도 동의 없이 개인정보를 수집.이용할 수 있습니다.
- 서비스의 제공에 관한 계약의 이행을 위하여 필요한 개인정보로서 경제적.기술적인 사유로 통상의 동의를 받는 것이 현저히 곤란한 경우
- 서비스의 제공에 따른 요금정산을 위하여 필요한 경우
- 이 법 또는 다른 법률에 특별한 규정이 있는 경우

■ 수집하는 개인정보 항목

단체는 회원가입, 상담, 서비스 신청 등등을 위해 아래와 같은 개인정보를 수집하고 있습니다.

ο 수집항목 : 이름, 생년월일, 로그인ID, 비밀번호, 자택 전화번호, 자택 주소, 휴대전화번호, 이메일, 직업, 단체명, 단체전화번호, 주민등록번호, 은행계좌 정보, 접속 로그, 쿠키, 후원회비, 자동이체날짜, 봉사희망분야, 단체주소, 자기소개, 자기정보공개여부
ο 개인정보 수집방법 : 홈페이지(회원가입,후원하기)

■ 개인정보의 수집 및 이용목적

단체는 수집한 개인정보를 다음의 목적을 위해 활용합니다..

ο 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산
- 콘텐츠 제공
ο 회원 관리
- 회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불만처리 등 민원처리 , 고지사항 전달
ο 마케팅 및 광고에 활용
- 이벤트 등 광고성 정보 전달

■ 개인정보의 보유 및 이용기간

원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 단체는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.

- 보존 항목 : 이름, 휴대전화번호, 이메일, 은행계좌 정보, 후원회비, 자동이체날짜, 봉사희망분야, 단체주소, 자기소개, 자기정보공개여부
- 보존 근거 : 전자상거래등에서의 소비자보호에 관한 법률
- 보존 기간 : 5년
            </textarea> 
          </div>

        </div>

        위 개인정보취급방침에 동의합니다. <input name="age" type="radio" v-model="agreement" value="0">미동의 /
        <input name="ag" type="radio" v-model="agreement" value="1">동의<br/>

        <div><span>NAME</span><span>님의 정보를 기입해 주세요.</span></div>
        id:<input type="text" v-model="id" readonly><br/>

        

        <p>입양을 결심하신 이유를 알려주세요.</p>
       <input type="text" v-model="reason"><br/> 

        <p>다른 반려 동물이 있으십니까?</p>
        <input type="text" v-model="another"><br/>

        <p>반려 동물로 맞이한 후에 아가와 함께할 시간들을 생각하며, 입양 후 다짐을 자유롭게 기술해 주세요.</p>
        <input type="text" v-model="feeding"><br/>

        <div><span>입양신청일 : </span><input type="text" v-model="wdate" readonly></div>


        <button v-on:click="apply()">신청</button>
      </div>
    </div>

</template>
  
  <script>
  export default {
    name: 'ApplyForm',
    data () {
    return {
      id: sessionStorage.getItem('loginId'),
      wdate: '',
      agreement:0,
      another:'',
      reason:'',
      feeding:'',
      ischeck:0,
      applyPetCd: this.$route.query.applyPetCd,
      info: {
        target: {
          kindCd: '[개]믹스견',
          sexCd: '암컷',
          age: '2020(년생)',
          colorCd: '갈색',
          weight: '30(Kg)',
          neuterYn: '네',
          processState: '종료(반환)',
          specialMark: '특별사항',
          happenDt: '접수일시',
          happenPlace: '발견장소',
          careNm: '동물보호센터명',
          careAddr: '보호장소',
          orgNm: '담당자',
          careTel: '보호소 전화번호',
          officetel: '담당자 전화번호'
        }
      }
    }
  },
  created: function() {
    this.nowTimes();
    const self = this;
    self.info = JSON.parse(this.$route.query.info);
    console.log(self.info);

    
  },
  methods:{
    apply(){
      const self = this;

      let formdata = new FormData();

      const moment = require('moment');
      const today = moment(Date.now()).format('L');
      console.log(today);

      formdata.append('id', sessionStorage.getItem('loginId'));
      formdata.append('wdate', today);
      formdata.append('agreement', self.agreement);
      formdata.append('another', self.another);
      formdata.append('reason', self.reason);
      formdata.append('feeding', self.feeding);
      formdata.append('ischeck', self.ischeck);
      formdata.append('petCd', self.applyPetCd);

      self.$axios.post('http://localhost:8082/Applyform', formdata)//비동기 요청
      .then(function(res) {//요청 결과 받아옴. 파람 res에 결과저장됨. res.data가 백단에서 전송한 데이터
        if(res.status == 200){
          if(res.data.dto != null){
            self.msg = '입양 신청이 완료되었습니다.';
            alert(self.msg);
            self.$router.go(-1);
          } else {
            self.msg = '신청 양식을 보낸 상태입니다';
            alert(self.msg);
          }
        }else{
          alert('에러코드:'+res.status)
        }
      });
    },
    setDate() {
          let year = new Date().getFullYear();
          let month =new Date().getMonth() + 1 < 10? "0" + (new Date().getMonth() + 1): new Date().getMonth() + 1;
          let date =new Date().getDate() < 10? "0" + new Date().getDate(): new Date().getDate();
          let hh =new Date().getHours() < 10? "0" + new Date().getHours(): new Date().getHours();
          let mm =new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
          let ss =new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
      return {
            'year' : year
          , 'month' : month
          , 'date' : date
          , 'hh' : hh
          , 'mm' : mm
          , 'ss' : ss
      }
    },
    nowTimes() {
      this.wdate = this.setDate().year + "-" + this.setDate().month + "-" + this.setDate().date + " " +
                   this.setDate().hh + ":" + this.setDate().mm + ":" + this.setDate().ss;
    }
  }
}
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
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


  </style>
  