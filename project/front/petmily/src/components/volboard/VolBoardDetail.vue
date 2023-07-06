<template>
  <div class="v-title" @click="goHome" style="cursor: pointer;">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">VOLUNTEER</span></strong></h4>
  </div>
  <div class="v-all">
    <div style="display: flex; justify-content: space-between;">
      <div>
        <router-link to="/volboardhome" class="badge text-bg-secondary"
          style="font-size: 17px; text-decoration: none;">목록으로</router-link>
      </div>
      <div style="float:right">
        <span v-if="!isApplied" v-on:click="apply" class="badge text-bg-danger" style="font-size: 17px; cursor: pointer;">신청하기</span>&nbsp;
         <span v-else v-on:click="cancelApply" class="badge text-bg-danger" style="font-size: 17px; cursor: pointer;">신청취소</span>&nbsp;
        <span v-on:click="addwatch(dto.num)" class="badge text-bg-secondary" style="font-size: 17px; cursor: pointer;">♡관심목록담기</span>&nbsp;
        <span v-if="this.loginId==='admin'" v-on:click="del(dto.num)" class="badge text-bg-danger" style="font-size: 17px; cursor: pointer;">삭제</span>
      </div>
    </div>
    <div class="vhead">
      &nbsp;
      <span class="badge text-bg-danger" style="font-size: 17px;"
        v-if="calculateDateDifference(dto.deadline).difference < 0">모집마감</span>
      <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
        calculateDateDifference(dto.deadline).days }}</span>
      &nbsp;
      <span style="font-size:20px">{{ dto.title }}</span><span style="float:right; padding-right: 10px; padding-top: 5px;">조회수:{{ dto.cnt }}</span>
    </div>
    <table class="table table-bordered" style="margin-bottom:50px">
      <tbody div class="tbody">
        <tr>
          <th>봉사날짜</th>
          <td>{{ formatDateTime(volunteerDate) }}</td>
          <th>모집기간</th>
          <td>{{ formatDate(dto.deadline) }}까지</td>
        </tr>
        <tr>
          <th>모집인원</th>
          <td>{{ dto.vol_number }}명</td>
          <th>신청인원</th>
          <td>{{ dto.count }}명</td>
        </tr>
        <tr>
          <th>모집기관</th>
          <td colspan="3">{{ dto.place }}</td>
        </tr>
        <tr>
          <th>봉사장소</th>
          <td colspan="3">{{ dto.address }}<br />
            <button v-on:click="scrollToMap()">지도참조</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div style="border:1px solid black">
      <img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/1'">
      <img :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/2'">
    </div>
    <div>
      <input type="hidden" v-model="address" placeholder="주소를 입력하세요" />
    </div>
    <div id="map" ref="map"></div>
    <div class="form-container">

<div class="list-container" v-if="this.loginId=='admin'">
  <p><label class="section01">SECTION 1</label></p>
  <p><label class="section01-title"><u><span>봉사신청명단영역</span></u></label></p>
  <div class="section01-content">
    <div class="data-header">
      <table>
        <tr aria-colspan="9">
          <th colspan="1">
            <label><span>No.</span></label>
          </th>
          <th colspan="2">
            <label><span>참가자PROFILE</span></label>
          </th>
          <th colspan="1">
            <label><span>참가자ID</span></label>
          </th>
          <th colspan="1">
            <label><span>참가자NAME</span></label>
          </th>
          <th colspan="1">
            <label><span>참가자TEL</span></label>
          </th>
          <th colspan="1">
            <label><span>참가자EMAIL</span></label>
          </th>
          <th colspan="2">
            <label><span>참가자ADDRESS</span></label>
          </th>
          <th colspan="2">
            <label><span>참가자BIRTH</span></label>
          </th>
        </tr>
        <tr v-for="person in list2" :key="person.num">
          <td colspan="1"><label><span>1</span></label></td>
          <td colspan="2"><label><span>{{ person.id.id }}</span></label></td>
          <td colspan="1"><label><span>{{ person.id.id }}</span></label></td>
          <td colspan="1"><label><span>{{ person.id.name }}</span></label></td>
          <td colspan="1"><label><span>{{ person.id.phone }}</span></label></td>
          <td colspan="1"><label><span>{{ person.id.email }}</span></label></td>
          <td colspan="2"><label><span>{{ person.id.address }}</span></label></td>
          <td colspan="2"><label><span>{{ formatDate(person.id.birth) }}</span></label></td>
        </tr>
      </table>
    </div>
  </div>
</div>
</div>
</div>
</template>

<style scoped>
.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}

.v-all {
  padding-left: 150px;
  padding-right: 150px;
  padding-bottom: 150px;
}

#map {
  width: 100%;
  height: 400px;
}

.vhead {
  text-align: left;
  background-color: rgb(233, 230, 230);
  border-top: 2px solid black;
  border-bottom: 1px solid black;
  padding-top: 10px;
  padding-bottom: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
}

.table th {
  background-color: #bab9b9;
}

.tbody {
  border-top: 2px solid black;
  text-align: left;
}

img {
  width: 530px;
  height: 310px;
}

.form-container {
  display: block;
  position: relative;
}

.list-container {
  display: block;
  position: relative;
  margin-left: 50px;
  margin-right: 50px;
  padding-top: 40px;
  padding-bottom: 100px;
  border: 5px solid #efefef;
  border-radius: 10px;
  height: auto;
}

