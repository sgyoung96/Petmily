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

      <div id="top_02" class="top_02">
        <p>당신만을 바라보는 반려 동물 유기 횟수 10만 마리</p>
      </div>

      <!-- Display YouTube video -->
      <!-- Display YouTube video -->
      <div class="form-group">
        <div v-if="isValidVideoUrl">
          <iframe :src="embeddedVideoUrl" width="80%" height="800px" frameborder="0" allowfullscreen></iframe>
        </div>
      </div>



      <div id="top_03" class="top_02">
        <p>새로운 가족을 기다려요</p>
      </div>

    </div>



    
    <div class="container-chart">
      <div>
        <canvas id="PieChart" width="400" height="400"></canvas>
        <!--{{ formatDate(this.sysdate) }}-->
        <p class="txt-today">유기 반려 동물 비율</p>
      </div>
      <div>
        <canvas id="BarChart" width="400" height="400"></canvas>
        <p class="txt-today">새 가족을 기다리는 아가들</p>
      </div>
    </div>

    <div class="box-bg-cat-dog">
      <img class="bg-cat-dog" src="../assets/images/댕냥.png" />
    </div>





    <div class="container-dash-board">
      <div class="container-box01">
        <label class="lbl-title">새 반려인을 찾고 있어요</label>
        <div class="box-atboard">
          <div v-for="dboard in arr" :key="dboard.num">
            <div class="img-box" v-on:click="$event => detail(dboard.num)">
              <a><img class="b-img" :src="'http://localhost:8082/dboard/imgs/' + dboard.num + '/1'"></a>
              <div class="b-txt">
                <div class="b-title">
                  {{ dboard.title }}
                </div>
                <div class="b-id">
                  <span class="span-name">
                    작성자: {{dboard.id.name}} ({{ dboard.id.id }})
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
      </div>

      <div class="container-box01">
        <label class="lbl-title">우리 아가 잘 지내고 있어요</label>

          <div class="box-dbboard">
          <div v-for="dboard in arr2" :key="dboard.num">
            <div class="img-box" v-on:click="$event => detail2(dboard.num)">
              <a><img class="b-img" :src="'http://localhost:8082/adopt/imgs/' + dboard.num + '/1'"></a>
              <div class="b-txt">
                <div class="b-title">
                  {{ dboard.title }}
                </div>
                <div class="b-id">
                  <span class="span-name">
                    작성자: {{ dboard.id.name }} ({{dboard.id.id}})
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
      </div>
    </div>
  </div>
    
  <div class="container text-center box-walking">
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
                        'rgb(224, 243, 245)',
                        'rgb(243, 216, 218)',
                        '#eeeeee'
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
              label: '오늘 들어온 아가들',
              data: data,
              backgroundColor: [
                'rgba(255, 99, 132, 0.7)',
                'rgba(54, 162, 235, 0.7)',
                'rgba(255, 206, 86, 0.7)',
                'rgba(75, 192, 192, 0.7)',
                'rgba(153, 102, 255, 0.7)',
                'rgba(255, 159, 64, 0.7)'
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
        try {
          document.getElementById('fadeout_text').style.display = 'none';
          document.getElementById('fadeout_bg').style.display = 'none';
        } catch(e) {
          console.log(e);
        }
      }, 3000);
    },
    showHeadText02() {
      document.addEventListener('scroll', () => {
        //alert('aaa');

        // if (document.scrollY > document.getElementById('container2').getBoundingClientRect().height/2) {
        //   window.scrollBy(0, 400);
        // }
        // let imgBannerHeight = document.getElementById('container2').scrollTop + document.getElementById('container2').clientHeight;
        // if (imgBannerHeight / 2 < window.scrollY) {
        //   document.getElementById('top_02').classList.add('active');
        //   setTimeout(function() {
        //     //window.scrollTo(imgBannerHeight, imgBannerHeight * 2);
        //   }, 6000)
        // }

        
      });
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Single+Day&display=swap');
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

.home {
  background: linear-gradient(to bottom, #000000 0%, #000000 10%, rgb(244, 191, 79) 40%, #c9d4d2 60%, white 100%);
  position: relative;
  display: block;
  width: 100%;
  justify-content: center;
}

#app {
  display: relative;
  width: 100%;
  justify-content: center;
}

.top_02 {
  position: relative;
  width: 100%;
  justify-content: center;
  vertical-align: middle;
}

.top_02 p {
  margin-top: 400px;
  color: white;
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

#top_03 p {
  margin-top: 400px;
  color: black;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 20px;
}

.form-group {
  margin-top: 400px;
  position: relative;
  width: 100%;
  justify-content: center;
}

.container-box01 {
  display: block;
  position: relative;
  width: 50%;
  height: 50%;
  justify-content: center;
}

.img-box {
  width: 300px;
  border: 1px solid silver;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
  justify-content: space-evenly;
}

.b-img {
  width: 300px;
  height: 200px;
  border-radius: 10px;
  object-fit: cover;
}

.b-txt {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.b-title {
  font-family: 'Single Day', cursive;
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

.container2 {
  background: #000;
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

.container-chart {
  padding-left: 100px;
  position: relative;
  display: flex;
  width: 70%;
  justify-content: center;
  margin-top: 400px;
  z-index: 2;
}

.txt-today {
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 13px;
  color: black;
  margin-top: 10px;
}

.box-walking {
  margin-top: 800px;
  display: block;
  position: relative;
  width: 90%;
}

.container-dash-board {
  display: flex;
  position: relative;
  width: 100%;
  justify-content: center;
  margin-top: 600px;
  padding-left: 60px;
}

.lbl-title {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 20px;
  color: black;
  margin-bottom: 50px;
}

.box-dbboard {
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
  position: relative;
  width: 90%;
  display: grid;
  justify-content: center;
}

.box-atboard {
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
  position: relative;
  width: 90%;
  display: grid;
  justify-content: center;
}

.box-bg-cat-dog {
  width: 600px;
  height: 600px;
  overflow: hidden;
  transform: translateX(100px) translateY(-400px);
  position: absolute;
  display: inline;
  z-index: 1;
}

.bg-cat-dog {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.span-name {
  font-family: 'IBMPlexSansKR-Medium';
  font-size: 12px;
  color: black;
}
</style>
