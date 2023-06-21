<template>
  <div>
    <h2>카카오 맵 보기</h2>
    <input type="text" v-model="address" placeholder="주소를 입력하세요" />
    <button @click="searchAddress">검색</button>
    <div id="map"></div>
  </div>
</template>

<style scoped>
#map {
  width: 100%;
  height: 400px;
}
</style>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      address: "", // 사용자가 입력한 주소를 저장할 데이터 속성
      marker: null, // 마커 객체를 저장할 데이터 속성
    };
  },
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
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=238a6ec85a58f614c0abe27a99a5b4e1&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
      };

      this.map = new window.kakao.maps.Map(container, options);
    },
    searchAddress() {
      const geocoder = new window.kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch(this.address, (result, status) => {
        if (status === window.kakao.maps.services.Status.OK) {
          const coords = new window.kakao.maps.LatLng(
            result[0].y,
            result[0].x
          );

          if (this.marker) {
            this.marker.setMap(null); // 이전에 생성한 마커를 삭제합니다
          }

          // 검색 결과로 받은 위치에 마커를 표시합니다
          this.marker = new window.kakao.maps.Marker({
            position: coords
          });

          this.marker.setMap(this.map);

          // 지도의 중심을 검색된 위치로 이동시킵니다
          this.map.setCenter(coords);
        } else {
          alert("주소를 검색할 수 없습니다.");
        }
      });
    },
  },
};
</script>