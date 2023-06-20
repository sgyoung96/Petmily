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
        <tr v-for="item in filteredItems" :key="item.careNm">
          <td>
            <img :src="item.filename" :alt="item.careNm" @click="goToDetail(item.desertionNo)" style="cursor: pointer;" />
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
      search: '',
    };
  },
  computed: {
    filteredItems() {
      if (!this.search) {
        return this.items;
      }
      const searchValue = this.search.toLowerCase();
      return this.items.filter(item => item.desertionNo.toLowerCase().includes(searchValue));
    },
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
    goToDetail(desertionNo) {
      this.$router.push({ name: 'Detail', params: { desertionNo: desertionNo } });
    },
  },
};
</script>
