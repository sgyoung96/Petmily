<template>
    <div id="app">
      desertion: <input type="text" v-model="search" @input="searchItems"><br/>
      <table border="1px">
        <thead>
          <tr>
            <th>filename</th>
            <th>Care Name</th>
            <th>Happen Place</th>
            <th>desertionNo</th>
            <th>careAddr</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in filteredItems" v-bind:key="item.careNm">
            <td><router-link to="/detail"><img :src="item.filename" :alt="item.careNm" @click="showItemInfo(item)" style="cursor: pointer;" /></router-link></td>
            <td>{{ item.careNm }}</td>
            <td>{{ item.happenPlace }}</td>
            <td>{{ item.desertionNo }}</td>
            <td>{{ item.careAddr }}</td>
          </tr>
        </tbody>
      </table>
      <div v-if="selectedItem">
        <h2>Selected Item</h2>
        <p>desertionNo: {{ selectedItem.desertionNo }}</p>
        <p>filename: {{ selectedItem.filename }}</p>
        <p>happenDt: {{ selectedItem.happenDt }}</p>
        <p>happenPlace: {{ selectedItem.happenPlace }}</p>
        <!-- 추가 필요한 다른 정보들을 표시 -->
      </div>
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
        selectedItem: null,
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
      searchItems() {
        // 검색 입력이 변경될 때마다 filteredItems를 업데이트합니다.
        // computed 속성인 filteredItems가 자동으로 재계산됩니다.
      },
      showItemInfo(item) {
        this.selectedItem = item;
      },
    },
  };
  </script>