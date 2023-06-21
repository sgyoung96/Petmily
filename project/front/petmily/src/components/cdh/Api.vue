<template>
  <div id="app">
    <table border="1px">
      <thead>
        <tr>
          <th>filename</th>
          <th>Care Name</th>
          <th>Happen Place</th>
          <th>desertionNo</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in items" :key="item.desertionNo">
          <td>
            <img :src="item.filename" :alt="item.careNm" @click="handleItemClick(item.desertionNo)" style="cursor: pointer;" />
          </td>
          <td>{{ item.careNm }}</td>
          <td>{{ item.happenPlace }}</td>
          <td>{{ item.desertionNo }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
  return {
    items: [],
  };
  },
  created() {
    axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
  },
  methods: {
    handleItemClick(desertionNo) {
  console.log(desertionNo); // desertionNo 값 확인
  this.desertionNo = desertionNo; // desertionNo 값을 설정
  this.$router.push({ name: 'Detail', params: { desertionNo: desertionNo } });
    },
  },
};
</script>
