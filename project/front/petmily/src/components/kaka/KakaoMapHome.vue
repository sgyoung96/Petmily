<template>
    <div>
      <input type="text" v-model="address" placeholder="주소를 입력하세요" />
      <button @click="searchAddress">주소 검색</button>
      <div id="map" style="width: 100%; height: 400px;"></div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        address: '',
        map: null,
        marker: null
      };
    },
    methods: {
      searchAddress() {
        // 주소 검색 버튼 클릭 시 호출되는 메소드
        if (!this.address) {
          alert('주소를 입력해주세요.');
          return;
        }
  
        // 카카오맵 API로 주소를 검색
        const geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(this.address, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            // 검색 결과가 있을 경우 첫 번째 결과의 좌표를 가져옴
            const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
  
            // 기존 마커 제거
            if (this.marker) {
              this.marker.setMap(null);
            }
  
            // 새로운 마커 생성
            this.marker = new kakao.maps.Marker({
              position: coords
            });
  
            // 지도 중심을 검색된 위치로 이동
            this.map.setCenter(coords);
            // 마커를 지도에 표시
            this.marker.setMap(this.map);
          } else {
            alert('주소 검색에 실패했습니다.');
          }
        });
      },
      initMap() {
        // 카카오맵 초기화 및 기본 설정
        const container = document.getElementById('map');
        const options = {
          center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 시청을 기준으로 지도 표시
          level: 8
        };
        this.map = new kakao.maps.Map(container, options);
      }
    },
    mounted() {
      // 컴포넌트가 마운트될 때 실행되는 라이프사이클 훅
      this.initMap();
    }
  };
  </script>
  
  <style>
  #map {
    width: 100%;
    height: 400px;
  }
  </style>
  