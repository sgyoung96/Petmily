<template>
  <div class="v-title" @click="goHome" style="cursor: pointer;">
    <h4 style="text-align: center;"><strong><span style="color:rgb(156, 156, 39)">PETMILY</span>
        &nbsp;<span style="color:rgb(244, 191, 79);">VOLUNTEER</span></strong></h4>
  </div>
  <div class="v-all">
    <div style="display: flex; justify-content: space-between;">
      <div>
        <button @click="$router.push('/volboardhome')" style="font-size: 17px; text-decoration: none;">목록으로</button>
      </div>
      <div style="float:right">
        <button @click="apply" style="font-size: 17px; cursor: pointer;">신청하기</button>
<button @click="addwatch(dto.num)" style="font-size: 17px; cursor: pointer;">♡관심목록</button>
<button v-if="this.loginId==='admin'" @click="del(dto.num)" style="font-size: 17px; cursor: pointer;">삭제</button>
      </div>
    </div>
    <div class="vhead">
      &nbsp;
      <span class="badge text-bg-danger" style="font-size: 17px;"
        v-if="calculateDateDifference(dto.deadline).difference < 0">모집마감</span>
      <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
        calculateDateDifference(dto.deadline).days }}</span>
      &nbsp;
      <span style="font-size:20px"><strong>{{ dto.title }}</strong></span><span style=" font-size:small; float:right; padding-right: 20px; padding-top: 5px;">조회수:{{ dto.cnt }}</span>
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
          <td colspan="3">{{ dto.address }}&nbsp;<button v-on:click="scrollToMap()">지도참조</button>
          </td>
        </tr>
      </tbody>
    <tfoot v-if="this.loginId=='admin'">
      <tr><th colspan="4">⬇ 참가자 리스트 ⬇</th></tr>
      <tr><th>아이디</th><th>전화번호</th><th>이름</th><th>이메일</th></tr>
      <tr v-for="person in list2" :key="person.num">
        <td>{{ person.id.id }}</td><td>{{ person.id.phone }}</td><td>{{ person.id.name }}</td><td>{{ person.id.email }}</td>
      </tr>
    </tfoot>
    </table>
      <img clss="box-img" :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/1'">
      <img clss="box-img" :src="'http://localhost:8082/volboard/imgs/' + dto.num + '/2'">
      <div class="b-content" v-if="dto.content" v-html="convertNewlines(dto.content)"></div>
    <div>
      <input type="hidden" v-model="address" placeholder="주소를 입력하세요" />
    </div>
    <div class="d-card">
              <div style=" margin-bottom:10px;">
              <strong style="font-size:26px;">봉사장소</strong>
            </div>
            <div style="display:inline-block">
              <div class="a-card" style="width: 100%;">
                  <div class="e-card">
                  <img src="../../assets/images/점.png">&nbsp;<h3>보호센터주소</h3>
                </div>
                  <span>{{ dto.address }}</span>
              </div>
            <div style="display: flex;">
              </div>
            </div>
            <div id="map" ref="map"></div>
          </div>
  </div>
</template>

<style scoped>
button{
  width: 100px;
    color: white;
    height: 30px;
    border: 1px solid rgb(244, 191, 79);
    border-radius: 20px;
    background-color: rgb(244, 191, 79);
    font-family: 'IBMPlexSansKR-Bold';
    font-size: 15px;
    padding-top: 2px;
    margin-right: 5px;
}
button:hover {
  background-color: rgb(235, 156, 39);
  cursor: pointer;
}
.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}

.v-all {
  padding-left: 200px;
  padding-right: 200px;
  padding-bottom: 150px;
}

#map {
  width: 100%;
  height: 400px;
}

.vhead {
  text-align: left;
  background-color: #f8f9fa;
  border-top: 2px solid black;
  border-bottom: 1px solid black;
  padding-top: 10px;
  padding-bottom: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
}

.table th {
  background-color: #f8f9fa;
}

.tbody {
  border-top: 2px solid black;
  text-align: left;
}

img {
  width: 530px;
  height: 310px;
}
.b-content{
padding:50px;
text-align: left;
}

table {
  width: 100%;
}


.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}
.d-card{
  border: solid #e5e7eb;
  border-radius: 20px;
  margin-bottom: 50px;
  position: relative;
  text-align: left;
  background-color:white;
  padding:24px;
  display:flex;
  flex-direction: column;
  display: flex;
}
.a-card{
  margin-bottom:10px;
  float: left;
  width: 80%;
}
.e-card{
  display: flex;
  flex-direction: row;
}
.d-card img{
  width:20px;
  height:20px;
}
.d-card h3{
  font-size:16px;
  margin-bottom:0px;
  font-weight: bold;
}

.d-card span{
  margin-left:25px;
  color:grey;
  font-size:15px;
  }
.box-img {
  padding: 20px;
  width: 45%;
  height: 400px;
  margin-top: 20px;
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
convertNewlines(text) {
    return text.replace(/\n/g, '<br>');
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
  del: function(num) {
  const self = this;
  self.$axios.delete('http://localhost:8082/volboard/' + num)
    .then(function (res) {
      if(res.status == 200){
        if(res.data.flag){
          alert('삭제가 정상적으로 되었습니다');
          self.$router.push({ name: 'VolBoardHome' });
        }else{
          alert('삭제 처리 에러');
        }
      }else{
        alert('에러코드: '+ res.status);
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
              "https://cdn-icons-png.flaticon.com/512/9922/9922172.png", // 강아지 모양 이미지 URL로 변경
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