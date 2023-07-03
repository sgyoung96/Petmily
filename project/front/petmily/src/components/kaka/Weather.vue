<template>
  <div>
    <div v-for="item in items" :key="item.base_date">
      <div>
        {{ item.fcstTime }}시 예보
        <span v-if="item.category == 'TMP'">기온: {{ item.fcstValue }}</span>
        <span v-else-if="item.category === 'VEC'">풍향: {{ item.fcstValue }}</span>
        <span v-else-if="item.category === 'WSD'">풍속: {{ item.fcstValue }}</span>
        <span v-else-if="item.category === 'SKY'">날씨: <span v-if="item.fcstValue == '1'">맑음</span><span v-else-if="item.fcstValue == '3'">구름많음</span><span v-else-if="item.fcstValue == '4'">흐림</span></span>
        <span v-else-if="item.category === 'PTY'">강수형태: {{ item.fcstValue }}</span>
        <span v-else-if="item.category === 'POP'">강수확률: {{ item.fcstValue }}</span>
        <span v-else-if="item.category === 'PCP'">강수량: {{ item.fcstValue }}</span>
        <span v-else>dd</span>
      </div>
    </div>
  </div>
</template>
  
<script>

export default {
  name: 'App',
  data() {
    return {
      items: [],
      datetime: this.formatDate2(new Date()),
      date: this.formatDate(new Date()),
      time: ''
    };
  },
  created() {
    this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
  },
  methods: {
    fetchData() {
      const self = this;
      // if (self.datetime.getHours() < 12) {
      //   self.time = '0800';
      // } else {
      //   self.time = '1400';
      // }
      alert(self.date)
      self.$axios.get(`https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?dataType=json&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D&numOfRows=50&pageNo=1&base_date=${self.date}&base_time=0800&nx=55&ny=127`)
        .then(function (res) {
          if (res.status == 200) {
            const data = res.data.response.body
            self.items = data.items.item;
          } else {
            alert(res.status)
          }
        })
    },
    handleItemClick(addr, name) {
      this.addr = addr;
      this.name = name;
      this.$router.push({ name: 'AnimalHospital', params: { addr: this.addr, name: this.name } });
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
    },
    formatDate2(date) {
      const options = {
        hour: '2-digit',
        minute: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
    }

  },


};
</script>
  
  
<style></style>