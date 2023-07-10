<template>
  <img id="fadeout_text" class="head-text fade-out" src="../assets/images/문구_01.png" />
  <div id="fadeout_bg" class="bg_fadeout"></div>
  <div class="home">
    <div id="container2" class="container2">
      <img class="picture left" src="../assets/images/banner_top_cat_02.jpg" @click="all_cats()">
      <img class="picture right" src="../assets/images/banner_top_dog_01.jpg" @click="all_dogs()">
    </div>

    <div id="app">
      <!-- ... Existing code ... -->

      <!-- <div class="form-group">
        <input type="hidden" class="form-control" v-model="videoUrl">
      </div> -->

      <div id="top_02" class="top_02">
        <p>당신만을 바라보는 반려견 유기 횟수 10만 마리</p>
      </div>

      <!-- Display YouTube video -->
      <!-- Display YouTube video -->
      <div class="form-group">
        <div v-if="isValidVideoUrl">
          <iframe :src="embeddedVideoUrl" width="80%" height="800px" frameborder="0" allowfullscreen></iframe>
        </div>
      </div>
    </div>





    <div class="container text-center">
      <div class="row">
        <div class="col">
          <canvas id="PieChart" width="1" height="1"></canvas>
          {{ formatDate(this.sysdate) }}
        </div>
        <div class="col">
          <canvas id="BarChart" width="50px" height="50px"></canvas>
          <div>{{ t1 }}</div>
          <div>{{ t2 }}</div>
          <div>{{ t3 }}</div>
        </div>
      </div>
    </div>

    
    <div class="container">
    <div style="display:flex">
      <div v-for="dboard in arr" :key="dboard.num">
        <div class="img-box" v-on:click="$event => detail(dboard.num)">
          <a><img class="b-img" :src="'http://localhost:8082/dboard/imgs/' + dboard.num + '/1'"></a>
          <div class="b-txt">
            <div class="b-title">
              {{ dboard.title }}
            </div>
            <div class="b-id">
              <span>
                작성자: {{ dboard.id.id }}
              </span>
              <span>
                <img class="l-img" src="../assets/images/heart.png" style="width: 15px; height: 15px;">{{ dboard.likecnt
                }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="display:flex">
      <div v-for="dboard in arr2" :key="dboard.num">
        <div class="img-box" v-on:click="$event => detail2(dboard.num)">
          <a><img class="b-img" :src="'http://localhost:8082/adopt/imgs/' + dboard.num + '/1'"></a>
          <div class="b-txt">
            <div class="b-title">
              {{ dboard.title }}
            </div>
            <div class="b-id">
              <span>
                작성자: {{ dboard.id.id }}
              </span>
              <span>
                <img class="l-img" src="../assets/images/heart.png" style="width: 15px; height: 15px;">{{ dboard.likecnt
                }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container text-center">
      <div class="row">
        <div class="col">
          <canvas id="PieChart" width="1" height="1"></canvas>
          {{ formatDate(this.sysdate) }}
        </div>
        <div class="col">
          <canvas id="BarChart" width="50px" height="50px"></canvas>
          <div>{{ t1 }}</div>
          <div>{{ t2 }}</div>
          <div>{{ t3 }}</div>
        </div>
      </div>
    </div>

    
    <div class="container text-center">

  <div class="row">
    <div class="col-7">
      <table class="table" v-if="witems.length">
  <thead>
    <tr>
      <th scope="col">#{{ this.date }}일자 산책</th>
      <th scope="col" v-if="witems.length">{{ witems[0].fcstTime }}시</th>
      <th scope="col" v-if="witems.length">{{ witems[12].fcstTime }}시</th>
      <th scope="col" v-if="witems.length">{{ witems[24].fcstTime }}시</th>
      <th scope="col" v-if="witems.length">{{ witems[36].fcstTime }}시</th>
      <th scope="col" v-if="witems.length">{{ witems[48].fcstTime }}시</th>
    </tr>
  </thead>
  <tbody v-if="witems.length">
    <tr>
      <th scope="row">기온</th>
      <td>{{ witems[0].fcstValue }}℃</td>
      <td>{{ witems[12].fcstValue }}℃</td>
      <td>{{ witems[24].fcstValue }}℃</td>
      <td>{{ witems[36].fcstValue }}℃</td>
      <td>{{ witems[48].fcstValue }}℃</td>
    </tr>
    <tr>
      <th scope="row">날씨</th>
      <td v-if="witems.length">
        <span v-if="witems[9].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="witems[5].fcstValue === '1'">🌞</span>
          <span v-else-if="witems[5].fcstValue === '3'">⛅</span>
          <span v-else-if="witems[5].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="witems.length">
        <span v-if="witems[21].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="witems[17].fcstValue === '1'">🌞</span>
          <span v-else-if="witems[17].fcstValue === '3'">⛅</span>
          <span v-else-if="witems[17].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="witems.length">
        <span v-if="witems[33].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="witems[29].fcstValue === '1'">🌞</span>
          <span v-else-if="witems[29].fcstValue === '3'">⛅</span>
          <span v-else-if="witems[29].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="witems.length">
        <span v-if="witems[45].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="witems[41].fcstValue === '1'">🌞</span>
          <span v-else-if="witems[41].fcstValue === '3'">⛅</span>
          <span v-else-if="witems[41].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="witems.length">
        <span v-if="witems[45].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="witems[41].fcstValue === '1'">🌞</span>
          <span v-else-if="witems[41].fcstValue === '3'">⛅</span>
          <span v-else-if="witems[41].fcstValue === '4'">☁</span>
        </span>
      </td>
    </tr>
    <tr>
      <th scope="row">강수량</th>
      <td v-if="witems.length">{{ witems[9].fcstValue }}</td>
      <td v-if="witems.length">{{ witems[21].fcstValue }}</td>
      <td v-if="witems.length">{{ witems[33].fcstValue }}</td>
      <td v-if="witems.length">{{ witems[45].fcstValue }}</td>
      <td v-if="witems.length">{{ witems[45].fcstValue }}</td>
    </tr>
    <tr>
      <th scope="row">갱얼쥐 산책</th>
      <td>
        <span v-if="witems[0].fcstValue < '18'">양호🐾</span>
        <span v-else-if="witems[0].fcstValue >= '18' && witems[0].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="witems[0].fcstValue >= '26' && witems[0].fcstValue < '29'">주의🚨</span>
        <span v-else-if="witems[0].fcstValue >= '29' && witems[0].fcstValue < '32'">위험🔥</span>
        <span v-else-if="witems[0].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="witems[12].fcstValue < '18'">양호🐾</span>
        <span v-else-if="witems[12].fcstValue >= '18' && witems[12].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="witems[12].fcstValue >= '26' && witems[12].fcstValue < '29'">주의🚨</span>
        <span v-else-if="witems[12].fcstValue >= '29' && witems[12].fcstValue < '32'">위험🔥</span>
        <span v-else-if="witems[12].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="witems[24].fcstValue < '18'">양호🐾</span>
        <span v-else-if="witems[24].fcstValue >= '18' && witems[48].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="witems[24].fcstValue >= '26' && witems[24].fcstValue < '29'">주의🚨</span>
        <span v-else-if="witems[24].fcstValue >= '29' && witems[24].fcstValue < '32'">위험🔥</span>
        <span v-else-if="witems[24].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="witems[36].fcstValue < '18'">양호🐾</span>
        <span v-else-if="witems[36].fcstValue >= '18' && witems[36].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="witems[36].fcstValue >= '26' && witems[36].fcstValue < '29'">주의🚨</span>
        <span v-else-if="witems[36].fcstValue >= '29' && witems[36].fcstValue < '32'">위험🔥</span>
        <span v-else-if="witems[36].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="witems[48].fcstValue < '18'">양호🐾</span>
        <span v-else-if="witems[48].fcstValue >= '18' && witems[48].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="witems[48].fcstValue >= '26' && witems[48].fcstValue < '29'">주의🚨</span>
        <span v-else-if="witems[48].fcstValue >= '29' && witems[48].fcstValue < '32'">위험🔥</span>
        <span v-else-if="witems[48].fcstValue >= '32'">매우위험🚷</span>
      </td>
    </tr>
  </tbody>
  <tbody v-else>
    <tr>
      <td colspan="6">데이터가 없습니다.</td>
    </tr>
  </tbody>
</table>
    </div>
    <div class="col-5">
      <table class="table">
  <thead>
    <tr>
      <th scope="row">#</th>
      <th scope="col">여름철산책정도</th>
      <th scope="col">기온</th>
    </tr>
  </thead>
  <tbody>
    <tr class="table-info">
      <th scope="row">1</th>
      <th>양호🐾</th>
      <th>18℃미만</th>
    </tr>
    <tr class="table-primary">
      <th scope="row">2</th>
      <th>약간주의💦</th>
      <th>18℃이상 26℃미만</th>
    </tr>
    <tr class="table-warning">
      <th scope="row">3</th>
      <th>주의🚨</th>
      <th>26℃이상 29℃미만</th>
    </tr>
    <tr class="table-danger">
      <th scope="row">4</th>
      <th>위험🔥</th>
      <th>29℃이상 32℃미만</th>
    </tr>
    <tr class="table-secondary">
      <th scope="row">5</th>
      <th>매우위험🚷</th>
      <th>32℃이상</th>
    </tr>
  </tbody>
</table>
    </div>
  </div>
</div>

      <div>
        <img src="../assets/images/dboardpic2.jpg" style="width: 40%; height: 200px; margin-bottom: 20px;">
      </div>
      <div>
        <img src="../assets/images/dboardpic2.jpg" style="width: 40%; height: 200px; margin-bottom: 20px;">
      </div>
      <div>
        <img src="../assets/images/dboardpic2.jpg" style="width: 40%; height: 200px; margin-bottom: 20px;">
      </div>
      <div>
        <img src="../assets/images/dboardpic2.jpg" style="width: 40%; height: 200px; margin-bottom: 20px;">
      </div>
</div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import axios from 'axios';
export default {
  name: 'HomeView',
  data() {
    const sysdate = new Date();
    const D1 = new Date(sysdate);
    const D2 = new Date(sysdate);
    const D3 = new Date(sysdate);
    const D4 = new Date(sysdate);
    const D5 = new Date(sysdate);
    D1.setDate(D1.getDate() - 1);
    D2.setDate(D2.getDate() - 2);
    D3.setDate(D3.getDate() - 3);
    D4.setDate(D4.getDate() - 4);
    D5.setDate(D5.getDate() - 5);
    return {
      loginId: null,
      list: [],
      items: [],
      totalItems: 0, // 전체 항목 수
      sysdate: sysdate,
      D1: D1,
      D2: D2,
      D3: D3,
      D4: D4,
      D5: D5,
      videoUrl: 'https://www.youtube.com/watch?v=3AV35NdBZOI',
      arr: [],
      arr2: [],
      witems: [],
      datetime: new Date(),
      date: this.formatDate(new Date()),
      time: ''
    };
  },
  computed: {
    embeddedVideoUrl() {
      // Extract video ID from the URL
      const videoId = this.extractVideoId(this.videoUrl);
      // Create the embedded video URL
      return `https://www.youtube.com/embed/${videoId}`;
    },
    isValidVideoUrl() {
      // Validate the YouTube video URL
      return this.extractVideoId(this.videoUrl) !== null;
    }
  },
  created: function () {
    this.fadeoutAnim();
    this.showHeadText02();
    this.loginId = sessionStorage.getItem('loginId')
    const self = this;
    self.$axios.get('http://localhost:8082/dboard/ol')//+self.loginId
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list
          let list = res.data.list
          if (list.length < 4) {
            for (let i = 0; i < list.length; i++) {
              self.arr[i] = list[i]
            }
          } else {
            for (let i = 0; i < 4; i++) {
              self.arr[i] = list[i]
            }
          }
        } else {
          alert('에러코드' + res.status)
        }
      });

    if (8 <= this.datetime.getHours() && this.datetime.getHours() <= 14) {
      this.time = '0800';
    } else if (14 <= this.datetime.getHours() && this.datetime.getHours() < 20) {
      this.time = '1400';
    } else {
      this.time = '2000';
    }

    this.fetchData2();

    self.$axios.get('http://localhost:8082/adopt/ol').then(function (res) {
      if (res.status == 200) {
        self.list = res.data.list;
        let list = res.data.list
        if (list.length < 4) {
          for (let i = 0; i < list.length; i++) {
            self.arr2[i] = list[i]
          }
        } else {
          for (let i = 0; i < 4; i++) {
            self.arr2[i] = list[i]
          }
        }
      } else {
        alert('에러');
      }
    });
  },
  mounted() {
    this.loadData();
    this.getKind(this.formatDate(this.sysdate), 417000)
      .then((totalCount1) => {
        return this.getKind(this.formatDate(this.sysdate), 422400)
          .then((totalCount2) => {
            return this.getKind(this.formatDate(this.sysdate), '')
              .then((totalCount3) => {
                const ctx = document.getElementById('PieChart').getContext('2d');
                const PieChart = new Chart(ctx, {
                  type: 'pie',
                  data: {
                    labels: [
                      '강아지',
                      '고양이',
                      '기타'
                    ],
                    datasets: [{
                      label: 'My First Dataset',
                      data: [totalCount1, totalCount2, totalCount3 - (totalCount1 + totalCount2)],
                      backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)'
                      ],
                      hoverOffset: 4
                    }]
                  },
                });
                PieChart;
              });
          })
          .catch((error) => {
            console.error(error);
          });
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    all_dogs() { // 강아지
      this.$router.push('/apidog');
    },
    all_cats() { // 고양이
      this.$router.push('/apicat');
    },
    fetchData2() {


      console.log(this.time);
      console.log(this.date);
      const self = this;
      self.$axios.get(`https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?dataType=json&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D&numOfRows=50&pageNo=1&base_date=${self.date}&base_time=${self.time}&nx=62&ny=122`)
        .then(function (res) {
          if (res.status == 200) {
            const data = res.data.response.body
            self.witems = data.items.item;
          } else {
            alert(res.status)
          }
        })
    },
    detail(num) {
      // alert(num)
      this.$router.push({ name: 'DiaryBoardDetail', query: { num: num } })
    },
    detail2(num) {
      this.$router.push({ name: 'AdoptDetail', query: { num: num } })
    },
    extractVideoId(url) {
      // Regular expression to extract the video ID from YouTube URL
      const regex = /[?&]v=([^&#]+)/;
      const match = url.match(regex);

      return match ? match[1] : null;
    },

    getKind(day, kindCd) {
      const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&bgnde=${day}&endde=${day}&pageNo=1&numOfRows=1000&upkind=${kindCd}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;

      return axios
        .get(apiUrl)
        .then((response) => {
          const data = response.data.response.body;
          this.items = data.items.item;
          this.totalItems = data.totalCount;
          return data.totalCount;
        })
        .catch((error) => {
          console.error(error);
          return 0;
        });
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
    },
    loadData() {
      const promises = [];
      const days = [this.D5, this.D4, this.D3, this.D2, this.D1, this.sysdate];
      days.forEach((day, index) => {
        const formattedDay = this.formatDate(day);
        promises.push(
          this.getCount(formattedDay)
            .then((totalCount) => {
              this[`t${index}`] = totalCount;
            })
            .catch((error) => {
              console.error(error);
            })
        );
      });

      Promise.all(promises)
        .then(() => {
          this.createChart();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getCount(day) {
      const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&bgnde=${day}&endde=${day}&pageNo=1&numOfRows=1000&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;

      return axios
        .get(apiUrl)
        .then((response) => {
          const data = response.data.response.body;
          this.items = data.items.item;
          this.totalItems = data.totalCount;
          return data.totalCount;
        })
        .catch((error) => {
          console.error(error);
          return 0;
        });
    },
    createChart() {
      const ctx = document.getElementById('BarChart').getContext('2d');
      const labels = [
        this.formatDate(this.D5),
        this.formatDate(this.D4),
        this.formatDate(this.D3),
        this.formatDate(this.D2),
        this.formatDate(this.D1),
        this.formatDate(this.sysdate)
      ];
      const data = [this.t0, this.t1, this.t2, this.t3, this.t4, this.t5];

      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [
            {
              label: '최근 6일간 들어온 아기들 추이',
              data: data,
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
            }
          ]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    },
    fadeoutAnim() {
      setTimeout(function() {
        document.getElementById('fadeout_text').style = 'display: none;';
        document.getElementById('fadeout_bg').style = 'display: none;';
      }, 3000);
    },
    showHeadText02() {
      document.addEventListener('scroll', () => {
        //alert('aaa');

        // if (document.scrollY > document.getElementById('container2').getBoundingClientRect().height/2) {
        //   window.scrollBy(0, 400);
        // }
        let imgBannerHeight = document.getElementById('container2').scrollTop + document.getElementById('container2').clientHeight;
        if (imgBannerHeight / 2 < window.scrollY) {
          document.getElementById('top_02').classList.add('active');
          setTimeout(function() {
            //window.scrollTo(imgBannerHeight, imgBannerHeight * 2);
          }, 6000)
        }

        
      });
    }
  }
}
</script>

<style scoped>
.bg_fadeout {
  position: absolute;
  width: 100%;
  height: 100%;
  background: #000;
  opacity: 30%;
  z-index: 9;
  animation: fadeout 3s;
}

.head-text {
  position: absolute;
  width: 100%;
  height: auto;
  z-index: 10;
  transform: translateX(-50%);
  margin-top: 10%;
  animation: fadein 3s;
}

@keyframes fadein {
  0% { opacity: 0; }
  50% { opacity: 1; }
  100% { opacity: 0;}
}
@keyframes fadeout {
  0% { opacity: 0; }
  50% { opacity: 0.5; }
  100% { opacity: 0;}
}

#app {
  display: relative;
  width: 100%;
  height: 3000px;
  justify-content: center;
}

.top_02 {
  position: absolute;
  width: 100%;
  height: 800px;
  justify-content: center;
  vertical-align: middle;
  background: white;
}

.top_02 p {
  margin-top: 200px;
  color: black;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 20px;
}

#top_02.active {
  animation: showtext 3s linear;
}

@keyframes showtext {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.form-group {
  display: relative;
  margin-top: 800px;
  position: relative;
  width: 100%;
  height: 1000px;
  justify-content: center;
}

.img-box {
  border: 1px solid silver;
  cursor: pointer;
  width: 293px;
  height: 260px;
}

.b-img {
  width: 293px;
  height: 200px;
}

.b-txt {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.b-title {
  font-size: large;
}

.b-id {
  font-size: medium;
  display: flex;
  justify-content: space-between;
}

.l-img {
  width: 15px;
  height: 15px;
}

.container {
  width: 100%;
  height: 1000px;
}

.container2 {
  position: relative;
  width: 100%;
  height: 550px;
  overflow: hidden;
}

.picture {
  position: absolute;
  top: 0;
  width: 100%;
  height: 550px;
  animation-duration: 3s;
  animation-timing-function: ease-out;
  animation-fill-mode: forwards;
}

.left {
  left: 0;
  animation-name: slideInLeft;
  animation-delay: 0s;
  clip-path: polygon(0 0, 66% 0, 100% 100%, 0% 100%, 0 100%);
  cursor: pointer;
}

.right {
  right: 50px;
  animation-name: slideInRight;
  animation-delay: 0s;
  clip-path: polygon(0 0, 100% 0, 100% 100%, 33% 100%, 0 0%);
  cursor: pointer;
}

@keyframes slideInLeft {
  0% {
    transform: translateX(-100%);
  }
  50% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-25%);
  }
}

@keyframes slideInRight {
  0% {
    transform: translateX(100%);
  }
  50% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(30%);
  }
}


</style>
