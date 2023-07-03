<template>
  <div>
    <h2>위도, 경도로 위치 보기</h2>
    <div>
      <label>위도:</label>
      <input type="text" v-model="latitude" placeholder="위도를 입력하세요" />
      <label>경도:</label>
      <input type="text" v-model="longitude" placeholder="경도를 입력하세요" />
      <button @click="showLocation">위치 보기</button>
    </div>
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
  name: "LocationToAddress",
  data() {
    return {
      latitude: ["37.541", "37.141", "37.241", "37.341"],
      longitude: ["126.986", "126.886", "126.786", "126.686"],
      map: null,
      markers: []
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
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=AIzaSyAEMcBVXcTsB5UmbNou29kkZkSPpq4mDJA&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap.bind(this));

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(
          parseFloat(this.latitude[0]), // 첫 번째 위도
          parseFloat(this.longitude[0]) // 첫 번째 경도
        ),
        level: 3
      };

      this.map = new window.kakao.maps.Map(container, options);

      // 각 좌표에 마커 추가
      for (let i = 0; i < this.latitude.length; i++) {
        const lat = parseFloat(this.latitude[i]);
        const lng = parseFloat(this.longitude[i]);

        if (isNaN(lat) || isNaN(lng)) {
          continue;
        }

        const position = new window.kakao.maps.LatLng(lat, lng);

        // Custom marker image
        const markerImage = new window.kakao.maps.MarkerImage(
          "https://media.istockphoto.com/id/1268251949/ko/%EB%B2%A1%ED%84%B0/%ED%94%8C%EB%9E%AB-%EC%95%A0%EC%99%84-%EB%8F%99%EB%AC%BC-gps-%EB%A1%9C%EA%B3%A0-%EB%94%94%EC%9E%90%EC%9D%B8-%EA%B0%9C-%EB%A7%B5-%EB%A7%88%EC%BB%A4-%EB%B2%A1%ED%84%B0-%EB%8F%99%EB%AC%BC-%EC%82%B0%EC%B1%85%EC%9D%80-%EC%9C%84%EC%B9%98-%EC%9C%84%EC%B9%98%EC%97%90-%EC%A3%BC%EC%9D%98%ED%95%A9%EB%8B%88%EB%8B%A4-%EC%95%A0%EC%99%84-%EB%8F%99%EB%AC%BC-%EC%9B%B9-%EC%9D%91%EC%9A%A9-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%EC%97%90-%EB%8C%80%ED%95%9C-%EB%84%A4%EB%B9%84%EA%B2%8C%EC%9D%B4%EC%85%98-%EA%B8%B0%ED%98%B8-%EA%B7%80%EC%97%AC%EC%9A%B4-%ED%96%89%EB%B3%B5-%EA%B0%95%EC%95%84%EC%A7%80.jpg?s=612x612&w=0&k=20&c=hBf2iFyW03qcuBKd_WoD8g96ZA6sk1dwHk3SMuZoIQg=",
          new window.kakao.maps.Size(50, 50)
        );

        const marker = new window.kakao.maps.Marker({
          position: position,
          image: markerImage // Custom marker image 설정
        });

        marker.setMap(this.map);

        // 마커를 markers 배열에 저장
        this.markers.push(marker);
      }

      // 마커를 모두 보여줌
      this.showAllMarkers();
    },
    showAllMarkers() {
      const bounds = new window.kakao.maps.LatLngBounds();

      // 모든 마커를 포함하는 범위 계산
      for (const marker of this.markers) {
        bounds.extend(marker.getPosition());
      }

      // 지도의 중심과 레벨을 마커를 모두 포함하는 범위로 설정
      this.map.setBounds(bounds);
    }
  }
};
</script>