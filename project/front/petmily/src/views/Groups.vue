<template>
  <div id="app" style="margin-top:50px;">
    <div class="grid-container">
      <div v-for="item in displayedItems" :key="item.careNm" class="grid-item">
        <div class="card" style="  height: 186px;
  width:280px;">
          <div class="cardName">
            <div @click="handleItemClick(item.careAddr, item.careNm)">
              <strong>{{ item.careNm }}</strong>
            </div>
            <div class="divisionNm">
              {{ item.divisionNm }}
            </div>
          </div>
          <div>
            <img src="../assets/images/map.png" style="width: 15px; height: 15px;">
            {{ item.careAddr.length > 13 ? item.careAddr.substring(0, 13) + '...' : item.careAddr }}
          </div>
          <div>
            <img src="../assets/images/phone.png" style="width: 15px; height: 15px;">
            {{ item.careTel }}
          </div>
          <div v-if="item.weekOprStime && item.weekOprEtime && item.weekOprStime!== ':' && item.weekOprEtime!== ':'">
            <span
              style="border-top: 1px solid lightgrey; border-radius: 10px 10px 10px 10px; background-color: skyblue; color: white; padding: 5px; font-size: x-small;">
              <strong>평일운영시작:</strong> {{ item.weekOprStime }}~{{ item.weekOprEtime }}
          </span>
          </div>
          <div v-if="item.weekendOprStime && item.weekendOprEtime && item.weekendOprStime!== ':' && item.weekendOprEtime!== ':'">
            <span
              style="border-radius: 10px 10px 10px 10px; background-color: rgb(255, 134, 134); color: white; padding: 5px; font-size: x-small;">
              <strong>주말운영시작:</strong> {{ item.weekendOprStime }}~{{ item.weekendOprEtime }}
            </span>
          </div>
          <div v-if="item.closeDay && item.closeDay !== '0'">
  <span style="border-radius: 10px; background-color: rgb(255, 134, 134); color: white; padding: 5px; font-size: x-small;">
    <strong>{{ item.closeDay }}휴무</strong>
  </span>
</div>

        </div>
      </div>
    </div>
  </div>
  <div style="margin-top: 2%;">
    <button v-on:click="previousPage" class="custom-button" :disabled="pageNo === 1">이전</button>
    <button v-for="pageNumber in currentPageNumbers" :key="pageNumber" v-on:click="goToPage(pageNumber)"
      class="custom-button" :class="{ 'active-button': pageNo === pageNumber }">
      {{ pageNumber }}
    </button>
    <button v-on:click="nextPage" class="custom-button" :disabled="pageNo === totalPages">다음</button>
  </div>
</template>
  
<script>

export default {
  name: 'App',
  data() {
    return {
      items: [],
      pageNo: 1, // 현재 페이지 번호
      pageSize: 16, // 한 페이지에 표시할 항목 수
      totalItems: 0, // 전체 항목 수
      totalPages: 0, // 전체 페이지 수
      kindCd: '',
      displayedPages: [], // 현재 표시되는 페이지 버튼
      startPage: 1, // 시작 페이지 번호
      endPage: 10, // 끝 페이지 번호
      addr: ''
    };
  },
  computed: {
  displayedItems() {
    const startIndex = (this.pageNo - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    console.log(this.itmes)
    return this.items.filter((item, index) => index >= startIndex && index < endIndex);
  },
  currentPageNumbers() {
      const MAX_DISPLAYED_PAGES = 10; // 표시되는 최대 페이지 버튼 수
      let startPage = Math.max(1, this.pageNo - Math.floor(MAX_DISPLAYED_PAGES / 2));
      let endPage = Math.min(startPage + MAX_DISPLAYED_PAGES - 1, this.totalPages);

      const pageNumbers = [];
      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
  },


  created() {
    this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
  },
  methods: {
    fetchData() {
  const self = this;
  const url = 'https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?_type=json&numOfRows=1000&pageNo=' + this.pageNo + '&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D';
  self.$axios.get(url)
    .then(function (res) {
      if (res.status == 200) {
        const data = res.data.response.body;
        console.log(data.items.item); // 데이터 확인
        self.items = data.items.item;
        self.totalItems = parseInt(data.numOfRows);
        self.totalPages = Math.ceil(self.totalItems / self.pageSize);
        self.updateDisplayedPages();
      } else {
        alert(res.status);
      }
    })
    .catch(function (error) {
      console.log(error);
    });
},

    handleItemClick(addr, name) {
      this.addr = addr;
      this.name = name;
      this.$router.push({ name: 'AnimalHospital', params: { addr: this.addr, name: this.name } });
    },
    previousPage() {
      if (this.pageNo > 1) {
        this.pageNo--;
        this.fetchData();
      }
    },
    nextPage() {
      if (this.pageNo < this.totalPages) {
        this.pageNo++;
        this.fetchData();
      }
    },
    goToPage(pageNumber) {
      this.pageNo = pageNumber;
      this.fetchData();
    },
    updateDisplayedPages() {
      const MAX_DISPLAYED_PAGES = 10; // 표시되는 최대 페이지 버튼 수
      let startPage = Math.max(1, this.pageNo - Math.floor(MAX_DISPLAYED_PAGES / 2));
      let endPage = startPage + MAX_DISPLAYED_PAGES - 1;

      if (endPage > this.totalPages) {
        endPage = this.totalPages;
        startPage = Math.max(1, endPage - MAX_DISPLAYED_PAGES + 1);
      }

      this.displayedPages = [];
      for (let i = startPage; i <= endPage; i++) {
        this.displayedPages.push(i);
      }

      this.startPage = startPage;
      this.endPage = endPage;
    }
  }
};
</script>
  
  
<style>
.cardName {
  display: flex;
  justify-content: space-between;
}

.divisionNm {
  font-size: small
}

.custom-button {
  background-color: #f0cf81;
  /* 배경색 설정 */
  border: none;
  /* 테두리 제거 */
  color: white;
  /* 텍스트 색상 설정 */
  padding: 10px 20px;
  /* 안쪽 여백 설정 */
  text-align: center;
  /* 텍스트 가운데 정렬 */
  text-decoration: none;
  /* 밑줄 제거 */
  display: inline-block;
  /* 인라인 요소로 표시 */
  font-size: 16px;
  /* 폰트 크기 설정 */
  margin: 4px 2px;
  /* 외부 여백 설정 */
  cursor: pointer;
  /* 커서 포인터로 변경 */
  border-radius: 4px;
  /* 모서리를 둥글게 설정 */
}

.custom-button:hover {
  background-color: #f0cf81;
  /* 마우스 호버시 배경색 변경 */
}

.card {
  border-radius: 10px;
  text-align: left;
  padding: 20px;
  /* 모서리를 둥글게 만듭니다 */
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