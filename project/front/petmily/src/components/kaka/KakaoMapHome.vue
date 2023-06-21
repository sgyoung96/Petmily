<template>
    <div>
      <h2>카카오 맵 보기</h2>
      <div id="map"></div>
      <div>
        <label>위도:</label>
        <input type="text" v-model="latitude" placeholder="위도를 입력하세요" />
        <label>경도:</label>
        <input type="text" v-model="longitude" placeholder="경도를 입력하세요" />
        <button @click="showLocation">위치 보기</button>
      </div>
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
        latitude: "",
        longitude: "",
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
          center: new window.kakao.maps.LatLng(37.541, 126.986),
          level: 3,
        };
  
        this.map = new window.kakao.maps.Map(container, options);
      },
      showLocation() {
        const latitude = parseFloat(this.latitude);
        const longitude = parseFloat(this.longitude);
  
        if (isNaN(latitude) || isNaN(longitude)) {
          alert("유효한 위도와 경도를 입력하세요.");
          return;
        }
  
        const position = new window.kakao.maps.LatLng(latitude, longitude);
  
        this.map.setCenter(position);
        this.map.setLevel(3);
  
        const marker = new window.kakao.maps.Marker({
          position: position,
        });
  
        marker.setMap(this.map);
      },
    },
  };
  </script>  