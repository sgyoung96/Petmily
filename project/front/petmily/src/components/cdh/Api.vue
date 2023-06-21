<template>
  <div id="app">
    <table border="1px">
     <tbody v-for="item in items" :key="item.desertionNo">
      <div class="w-full grid grid-cols-2 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-y-3 gap-x-3">
        <div class="max-h-48 overflow-hidden rounded-t-lg relative sm:h-[192px]">
          <div class="h-40 sm:h-48 overflow-hidden relative h-[192px] hover:scale-110 transition-transform">
            <div>
            <img :src="item.filename" :alt="item.careNm" @click="handleItemClick(item.desertionNo)" style="cursor: pointer;">
          </div>
          </div>
          <div>
            {{ item.careNm }}
          </div>
        </div>
      </div>
      </tbody>
    </table>
    <div><button v-on:click="page1">1</button>
    <button v-on:click="page2">2</button>
    <button v-on:click="page3">3</button>
    <button v-on:click="page4">4</button>
    <button v-on:click="page5">5</button>
    <button v-on:click="page6">6</button>
    <button v-on:click="page7">7</button>
    <button v-on:click="page8">8</button>
    <button v-on:click="page9">9</button>
    <button v-on:click="page10">10</button></div>

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
    page1(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page2(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=2&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page3(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=3&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page4(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=4&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page5(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=5&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page6(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=6&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page7(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=7&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page8(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=8&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page9(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=9&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    },
    page10(){
      axios
      .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=10&numOfRows=24&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        const data = response.data;
        const items = data.response.body.items.item;
        this.items = items; // 데이터를 items 배열에 할당
      });
    }
  },
};
</script>


<style>
.grid-container {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 10px;
}

.grid-item {
  border: 1px solid black;
  padding: 10px;
  text-align: center;
}

.care-name,
.happen-place,
.desertion-no {
  margin-top: 10px;
}
</style>