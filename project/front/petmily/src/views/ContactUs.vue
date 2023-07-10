<template>
  <link
    rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"
  />

  <div style="position: relative">
   

  <img
    src="@/assets/images/contactus2.jpg"
    alt="컨택트 이미지"
    style="width: 100%; padding-bottom: 10px; position: relative;"
  />
  <div class="wrapper1">Contact us</div>
  <div class="wrapper2" sytle="font-size:50px;display:block">about petmily</div>
  </div>
 

  <div class="info">
    <div class="info_box">
      <div class="info_tit">이메일</div>
      <span class="material-symbols-outlined"> alternate_email </span>
      <p>petmily@petmily.com</p>
    </div>

    <div class="info_box">
      <div class="info_tit">전화번호</div>
      <div class="info_phones">
        <div class="info_phone">
          <span class="material-symbols-outlined"> phone_in_talk </span>
          <p>031-5678-1234</p>
        </div>
        <div class="info_phone">
          <span class="material-symbols-outlined"> phone_iphone </span>
          <p>010-1234-5678</p>
        </div>
      </div>
    </div>

    <div class="info_box">
      <div class="info_tit">운영시간</div>
      <span class="material-symbols-outlined"> schedule </span>
      <p>월요일-금요일: 9:00 AM - 5:00 PM</p>
      <p>토요일: 10:00 AM - 2:00 PM</p>
      <p>일요일/공휴일: Closed</p>
    </div>
  </div>

    <div class="info"  style="margin-bottom:10px" >
      <div  class="info_box2">
        <div class="info_tit"> 주소 </div>
        <p>경기도 성남시 분당구 성남대로 34 6층(구미동 하나프라자빌딩)</p>
      </div>

      <div class="info_box2">
        <div class="info_tit"> 오시는 길 </div>
        <p>분당선 오리역 3번출구 전방 30m.</p>
        <p>주차장, 주변교통이 혼잡 하오니 대중교통을 이용하여 주시기 바랍니다.</p>
        <p>인근주차장안내: 오리역 공영주차장, 하나로클럽마트 성남점</p>
      </div>
    </div>
 <span
      class="material-symbols-outlined"
      @click="toggleMap"
      style="
        font-weight: initial;
        font-size: 40px;
        color: black;
        cursor: pointer;
      "
      >add</span>

  

  <div id="map" v-show="showMap"></div>
</template>

<script>
import "@/assets/fonts/IBMPlexSansKR.css";

export default {
  data() {
    return {
      showMap: true,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=90a9e14a8d8d8c4e2a9a92bd4ca90bbb&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(37.3388756, 127.1093775),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);

      const markerPosition = new window.kakao.maps.LatLng(
        37.3388756,
        127.1093775
      );

      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
    toggleMap() {
      this.showMap = !this.showMap;
    },
  },
};
</script>
  <style scoped>
.material-symbols-outlined {
  font-size: 30px;
  font-weight: bold;

  color: rgb(244, 191, 79);
 



}
.info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  margin: 0 auto;
  max-width: 1400px;
  margin-bottom:100px;
  
}
.info_tit {
  margin-bottom: 15px;
  /* font-weight: bold; */
  font-size: large;
  font-family: "IBMPlexSansKR-Bold";
}

.info_box {
  flex-direction: column;
  width: 30%;
  height: 170px;
  margin: 10px;
  padding: 20px;
  text-align: center;
  align-items: center;
  border: none;
  border-right: 0px;
  border-top: 0px;
  border-left: 0px;
  border-top: 2px solid;
  font-family: "IBMPlexSansKR-Medium";
}

.info_box2{
  width: 40%;
  height: 170px;
  margin: 10px;
  padding: 20px;
  border: none;
  border-right: 0px;
  border-top: 0px;
  border-left: 0px;
  border-top: 2px solid;
   
  font-family: "IBMPlexSansKR-Medium";
}

h3 {
  font-size: 20px;
  margin-bottom: 10px;
}

p {
  font-size: 16px;
  margin: 0;
}
.info_phones {
  display: flex;
}
.info_phone {
  width: 50%;
  display: inline;
  margin-bottom: 10px;
}

#map {
  width: 1400px;
  height: 400px;
  margin-top: 40px;
  margin: 0 auto;
  margin-bottom: 20px;
  z-index: -1;
}
img{
   z-index:-1;
}
.wrapper1 {
  display: block;
  font-family: "IBMPlexSansKR-Bold";
  color:rgba(255, 255, 255, 0.374);
  position:absolute;
  font-size:120px;
  width: 100%;
  top: 40px;
  text-align:left;
  left:50px;

  z-index:0;
  
}

.wrapper2{
   display: block;
  font-family: "IBMPlexSansKR-Bold";
  color:rgba(255, 255, 255, 0.374);
  position:absolute;
  font-size:60px;
  width: 100%;
  top: 160px;
  text-align:left;
  left:50px;

  z-index:0;
}
</style>