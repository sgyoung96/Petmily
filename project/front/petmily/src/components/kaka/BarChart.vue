<template>
  <canvas id="BarChart" width="200" height="200"></canvas>
  <div>{{ t1 }}</div>
  <div>{{ t2 }}</div>
  <div>{{ t3 }}</div>
</template>

<script>
import Chart from 'chart.js/auto';
import axios from 'axios';

export default {
  name: "BarChart",
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
      daycount: this.D5,
      sysdate: sysdate,
      D1: D1,
      D2: D2,
      D3: D3,
      D4: D4,
      D5: D5
    };
  },
  mounted() {
    this.loadData();
  },
  methods: {
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
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
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
              label: '# of Votes',
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
    }
  }
};
</script>

<style></style>