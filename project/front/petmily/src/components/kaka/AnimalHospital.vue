<template>
    <div>
      <h3>동물병원</h3>
      <div v-for="item in items" :key="item.careNm">
        {{ item.careNm }}
      </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'AnimalHospital',
    data() {
      return {
        items: [],
      };
    },
    created() {
      this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
    },
    methods: {
      fetchData() {
        const self = this;
        self.$axios.get('https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?_type=json&numOfRows=1000&pageNo=1&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D')
        .then(function(res){
            if(res.status==200){
                const data = res.data.response.body
                self.items = data.items.item;
            }else{
                alert(res.status)
            }
        })
      },
    },
  };
  </script>
  
  <style>
  </style>  