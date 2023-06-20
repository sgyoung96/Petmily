<template>
  <div>
    <h2>카카오 맵 보기</h2>
    <div id="map"></div>
  </div>
</template>
<style scoped>
#map {
  width:100%;
  height:400px;
}
</style>
<script>
export default {
  name:"KakaoMap", // 컴포넌트 이름 지정
  data() {
    return {
      // map 객체 설정
      map:null,
    };
  },
  setup() {},
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  unmounted() {},
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=90a9e14a8d8d8c4e2a9a92bd4ca90bbb&autoload=false"; 
      script.onload = () => window.kakao.maps.load(this.loadMap); 

      document.head.appendChild(script);
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map"); 
      const options = {
        center: new window.kakao.maps.LatLng(37.541,126.986), 
        level: 3
      };

      this.map = new window.kakao.maps.Map(container,options); 
      this.loadMaker();
    },
    // 지정한 위치에 마커 불러오기
    loadMaker() {
      const markerPosition = new window.kakao.maps.services.Geocoder();
      this.Geocoder.addressSearch('제주특별자치도 제주시 첨단로 242')
      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
  },
};
</script>