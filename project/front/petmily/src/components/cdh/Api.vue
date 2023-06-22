<template>
  <div class="button-container">
    <div id="button-style">
      <button v-on:click="AllKindCd">전체</button>
      <button v-on:click="DogkindCd">개</button>
      <button v-on:click="CatkindCd">고양이</button>
    </div>
  </div>
  <div id="app">
    <div class="grid-container">
      <div v-for="item in items" :key="item.desertionNo" class="grid-item">
        <div class="card">
          <div>
            <img :src="item.filename" :alt="item.careNm" @click="handleItemClick(item.desertionNo)"
              style="cursor: pointer; width:300px; height: 200px;">
          </div>
          <div class="item-info-space-between">
            <div class="info-item info-left">{{ item.kindCd }}</div>
            <div class="info-item info-right">{{ item.happenDt }}</div>
          </div>
          <div class="item-info">
            <div id="processState">
              {{ item.processState }}
            </div>
            <div id="age">
              {{ item.age }}
            </div>
          </div>
          <div class="item-info">
            <div id="colorCd">
              {{ item.colorCd }}
            </div>
            <div id="weight">
              {{ item.weight }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div><button v-on:click="previousPage">이전</button>
    <button v-on:click="page1">1</button>
    <button v-on:click="page2">2</button>
    <button v-on:click="page3">3</button>
    <button v-on:click="page4">4</button>
    <button v-on:click="page5">5</button>
    <button v-on:click="page6">6</button>
    <button v-on:click="page7">7</button>
    <button v-on:click="page8">8</button>
    <button v-on:click="page9">9</button>
    <button v-on:click="page10">10</button>
    <button v-on:click="nextPage">다음</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      items: [],
      pageNo: 1, // 현재 페이지 번호
      pageSize: 24, // 한 페이지에 표시할 항목 수
      totalItems: 0, // 전체 항목 수
      kindCd: ''
    };
  },
  created() {
    this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
  },
  methods: {
    fetchData() {
      const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&upkind=${this.kindCd}&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
      axios.get(apiUrl)
        .then((response) => {
          const data = response.data.response.body;
          this.items = data.items.item;
          this.totalItems = data.totalCount;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleItemClick(desertionNo) {
      console.log(desertionNo); // desertionNo 값 확인
      this.desertionNo = desertionNo; // desertionNo 값을 설정
      this.$router.push({ name: 'Detail', params: { desertionNo: desertionNo } });
    },
    previousPage() {
      if (this.pageNo > 1) {
        this.pageNo--;
        this.fetchData();
      }
    },
    nextPage() {
      const totalPages = Math.ceil(this.totalItems / this.pageSize);
      if (this.pageNo < totalPages) {
        this.pageNo++;
        this.fetchData();
      }
    },
    page1() {
      this.pageNo = 1;
      this.fetchData();
    },
    page2() {
      this.pageNo = 2;
      this.fetchData();
    },
    page3() {
      this.pageNo = 3;
      this.fetchData();
    },
    page4() {
      this.pageNo = 4;
      this.fetchData();
    },
    page5() {
      this.pageNo = 5;
      this.fetchData();
    },
    page6() {
      this.pageNo = 6;
      this.fetchData();
    },
    page7() {
      this.pageNo = 7;
      this.fetchData();
    },
    page8() {
      this.pageNo = 8;
      this.fetchData();
    },
    page9() {
      this.pageNo = 9;
      this.fetchData();
    },
    page10() {
      this.pageNo = 10;
      this.fetchData();
    },
    AllKindCd() {
      this.kindCd = "";
      this.fetchData();
    },
    DogkindCd() {
      this.kindCd = '417000';
      this.fetchData();
    },
    CatkindCd() {
      this.kindCd = '422400';
      this.fetchData();
    }
  },


};
</script>


<style>
.card {
  border-radius: 10px; /* 모서리를 둥글게 만듭니다 */
  /* 다른 스타일을 추가로 적용할 수 있습니다 */
}
.button-container {
  display: flex;
  justify-content: flex-end;
  margin-right: 136px;
}

.grid-container {
  display: grid;
  margin-left: 150px;
  margin-right: 200px;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(6, 1fr);
  gap: 10px;
}

.grid-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.item-info {
  display: center;

  /* 줄 바꿈 방지 */
}

.item-info-space-between {
  display: grid;
  grid-template-columns: auto auto;
  justify-items: space-between;
  /* 양쪽 끝에 배치 */
  gap: 10px;
  /* 요소 사이 간격 조정 */
}

.info-item {
  display: inline-block;
}


#processState {
  display: inline-block;
  margin-right: 10px;
  background-color: aquamarine;
  color: white;
}

#age {
  display: inline-block;
  margin-right: 10px;
  background-color: bisque;
  color: white;
}

#colorCd {
  display: inline-block;
  margin-right: 10px;
  background-color: rgb(203, 155, 248);
  color: white;
}

#weight {
  display: inline-block;
  margin-right: 10px;
  background-color: rgb(199, 97, 129);
  color: white;
}
</style>