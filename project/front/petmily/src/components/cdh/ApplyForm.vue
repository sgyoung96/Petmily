<template>
  <div class="base-info-container">
    <div class="base-info-pet">
      <div>
        <p>입양하실 아가의 기본정보예요.</p>
        <div class="row">
          <div class="col-3">
          </div>
          <div class="col-8" >
            <div class="row" style="display: flex; text-align: left; align-items: center;">
              <div class="col-sm-4" >
                <img :src="info.popfile" style="width: 300px; height: 300px; border-radius: 20px; margin-left:-10px align-items: left;">
              </div>
              <div class="col-sm-2" style="text-align: left; align-items: center;">
                <strong class="g-color-black" style="padding-left: 20px;">견종</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">성별</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">나이</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">색</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">몸무게</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">중성화여부</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">현상황</strong><br />
                <strong class="g-color-black" style="padding-left: 20px;">특별사항</strong><br />
              </div>
              <div class="col-sm-4" style="text-align: left; align-items: center;">
                <strong class="g-color-black" >{{ info.kindCd }}</strong><br/>
              <strong class="g-color-black" v-if="info.sexCd === 'M'">수컷</strong>
              <Strong class="g-color-black" v-else-if="info.sexCd === 'F'">암컷</strong>
              <Strong class="g-color-black" v-else>미상</strong><br/>
              <strong class="g-color-black" >{{ info.age }}</strong><br/>
              <strong class="g-color-black" >{{ info.colorCd }}</strong><br/>
              <strong class="g-color-black" >{{ info.weight }}</strong><br/>
              <strong class="g-color-black" v-if="info.neuterYn === 'Y'">중성화O</strong>
              <Strong class="g-color-black" v-else-if="info.neuterYn === 'N'">중성화X</strong>
              <Strong class="g-color-black" v-else-if="info.neuterYn === 'U'">미상</strong><br/>
              <strong class="g-color-black" >{{ info.processState }}</strong><br/>
              <strong class="g-color-black" >{{ info.specialMark }}</strong><br/>
             </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="base-info-center" style="padding-top :20px;">
      <div>
        <p>아가를 보호하고 있는 곳의 정보예요.</p>
        <div class="row">
          <div class="col-3">
          </div>
          <div class="col-8" >
            <div class="row" style="display: flex; text-align: left; align-items: center;">
              <div class="col-sm-4" id="map">
              </div>
              <div class="col-sm-2" style="text-align: left; align-items: center;">
                <strong class="g-color-black" style="padding-left: 20px;">접수일시</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">발견장소</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">동물보호센터명</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">보호장소</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">담당자</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">보호소 전화번호</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">담당자 전화번호</strong><br/>
              </div>
              <div class="col-sm-6" style="text-align: left; align-items: center;">
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.happenDt }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.happenPlace }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.careNm }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.careAddr }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.orgNm }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.careTel }}</strong><br/>
                <strong class="g-color-black" style="padding-left: 20px;">{{ info.officetel }}</strong><br/>
             </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="applier-container">
    <p>입양 신청 양식을 작성해 주세요.</p>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">NAME님의 정보를 기입해 주세요.</div>
      <div class="col-3"><input type="text" style="width:250px" v-model="id" readonly><br /></div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">다른 반려 동물이 있으십니까?</div>
      <div class="col-3"><input type="text" style="width:250px" v-model="another"><br /></div>
    </div>

    <p style="padding-top: 20px;">입양을 결심하신 이유를 알려주세요.</p>
    <textarea style="width: 800px; height: 300px; padding-top:10px;" v-model="reason"></textarea><br />

    <p style="padding-top: 20px;">반려 동물로 맞이한 후에 아가와 함께할 시간들을 생각하며, 입양 후 다짐을 자유롭게 기술해 주세요.</p>
    <textarea style="width: 800px; height: 300px;" v-model="feeding"></textarea><br />

    <p style="padding-top: 20px;">개인정보 취급 동의서</p>
    <div>
      <div>

        <textarea style="width: 800px; height: 300px;" readonly>
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
    <input name="ag" type="radio" v-model="agreement" value="1">동의<br />

    <strong class="g-color-black" style="padding-right:20px; padding-left:20px">입양신청일</strong>
    <strong class="g-color-black" style="padding-right:20px; padding-left:20px">{{ wdate }}</strong>

    <button v-on:click="apply()" style="background-color:#FFD65B; border-radius:10px border-;">신청</button>
  </div>
</template>
  
