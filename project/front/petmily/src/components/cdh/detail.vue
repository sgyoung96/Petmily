<template>
  <div id="DetailsItem" style="background-color:#e5e7eb;">
    <div>
      <div class="container text-center">
        <div class="row" v-for="item in filteredItems" :key="item.desertionNo">
          <div class="col-1">
          </div>
          <div class="col-10" style="border: solid #e5e7eb; margin-top: 100px; border-radius: 20px;">
            <div style="margin-left: -12px;">
              <img :src="item.popfile" :alt="item.careNm" style="width: 400px; height: 400px; border-radius: 20px;">
            </div>
          </div>
          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h4>유기동물정보</h4>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div>
                  <img src="@/assets/images/kindCd.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">품종 : <span>{{ item.kindCd }}</span></h5>
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/age.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">나이 : <span>{{ item.age }}</span></h5>
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/weight.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">체중 : <span>{{ item.weight }}</span></h5>
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/processState.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">상태 : <span>{{ item.processState }}</span></h5>
                </div>
                <div class="padd"></div>
              </div>
              <div style="flex: 1;">
                <div>
                  <img src="@/assets/images/sexCd.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">성별 : <span v-if="item.sexCd === 'M'">수컷</span>
                  <span v-else-if="item.sexCd === 'F'">암컷</span></h5>
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/color.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">색상 : <span>{{ item.colorCd }}</span></h5>
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/neuterYn.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">중성화 : <span v-if="item.neuterYn === 'Y'">네</span>
                  <span v-else-if="item.neuterYn === 'N'">아니요</span>
                  <span v-else>확인 X</span></h5> 
                </div>
                <div class="padd"></div>
              </div>
            </div>
            <img src="@/assets/images/점.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;"><span>특별사항 : {{ item.specialMark }}</span></h5>
          </div>

          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h4>동물보호센터</h4>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div>
                  <img src="@/assets/images/careNm.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">보호센터명 : <span>{{ item.careNm }}</span></h5>
                  
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/careAddr.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">보호장소 : <span>{{ item.careAddr }}</span></h5>
                  
                </div>
                <div class="padd"></div>
                <div>
                  <img src="@/assets/images/orgNm.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">담당자 : <span>{{ item.orgNm }}</span></h5>
                  
                </div>
              </div>
              <div style="flex: 1;">
                <div>
                  <img src="@/assets/images/careTel.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">보호소전화번호 : <span>{{ item.careTel }}</span></h5>
                  
                </div>
                <div class="padd"></div>
                <div style="margin-top: 37px;"> <!-- 간격을 띄우는 div 요소 -->
                </div>
                <div>
                  <img src="@/assets/images/officetel.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">담당자전화번호 : <span>{{ item.officetel }}</span></h5>
                  
                </div>
              </div>
            </div>
            <div id="map"></div>
          </div>
          <div class="box-form"> <!-- 입양신청양식으로 이동 -->
            <span @click="apply()" class="txt-form">입양 신청하기</span>
          </div>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'DetailsItem',
  data() {
    return {
      items: [],
      careAddr: '',
      map: null,
      marker: null,
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
    };
  },

  mounted() {
  const mapElement = document.getElementById("map");
  if (window.kakao && window.kakao.maps && mapElement) {
    this.loadMap();
  } else {
    this.loadScript();
  }
},


  computed: {
    filteredItems() {
      if (!this.desertionNo) {
        return [];
      }

      var self = this;
      self.info = this.items.filter(item => item.desertionNo === this.desertionNo)[0];
      
      console.log(self.info);
      return this.items.filter(item => item.desertionNo === this.desertionNo);
    },
  },

  created: function () {
    this.$data.desertionNo = this.$route.query.desertionNo;
    this.$data.careAddr = this.$route.query.careAddr;
    this.fetchItems();

    alert(this.$data.careAddr);
  },

  methods: {
    fetchItems() {
      axios
        .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=1000&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
        .then((response) => {
          const data = response.data;
          const items = data.response.body.items.item;
          this.items = items; // 데이터를 items 배열에 할당
        })
        .catch(this.handleError); // handleError 함수 호출
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
    },
    apply() {
      const self = this;
      console.log(self.info);
      self.$router.push({name:'ApplyForm', query:{applyPetCd: this.$data.desertionNo, careAddr:this.$data.careAddr, info: JSON.stringify(self.info)}});
    }
  },
};
</script>

<style scoped>
.padd{
  padding-top: 10px;
}
img{
  width: 20px;
  height: 20px;
}
h4{
  font-family: 'IBMPlexSansKR-Bold';
}

h5{
  font-family: 'IBMPlexSansKR-Bold';
  padding-left: 10px;
}
.grid-container {
  display: grid;
  margin-left: 150px;
  margin-right: 200px;
  position: relative;
  grid-template-columns: repeat(1, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 10px;
}

.grid-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
#map {
    width: 100%;
    height: 400px;
  }

/* 입양신청하는폼 */
.box-form {
  position: relative;
  display: block;
  width: 100%;
}

.txt-form {
  width: 500px;
  height: 50px;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
  background-color: rgb(244, 191, 79);
  border-radius: 30px;
  color: white;
  cursor: pointer;
}
</style>
