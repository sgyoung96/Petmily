<template>
  <div id="DetailsItem">
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
        <tr v-for="item in filteredItems" :key="item.desertionNo">
          <td>
            <img :src="item.filename" :alt="item.careNm" />
          </td>
          <td>{{ item.careNm }}</td>
          <td>{{ item.happenPlace }}</td>
          <td>{{ item.desertionNo }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div>{{ items }}</div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'DetailsItem',
  data() {
    return {
      items: [],
    };
  },

  computed: {
    filteredItems() {
      if (!this.desertionNo) {
        return [];
      }
      return this.items.filter(item => item.desertionNo === this.desertionNo);
    },
  },

  created() {
    this.desertionNo = this.$route.params.desertionNo;
  this.fetchItems();
},

  methods: {
    fetchItems() {
      axios
        .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=100&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
        .then((response) => {
          const data = response.data;
          const items = data.response.body.items.item;
          this.items = items; // 데이터를 items 배열에 할당
          console.log(this.items); // items 출력
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>