.section01 {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 15px;
  text-align: center;
  vertical-align: center;
  width: 120px;
  height: 25px;
  background: rgb(244, 191, 79);
  color: white;
  border-radius: 10px;
}

.section01-content {
  border: 1px solid #efefef;
  border-radius: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.section01-title u span {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
  color: black;
}

.section01-title u {
  text-decoration-color: rgb(244, 191, 79);
  text-decoration-thickness: 3px;
}

.data-header {
  display: flex;
  justify-content: space-between;
  padding-left: 80px;
  padding-right: 80px;
}

table {
  width: 100%;
}

.data-header table tr th {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 14px;
  color: black;
  text-align: center;
}

.data-header table tr td {
  font-family: 'IBMPlexSansKR-Regular';
  font-size: 14px;
  color: black;
  text-align: center;
}

.data-contents {
  display: flex;
  justify-content: space-between;
  padding-left: 60px;
  padding-right: 80px;
}

.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}

.vbody {
  border-bottom: 1px solid #666666;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  cursor: pointer;
}

.vcount {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 70px;
  height: 70px;
  border: 2px solid #666666;
  font-size: 15px;
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
}

.vtitle {
  font-size: 30px;
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
      marker: null,
      list2: [],
      state: '',
      sysdate: new Date(),
      volunteerDate: '2023-07-11T15:00:00.000+00:00'
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }

    // 페이지 맨 위로 스크롤하기
    window.scrollTo({ top: 0, behavior: 'auto' });
  },
  methods: {
    formatDateTime(dateTime) {
  const dateObj = new Date(dateTime);
  const year = dateObj.getFullYear();
  const month = ("0" + (dateObj.getMonth() + 1)).slice(-2);
  const day = ("0" + dateObj.getDate()).slice(-2);

  return `${year}년 ${month}월 ${day}일`;
},

    goHome() {
      location.href = '/volboardhome';
    }, 
    addwatch(num) {
  let id = this.loginId;
  let formData = new FormData();
  formData.append('id', id);
  formData.append('num', num);
  axios.get('http://localhost:8082/watchlist/' + id + '/' + num)
    .then((res) => {
      if (res.status == 200) {
        if (res.data.flag) {
          axios.post('http://localhost:8082/watchlist', formData)
            .then((res) => {
              if (res.status == 200) {
                alert('관심목록에 추가되었습니다.');
              }
            });
        } else {
          axios.delete('http://localhost:8082/watchlist', {data: formData})
            .then((response) => {
              if (response.status === 200) {
                alert('관심목록에서 제거되었습니다.');
              }
            })
            .catch(error => {
              console.error(error);
              alert('삭제 요청 중에 오류가 발생했습니다.');
            });
        }
      }
    });
},
apply() {
const self = this;
    if (self.dto.vol_number <= self.count) {
      alert("봉사신청인원이 초과하였습니다.");
    } else {
      self.$axios.get('http://localhost:8082/participants/' + self.loginId + '/' + self.num)
        .then(function(res) {
          if (res.status == 200) {
            if (res.data.list.length === 0) {
              let formData = new FormData();
              formData.append('boardnum', self.num);
              formData.append('id', self.loginId);
              self.$axios.post('http://localhost:8082/participants', formData)
                .then(function(res) {
                  if (res.status == 200) {
                    alert(res.data.member.id + "님이 신청되셨습니다.");
                    // 신청 성공 시 아이콘 변경
                    self.isApplied = true;
                  } else {
                    alert("에러코드:" + res.status);
                  }
                });
            } else {
              alert('이미 신청되었습니다.');
              self.$axios.delete('http://localhost:8082/participants?boardnum=' + self.num + '&id=' + self.loginId)
                .then(function(res) {
                  if (res.status == 200) {
                    alert("신청이 취소되었습니다.");
                    // 신청 취소 성공 시 아이콘 변경
                    self.isApplied = false;
                  } else {
                    alert("신청 취소 처리 에러");
                  }
                });
            }
          }
        });
    }
  },
    del(num) {
      const self = this
      self.$axios.delete('http://localhost:8082/volboard/' + num)
      .then(function (res) {
        if(res.status == 200){
          if(res.data.flag){
            alert('삭제가 정상적으로 되었습니다')
            this.$router.push({ name: 'VolBoardHome' });
          }else{
            alert('삭제 처리 에러')
          }
        }else{
          alert('에러코드: '+ res.status)
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
      if (this.address === "") {
        alert("주소를 입력하세요.");
        return;
      }
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
    },
    calculateDateDifference(deadline) {
      const deadlineDate = new Date(deadline)
      const difference = deadlineDate.getTime() - this.sysdate.getTime()
      const days = Math.ceil(difference / (1000 * 60 * 60 * 24))
      return { difference, days }
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      const formattedDate = new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
      const year = formattedDate.slice(0, 4);
      const month = formattedDate.slice(4, 6);
      const day = formattedDate.slice(6, 8);

  return `${year}년 ${month}월 ${day}일`;
    },
    scrollToMap() {
      this.$refs.map.scrollIntoView({ behavior: 'smooth' });
    },
  },
  created: function () {//이 컴포넌트가 시작될때 실행되는 함수
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

    self.$axios.get('http://localhost:8082/participants/' + self.num)
      .then(function (res) {
        if (res.status == 200) {
          self.list2 = res.data.list
        } else {
          alert('에러')
        }
      })
  }
}
</script>