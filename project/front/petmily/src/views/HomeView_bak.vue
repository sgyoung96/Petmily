<template>
  <div class="home">
    <div class="container">
      <h3>지금 유기되고 있는 반려 동물의 수</h3>
 

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



      <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active" data-bs-interval="3000">
            <img src="../assets/images/top_banner_cat_01.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item" data-bs-interval="3000">
            <img src="../assets/images/top_banner_dog_01.jpg" class="d-block w-100" alt="...">
          </div>
          

          <!-- <div class="carousel-item" data-bs-interval="2000">
            <img src="../assets/images/배경.png" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="../assets/images/고양.png" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img src="../assets/images/강고.png" class="d-block w-100" alt="...">
          </div> -->
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval"
          data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval"
          data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
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
    
      <div id="app">
      <!-- ... Existing code ... -->

      <div class="form-group">
        <label for="volboard-video">유튜브 동영상</label>
        <input type="text" class="form-control" v-model="videoUrl">
      </div>

      <!-- Display YouTube video -->
      <!-- Display YouTube video -->
      <div class="form-group">
        <label for="volboard-video-preview">동영상 미리보기</label>
        <div v-if="isValidVideoUrl">
          <iframe :src="embeddedVideoUrl" width="560" height="315" frameborder="0" allowfullscreen></iframe>
        </div>
        <div v-else>
          <p>유효한 YouTube 동영상 URL을 입력하세요.</p>
        </div>
      </div>

      <!-- ... Existing code ... -->
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
      items: [],
      totalItems: 0, // 전체 항목 수
      sysdate: sysdate,
      D1: D1,
      D2: D2,
      D3: D3,
      D4: D4,
      D5: D5,
      videoUrl: 'https://www.youtube.com/watch?v=3AV35NdBZOI'
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
  created: function() { 
    
  },
  mounted() {
    this.loadData();
    // this.getKind(this.formatDate(this.sysdate), 417000)
    //   .then((totalCount1) => {
    //     return this.getKind(this.formatDate(this.sysdate), 422400)
    //       .then((totalCount2) => {
    //         return this.getKind(this.formatDate(this.sysdate), '')
    //           .then((totalCount3) => {
    //             const ctx = document.getElementById('PieChart').getContext('2d');
    //             const PieChart = new Chart(ctx, {
    //               type: 'pie',
    //               data: {
    //                 labels: [
    //                   '강아지',
    //                   '고양이',
    //                   '기타'
    //                 ],
    //                 datasets: [{
    //                   label: 'My First Dataset',
    //                   data: [totalCount1, totalCount2, totalCount3 - (totalCount1 + totalCount2)],
    //                   backgroundColor: [
    //                     'rgb(255, 99, 132)',
    //                     'rgb(54, 162, 235)',
    //                     'rgb(255, 205, 86)'
    //                   ],
    //                   hoverOffset: 4
    //                 }]
    //               },
    //             });
    //             PieChart;
    //           });
    //       })
    //       .catch((error) => {
    //         console.error(error);
    //       });
    //   })
    //   .catch((error) => {
    //     console.error(error);
    //   });
  },
  methods: {
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
      let formattedDate = new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
      console.log(formattedDate);

      let year = formattedDate.substring(0, 4);
      let month = formattedDate.substring(4, 6);
      let day = formattedDate.substring(6, 8);
      return year + '-' + month + '-' + day;
    },
    loadData() {
      const self = this;
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
          this.getKind(self.formatDate(self.sysdate), 417000)
          .then((totalCount1) => {
            return self.getKind(self.formatDate(self.sysdate), 422400)
              .then((totalCount2) => {
                return self.getKind(self.formatDate(self.sysdate), '')
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
  }
}
</script>

<style scoped>
.container {
  width: 100%;
}

.carousel-item {
  border: 2px solid rgba(244, 192, 79, 0.699);
  border-radius: 20px;
}

img {
  width: 100%;
  height: 500px;
  border: 2px solid rgb(244, 191, 79);
  border-radius: 20px;
}

#carouselExampleInterval {
  height: 500px;
}
</style>