<script>
import axios from 'axios';
export default {
  name: 'ApplyForm',
  data() {
    return {
      id: sessionStorage.getItem('loginId'),
      wdate: '',
      agreement: 0,
      another: '',
      reason: '',
      feeding: '',
      ischeck: 0,
      applyPetCd: this.$route.query.applyPetCd,
      careAddr: this.$data.careAddr,
      map: null,
      marker: null,
      info: {
        target: {
          filename: '',
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
  created: function () {
    this.nowTimes();
    const self = this;
    self.info = JSON.parse(this.$route.query.info);
    console.log(self.info);
    this.$data.careAddr = this.$route.query.careAddr;

    alert(this.$data.careAddr);
  },

  mounted() {
    const mapElement = document.getElementById("map");
    if (window.kakao && window.kakao.maps && mapElement) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    apply() {
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
        .then(function (res) {//요청 결과 받아옴. 파람 res에 결과저장됨. res.data가 백단에서 전송한 데이터
          if (res.status == 200) {
            if (res.data.dto != null) {
              self.msg = '입양 신청이 완료되었습니다.';
              alert(self.msg);
              self.$router.go(-1);
            } else {
              self.msg = '신청 양식을 보낸 상태입니다';
              alert(self.msg);
            }
          } else {
            alert('에러코드:' + res.status)
          }
        });
    },
    setDate() {
      let year = new Date().getFullYear();
      let month = new Date().getMonth() + 1 < 10 ? "0" + (new Date().getMonth() + 1) : new Date().getMonth() + 1;
      let date = new Date().getDate() < 10 ? "0" + new Date().getDate() : new Date().getDate();
      let hh = new Date().getHours() < 10 ? "0" + new Date().getHours() : new Date().getHours();
      let mm = new Date().getMinutes() < 10 ? "0" + new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds() < 10 ? "0" + new Date().getSeconds() : new Date().getSeconds();
      return {
        'year': year
        , 'month': month
        , 'date': date
        , 'hh': hh
        , 'mm': mm
        , 'ss': ss
      }
    },
    nowTimes() {
      this.wdate = this.setDate().year + "-" + this.setDate().month + "-" + this.setDate().date + " " +
        this.setDate().hh + ":" + this.setDate().mm + ":" + this.setDate().ss;
    },
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=90a9e14a8d8d8c4e2a9a92bd4ca90bbb&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap.bind(this));

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(37.541, 126.986),
        level: 3
      };

      this.map = new window.kakao.maps.Map(container, options);

      this.showLocation();
    },
    showLocation() {
      if (this.careAddr === "") {
        alert("주소를 입력하세요.");
        return;
      }
      const encodedAddress = encodeURIComponent(this.careAddr);
      const apiUrl = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodedAddress}&key=AIzaSyAEMcBVXcTsB5UmbNou29kkZkSPpq4mDJA`;

      axios
        .get(apiUrl)
        .then(response => {
          const result = response.data.results[0];
          if (result) {
            const location = result.geometry.location;
            const latitude = location.lat;
            const longitude = location.lng;

            const position = new window.kakao.maps.LatLng(latitude, longitude);

            if (this.marker) {
              this.marker.setMap(null);
            }

            this.map.setCenter(position);
            this.map.setLevel(3);

            // Custom marker image
            const markerImage = new window.kakao.maps.MarkerImage(
              "https://media.istockphoto.com/id/1268251949/ko/%EB%B2%A1%ED%84%B0/%ED%94%8C%EB%9E%AB-%EC%95%A0%EC%99%84-%EB%8F%99%EB%AC%BC-gps-%EB%A1%9C%EA%B3%A0-%EB%94%94%EC%9E%90%EC%9D%B8-%EA%B0%9C-%EB%A7%B5-%EB%A7%88%EC%BB%A4-%EB%B2%A1%ED%84%B0-%EB%8F%99%EB%AC%BC-%EC%82%B0%EC%B1%85%EC%9D%80-%EC%9C%84%EC%B9%98-%EC%9C%84%EC%B9%98%EC%97%90-%EC%A3%BC%EC%9D%98%ED%95%A9%EB%8B%88%EB%8B%A4-%EC%95%A0%EC%99%84-%EB%8F%99%EB%AC%BC-%EC%9B%B9-%EC%9D%91%EC%9A%A9-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%EC%97%90-%EB%8C%80%ED%95%9C-%EB%84%A4%EB%B9%84%EA%B2%8C%EC%9D%B4%EC%85%98-%EA%B8%B0%ED%98%B8-%EA%B7%80%EC%97%AC%EC%9A%B4-%ED%96%89%EB%B3%B5-%EA%B0%95%EC%95%84%EC%A7%80.jpg?s=612x612&w=0&k=20&c=hBf2iFyW03qcuBKd_WoD8g96ZA6sk1dwHk3SMuZoIQg=", // 강아지 모양 이미지 URL로 변경
              new window.kakao.maps.Size(50, 50)
            );

            this.marker = new window.kakao.maps.Marker({
              position: position,
              image: markerImage // Custom marker image 설정
            });

            this.marker.setMap(this.map);
          } else {
            alert("주소를 찾을 수 없습니다.");
          }
        })
        .catch(error => {
          console.error("Error:", error);
          alert("주소를 가져오는 도중 오류가 발생했습니다.");
        });
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

#map {
  width: 300px;
  height: 300px;
}

#block {
  border: #42b983;
}
</style>
  