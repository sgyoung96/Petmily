<template>
    <div id="DetailsItem" style="background-color:#e5e7eb;">

      <div class="banner">
        <img style="height:100px; width: 900px;" src="../../assets/images/배너.png">&nbsp;
      </div>
        <div v-for="item in item" :key="item.careNm">
            <div class="d-card">
              <div style=" margin-bottom:10px;">
              <strong style="font-size:26px;">보호센터정보</strong>
            </div>
            <div style="display:inline-block">
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/동물보호센터명.png">&nbsp;
                  <h3>동물보호센터명</h3>
                </div>
                  <span>{{ item.careNm }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/phone.png">&nbsp;
                  <h3>전화번호</h3>
                  </div>
                  <span>{{ item.careTel }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/관리기간명.png">&nbsp;
                <h3>관리기관명</h3>
              </div>
                  <span>{{ item.orgNm }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/동물보호센터명.png">&nbsp;
                  <h3>동물보호센터유형</h3>
                  </div>
                  <span>{{ item.divisionNm }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/구조대상동물.png">&nbsp;
                  <h3>구조대상동물</h3>
                </div>
                  <span>{{ item.saveTrgtAnimal }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/센터지정일자.png">&nbsp;
                  <h3>동물보호센터지정일자</h3>
                  </div>
                  <span>{{ item.dsignationDate }}</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/수의사.png">&nbsp;
                  <h3>수의사수</h3>
                </div>
                  <span>{{ item.vetPersonCnt }}명</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/사양관리사.png">&nbsp;
                  <h3>사양관리사인원수</h3>
                  </div>
                  <span>{{ item.specsPersonCnt }}명</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                    <img src="../../assets/images/점.png">&nbsp;
                  <h3>진료실수</h3>
                </div>
                  <span>{{ item.medicalCnt }}실</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                    <img src="../../assets/images/점.png">&nbsp;
                  <h3>사육실수</h3>
                  </div>
                  <span>{{ item.breedCnt }}실</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                    <img src="../../assets/images/점.png">&nbsp;
                  <h3>격리실수</h3>
                </div>
                  <span>{{ item.quarabtineCnt }}실</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                    <img src="../../assets/images/점.png">&nbsp;
                  <h3>사료보관실수</h3>
                  </div>
                  <span>{{ item.feedCnt }}실</span>
                </div>
                <div class="a-card">
                  <div class="e-card">
                    <img src="../../assets/images/구급차.png">&nbsp;
                  <h3>구조운반용차량보유대수</h3>
                  </div>
                  <span>{{ item.transCarCnt }}대</span>
                </div>
              </div>
            </div>
            <div class="d-card">
              <div style=" margin-bottom:10px;">
              <strong style="font-size:26px;">운영시간</strong>
            </div>
            <div style="display:inline-block">
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>평일운영시간</h3>
                </div>
                  <span>{{ item.weekOprStime }}~{{ item.weekOprEtime }}</span>
              </div>
                <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>평일분양시간</h3>
                </div>
                  <span>{{ item.weekCellStime }}~{{ item.weekCellEtime }}</span>
              </div>
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>주말운영시간</h3>
                </div>
                  <span>{{ item.weekendOprStime }}~{{ item.weekendOprEtime }}</span>
              </div>
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>주말분양시간</h3>
                </div>
                  <span>{{ item.weekendCellStime }}~{{ item.weekendCellEtime }}</span>
              </div>
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/휴무일.png">&nbsp;
                  <h3>휴무일</h3>
                </div>
                  <span>{{ item.closeDay }}</span>
              </div>
            </div>
            </div>
            <div class="d-card">
              <div style=" margin-bottom:10px;">
              <strong style="font-size:26px;">운영시간</strong>
            </div>
            <div style="display:inline-block">
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>보호센터주소</h3>
                </div>
                  <span>{{ item.careAddr }}</span>
              </div>
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>위도</h3>
                </div>
                  <span>{{ item.lat }}</span>
              </div>
              <div class="a-card">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;
                  <h3>경도</h3>
                </div>
                  <span>{{ item.lng }}</span>
              </div>
            <div style="display: flex;">
              </div>
            </div>
            <div id="map"></div>
          </div>
        </div>

  </div>

</template>
<style scoped>
.banner{
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-bottom: 50px;
  text-align: center;
  background-color:white;
  padding:24px 63px;
  display:flex;
  margin-left:250px;
  margin-right:250px;
}
.d-card{
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-bottom: 50px;
  position: relative;
  text-align: left;
  background-color:white;
  padding:24px;
  display:flex;
  margin-left:250px;
  margin-right:250px;
  flex-direction: column;
  display: flex;
}
.a-card{
  margin-bottom:10px;
  float: left;
  width: 50%;
}
.e-card{
  display: flex;
}
.d-card img{
  width:20px;
  height:20px;
}
.d-card h3{
  font-size:16px;
  margin-bottom:0px;
  font-weight: bold;
}

.d-card span{
  margin-left:25px;
  color:grey;
  font-size:15px;
  }
</style> 
  <script>
  import axios from 'axios';
  
  export default {
    name: 'AnimalHospital',
    data() {
      return {
        item: [],
        addr:'',
        name:''
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
    created() {
      this.addr = this.$route.params.addr;
      this.name = this.$route.params.name;
      this.fetchData(this.name); // 페이지가 생성될 때 데이터 가져오기
      
      alert(this.name)
    },
    methods: {
      fetchData(name) {
        const self = this;
        alert(name)
        self.$axios.get(`https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?_type=json&care_nm=${name}&numOfRows=1&pageNo=1&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D`)
        .then(function(res){
            if(res.status==200){
                const data = res.data.response.body
                self.item = data.items.item;
            }else{
                alert(res.status)
            }
        })
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
      if (this.addr === "") {
        alert("주소를 입력하세요.");
        return;
      }
      const encodedAddress = encodeURIComponent(this.addr);
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
    },
  };
  </script> 