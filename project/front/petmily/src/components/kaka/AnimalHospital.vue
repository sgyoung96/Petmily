<template>
    <h3>동물병원</h3>
    <div v-for="item in items" :key="item.careNm">
        <div>
            <table border="1">
                <tr><td>{{ item.careNm }}</td></tr>
                <tr><td>{{ item.orgNm }}</td></tr>
                <tr><td>dddddddddddddd</td></tr>
            </table>
        </div>
    </div>
</template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'App',
    data() {
      return {
        items:[]
      };
    },
    created() {
      this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
    },
    methods: {
      fetchData() {
        const apiUrl = `https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?numOfRows=1000&pageNo=1&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D`;
        axios.get(apiUrl)
          .then((response) => {
            const data = response.data.response.body;
            this.items = data.items.item;
          })
          .catch((error) => {
            console.error(error);
          });
      },
     
      },
  };
  </script>
  
  
  <style>
  
  </style>