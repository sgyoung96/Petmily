<template>
  <div>
    <div v-for="item in items" :key="item.base_date">
      <div>
        <span v-if="item.category === 'TMP'"><br />{{ item.fcstTime }}시 예보<br /> 기온: {{ item.fcstValue }}도
          <span v-if="item.fcstValue <= '18'">양호🐾</span>
          <span v-else-if="item.fcstValue <= '21'">조금 주의💦</span>
          <span v-else-if="item.fcstValue >= '26' && item.fcstValue < '32'">주의🚨</span>
          <span v-else-if="item.fcstValue >= '29' && item.fcstValue < '32'">위험🔥</span>
          <span v-else-if="item.fcstValue >= '32'">매우위험🚷</span>
        </span>
        <span v-else-if="item.category === 'SKY'">
          날씨:
          <span v-if="item.fcstValue === '1'">🌞</span>
          <span v-else-if="item.fcstValue === '3'">⛅</span>
          <span v-else-if="item.fcstValue === '4'">☁</span>
        </span>
        <span v-else-if="item.category === 'PCP'">강수량: {{ item.fcstValue }}</span>
      </div>
    </div>
  </div>
  <table class="table" v-if="items.length">
  <thead>
    <tr>
      <th scope="col">#{{ this.date }}일자 산책</th>
      <th scope="col" v-if="items.length">{{ items[0].fcstTime }}시</th>
      <th scope="col" v-if="items.length">{{ items[12].fcstTime }}시</th>
      <th scope="col" v-if="items.length">{{ items[24].fcstTime }}시</th>
      <th scope="col" v-if="items.length">{{ items[36].fcstTime }}시</th>
      <th scope="col" v-if="items.length">{{ items[48].fcstTime }}시</th>
    </tr>
  </thead>
  <tbody v-if="items.length">
    <tr>
      <th scope="row">기온</th>
      <td>{{ items[0].fcstValue }}℃</td>
      <td>{{ items[12].fcstValue }}℃</td>
      <td>{{ items[24].fcstValue }}℃</td>
      <td>{{ items[36].fcstValue }}℃</td>
      <td>{{ items[48].fcstValue }}℃</td>
    </tr>
    <tr>
      <th scope="row">날씨</th>
      <td v-if="items.length">
        <span v-if="items[9].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="items[5].fcstValue === '1'">🌞</span>
          <span v-else-if="items[5].fcstValue === '3'">⛅</span>
          <span v-else-if="items[5].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="items.length">
        <span v-if="items[21].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="items[17].fcstValue === '1'">🌞</span>
          <span v-else-if="items[17].fcstValue === '3'">⛅</span>
          <span v-else-if="items[17].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="items.length">
        <span v-if="items[33].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="items[29].fcstValue === '1'">🌞</span>
          <span v-else-if="items[29].fcstValue === '3'">⛅</span>
          <span v-else-if="items[29].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="items.length">
        <span v-if="items[45].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="items[41].fcstValue === '1'">🌞</span>
          <span v-else-if="items[41].fcstValue === '3'">⛅</span>
          <span v-else-if="items[41].fcstValue === '4'">☁</span>
        </span>
      </td>
      <td v-if="items.length">
        <span v-if="items[45].fcstValue != '강수없음'">☔</span>
        <span v-else>
          <span v-if="items[41].fcstValue === '1'">🌞</span>
          <span v-else-if="items[41].fcstValue === '3'">⛅</span>
          <span v-else-if="items[41].fcstValue === '4'">☁</span>
        </span>
      </td>
    </tr>
    <tr>
      <th scope="row">강수량</th>
      <td v-if="items.length">{{ items[9].fcstValue }}</td>
      <td v-if="items.length">{{ items[21].fcstValue }}</td>
      <td v-if="items.length">{{ items[33].fcstValue }}</td>
      <td v-if="items.length">{{ items[45].fcstValue }}</td>
      <td v-if="items.length">{{ items[45].fcstValue }}</td>
    </tr>
    <tr>
      <th scope="row">갱얼쥐 산책</th>
      <td>
        <span v-if="items[0].fcstValue <= '18'">양호🐾</span>
        <span v-else-if="items[0].fcstValue >= '21' && items[0].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="items[0].fcstValue >= '26' && items[0].fcstValue < '29'">주의🚨</span>
        <span v-else-if="items[0].fcstValue >= '29' && items[0].fcstValue < '32'">위험🔥</span>
        <span v-else-if="items[0].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="items[12].fcstValue <= '18'">양호🐾</span>
        <span v-else-if="items[12].fcstValue >= '21' && items[12].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="items[12].fcstValue >= '26' && items[12].fcstValue < '29'">주의🚨</span>
        <span v-else-if="items[12].fcstValue >= '29' && items[12].fcstValue < '32'">위험🔥</span>
        <span v-else-if="items[12].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="items[24].fcstValue <= '18'">양호🐾</span>
        <span v-else-if="items[24].fcstValue >= '21' && items[48].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="items[24].fcstValue >= '26' && items[24].fcstValue < '29'">주의🚨</span>
        <span v-else-if="items[24].fcstValue >= '29' && items[24].fcstValue < '32'">위험🔥</span>
        <span v-else-if="items[24].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="items[36].fcstValue <= '18'">양호🐾</span>
        <span v-else-if="items[36].fcstValue >= '21' && items[36].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="items[36].fcstValue >= '26' && items[36].fcstValue < '29'">주의🚨</span>
        <span v-else-if="items[36].fcstValue >= '29' && items[36].fcstValue < '32'">위험🔥</span>
        <span v-else-if="items[36].fcstValue >= '32'">매우위험🚷</span>
      </td>
      <td>
        <span v-if="items[48].fcstValue <= '18'">양호🐾</span>
        <span v-else-if="items[48].fcstValue >= '21' && items[48].fcstValue < '26'">약간주의💦</span>
        <span v-else-if="items[48].fcstValue >= '26' && items[48].fcstValue < '29'">주의🚨</span>
        <span v-else-if="items[48].fcstValue >= '29' && items[48].fcstValue < '32'">위험🔥</span>
        <span v-else-if="items[48].fcstValue >= '32'">매우위험🚷</span>
      </td>
    </tr>
  </tbody>
  <tbody v-else>
    <tr>
      <td colspan="6">데이터가 없습니다.</td>
    </tr>
  </tbody>
