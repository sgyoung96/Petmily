<template>
  <div>
    <h2>카카오 맵 보기</h2>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  mounted() {
    this.loadKakaoMapsScript();
  },
  methods: {
    loadKakaoMapsScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=238a6ec85a58f614c0abe27a99a5b4e1&libraries=services";
      script.onload = this.initializeMap;

      document.head.appendChild(script);
    },
    initializeMap() {
      var mapContainer = document.getElementById("map");
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      var geocoder = new kakao.maps.services.Geocoder();

      geocoder.addressSearch("제주특별자치도 제주시 첨단로 242", function(
        result,
        status
      ) {
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>',
          });
          infowindow.open(map, marker);

          map.setCenter(coords);
        }
      });
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 400px;
}
</style>