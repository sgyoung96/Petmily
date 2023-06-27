<template>
    <canvas id="PieChart" width="50" height="50"></canvas>
    {{ formatDate(this.sysdate) }}
  </template>
  
  <script>
  import Chart from 'chart.js/auto';
  import axios from 'axios';
  
  export default {
    name: "PieChart",
    data() {
      const sysdate = new Date();
  
      return {
        items: [],
        totalItems: 0, // 전체 항목 수
        sysdate: sysdate
      };
    },
    mounted() {
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
      }
    }
  }
  </script>
  
  <style></style>  