</table>

</template>
  
<script>

export default {
  name: 'App',
  data() {
    return {
      items: [],
      datetime: new Date(),
      date: this.formatDate(new Date()),
      time: ''
    };
  },
  created() {
    if (8 <= this.datetime.getHours() && this.datetime.getHours() <= 9) {
      this.time = '0800';
    } else if (9 <= this.datetime.getHours() && this.datetime.getHours() < 10) {
      this.time = '0800';
    } else if (10 <= this.datetime.getHours() && this.datetime.getHours() < 11) {
      this.time = '0800';
    } else if (11 <= this.datetime.getHours() && this.datetime.getHours() < 12) {
      this.time = '0800';
    } else if (12 <= this.datetime.getHours() && this.datetime.getHours() < 13) {
      this.time = '0800';
    } else if (13 <= this.datetime.getHours() && this.datetime.getHours() < 14) {
      this.time = '0800';
    } else if (14 <= this.datetime.getHours() && this.datetime.getHours() < 15) {
      this.time = '1400';
    } else if (15 <= this.datetime.getHours() && this.datetime.getHours() < 16) {
      this.time = '1400';
    } else if (16 <= this.datetime.getHours() && this.datetime.getHours() < 17) {
      this.time = '1400';
    } else {
      this.time = '2000';
    }

    this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
  },
  methods: {
    fetchData() {


      console.log(this.time);
      console.log(this.date);
      const self = this;
      self.$axios.get(`https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?dataType=json&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D&numOfRows=50&pageNo=1&base_date=${self.date}&base_time=${self.time}&nx=62&ny=122`)
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