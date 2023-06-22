<template>
  <div id="app">
    <h3>{{ dto.title }} 봉사모집게시판</h3>
    <table border="1">
      <tr>
        <td><img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/1'"></td>
        <td><img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/2'"></td>
      </tr>
    </table>
    <table border="1">
      <tr>
        <th>num</th>
        <td><input type="text" v-model="dto.num" readonly></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><input type="text" v-model="dto.title" readonly></td>
      </tr>
      <tr>
        <th>봉사장소</th>
        <td><input type="text" v-model="dto.address" readonly></td>
      </tr>
      <tr>
        <th>봉사실시일자</th>
        <td><input type="text" v-model="dto.vol_date" readonly></td>
      </tr>
      <tr>
        <th>봉사인원</th>
        <td><input type="text" v-model="dto.vol_number" readonly></td>
      </tr>
    </table>
    <button type="button" v-on:click="apply()">봉사신청</button>
    <button type="button" v-on:click="list(dto.num)">봉사참여자목록</button>
    {{ count }} / {{ dto.vol_number }} {{ address }}
    <div>
        <label>주소:</label>
        <input type="text" v-model="address" placeholder="주소를 입력하세요" />
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
import axios from "axios";

export default {
  name: 'VolBoardDetail',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      num: this.$route.query.num,
      dto: {},
      count: 0,
      address: this.$route.query.address,
      map: null,
      marker: null
    }
  },
  mounted() {
      if (window.kakao && window.kakao.maps) {
        this.loadMap();
      } else {
        this.loadScript();
      }
  },
  methods: {
    detail(num) {
      this.$router.push({ name: 'VolBoardDetail', params: { num: num } })
    },
    apply() {
      const self = this
      if(self.dto.vol_number <= self.count){
        alert("봉사신청인원이 초과하였습니다.")
      }else{
      self.$axios.get('http://localhost:8082/participants/' + self.loginId + '/' + self.num)
        .then(function (res) {
          if (res.status == 200) {
            alert(res.data.list)
            if (res.data.list.length === 0) {
              let formData = new FormData();
              formData.append('boardnum', self.num)
              formData.append('id', self.loginId)
              self.$axios.post('http://localhost:8082/participants', formData)
                .then(function (res) {
                  if (res.status == 200) {
                    alert(res.data.member.id + "님이 신청되셨습니다.")
                  } else {
                    alert("에러코드:" + res.status)
                  }
                })
            } else {
              alert('이미 신청되었습니다.')
              
              // let formData2 = new FormData();
              // formData2.append('boardnum', self.num)
              // formData2.append('id', self.loginId)
              
              self.$axios.delete('http://localhost:8082/participants?boardnum=' + self.num + '&id=' + self.loginId)
                .then(function (res) {
                  if (res.status == 200) {
                    alert("신청이 취소되었습니다.");
                  } else {
                    alert("신청 취소 처리 에러");
                  }
                });
            }
          }
        })}
    },
    list(num) {
      this.$router.push({ name: 'ParticipantsList', params: { num: num } })
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
        if (this.address === "") {
          alert("주소를 입력하세요.");
          return;
        }
        alert(this.address)
        const encodedAddress = encodeURIComponent(this.address);
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
  created: function () {//이 컴포넌트가 시작될때 실행되는 함수
    alert(this.num)
    alert(this.address+"***********************************")
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/volboard/' + self.num)
      .then(function (res) {
        if (res.status == 200) {
          self.dto = res.data.dto
          self.address = res.data.dto.address
        } else {
          alert('에러')
        }
      })

    self.$axios.get('http://localhost:8082/participants/boardnum/' + self.num)
      .then(function (res) {
        if (res.status == 200) {
          self.count = res.data.count
        } else {
          alert('에러')
        }
      })
  }
}
</script>
<style scoped>
img {
  width: 100px;
  height: 100px;
}

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}</style>