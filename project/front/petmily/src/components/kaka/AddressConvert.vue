<template>
    <div>
      <h2>주소를 입력하여 위도와 경도 얻기</h2>
      <div>
        <label>주소:</label>
        <input type="text" v-model="address" placeholder="주소를 입력하세요" />
        <button @click="getCoordinates">위도, 경도 얻기</button>
      </div>
      <div v-if="coordinates">
        <p>위도: {{ coordinates.latitude }}</p>
        <p>경도: {{ coordinates.longitude }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    name: "AddressGeocoding",
    data() {
      return {
        address: "",
        coordinates: null,
      };
    },
    methods: {
      getCoordinates() {
        if (this.address === "") {
          alert("주소를 입력하세요.");
          return;
        }
  
        const encodedAddress = encodeURIComponent(this.address);
        const apiUrl = `https://maps.googleapis.com/maps/api/geocode/json?address=${encodedAddress}&key=AIzaSyAEMcBVXcTsB5UmbNou29kkZkSPpq4mDJA`;
  
        axios
          .get(apiUrl)
          .then((response) => {
            const result = response.data.results[0];
            if (result) {
              const location = result.geometry.location;
              this.coordinates = {
                latitude: location.lat,
                longitude: location.lng,
              };
            } else {
              alert("주소를 찾을 수 없습니다.");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            alert("주소를 가져오는 도중 오류가 발생했습니다.");
          });
      },
    },
  };
  </script>  