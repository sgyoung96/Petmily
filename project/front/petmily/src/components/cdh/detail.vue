<template>
  <div id="DetailsItem" style="background-color:#e5e7eb; padding-top:50px">
    <div class="banner">
      <img style="height:100px; width: 900px;" src="../../assets/images/배너.png">&nbsp;
    </div>
    <div>
      <div class="container text-center">
        <div class="row" v-for="item in filteredItems" :key="item.desertionNo">
          <div class="col-1">
          </div>
          <div class="col-10" style="border: solid #e5e7eb; border-radius: 20px; padding:0px">
            <div style="background-color:white ; width:1100px; height:300px; border-radius:10px; padding:10px;">
              <img :src="item.popfile" :alt="item.careNm" style="width: 280px; height: 280px; border-radius: 10px;">
            </div>
          </div>
          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; padding: 30px; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h4 style="margin-bottom:10px">유기동물정보</h4>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div class="d-card">
                  <img src="@/assets/images/kindCd.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">품종</h5><br/>
                  <span>{{ item.kindCd }}</span>
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/age.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">나이</h5><br/>
                  <span>{{ item.age }}</span>
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/weight.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">체중</h5><br/>
                  <span>{{ item.weight }}</span>
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/processState.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">상태</h5><br/>
                  <span>{{ item.processState }}</span>
                </div>
                <div class="padd"></div>
              </div>
              <div style="flex: 1;">
                <div class="d-card">
                  <img src="@/assets/images/sexCd.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">성별</h5><br/>
                  <span v-if="item.sexCd === 'M'">수컷</span>
                  <span v-else-if="item.sexCd === 'F'">암컷</span>
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/color.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">색상</h5><br/>
                  <span>{{ item.colorCd }}</span>
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/neuterYn.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">중성화</h5> <br/>
                  <span v-if="item.neuterYn === 'Y'">네</span>
                  <span v-else-if="item.neuterYn === 'N'">아니요</span>
                  <span v-else>확인 X</span>
                </div>
                <div class="padd"></div>
              </div>
            </div>
            <div class="d-card">
            <img src="@/assets/images/점.png" alt="성별 코드 이미지" >
                  <h5 style="display: inline;">특별사항</h5><br/>
                  <span>{{ item.specialMark }}</span>
                </div>
          </div>

          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; padding:30px; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h4 style="margin-bottom:10px">동물보호센터</h4><br/>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div class="d-card">
                  <img src="@/assets/images/careNm.png" alt="보호센터 이미지" >
                  <h5 style="display: inline;">보호센터명</h5><br/>
                  <span>{{ item.careNm }}</span>
                  
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/orgNm.png" alt="담당자 이미지" >
                  <h5 style="display: inline;">담당자</h5><br/>
                  <span>{{ item.orgNm }}</span>
                  
                </div>
              </div>
              <div style="flex: 1;">
                <div class="d-card">
                  <img src="@/assets/images/careTel.png" alt="보호소 전화이미지" >
                  <h5 style="display: inline;">보호소전화번호</h5><br/>
                  <span>{{ item.careTel }}</span>
                  
                </div>
                <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/officetel.png" alt="담당자 전화 이미지" >
                  <h5 style="display: inline;">담당자전화번호</h5><br/>
                  <span>{{ item.officetel }}</span>
                  
                </div>
              </div>

            </div>
            <div class="padd"></div>
                <div class="d-card">
                  <img src="@/assets/images/careAddr.png" alt="보호장소 이미지" >
                  <h5 style="display: inline;">보호장소</h5><br/>
                  <span>{{ item.careAddr }}</span>
                </div>
            <br/>
            <div id="map"></div>
          </div>
          <br/>
          <div class="box-form" style="margin-top:10px; margin-bottom:10px"> <!-- 입양신청양식으로 이동 -->
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
      mapExists: false,
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
    setTimeout(() => {
    this.loadMap();
  }, 5000);
  
  } else {
    setTimeout(() => {
      this.loadScript();
  }, 5000);
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
      this.mapExists = true;
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
              "https://cdn-icons-png.flaticon.com/512/9922/9922172.png",
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

.banner {
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-bottom: 50px;
  text-align: center;
  background-color: white;
  padding: 24px 63px;
  display: flex;
  margin-left: 250px;
  margin-right: 250px;
}
.padd{
  padding-top: 10px;
}

.d-card span{
  margin-left: 25px;
  color: grey;
  font-size: 15px;
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
  width: 50px;
  height:30px;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
  background-color: rgb(244, 191, 79);
  border-radius: 30px;
  color: white;
  cursor: pointer;
}
</style>
