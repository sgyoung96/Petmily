<template>
  <div id="p" class="col-1">
  </div>
  <div class="col-10"
    style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 130px; text-align: left; background-color:white;">
    <div>
  <button v-on:click="place" class="custom-button">전체</button>
  <div v-for="button in placeButtons" :key="button.label">
    <button v-on:click="button.onClick" class="custom-button">{{ button.label }}</button>
  </div>
</div>
  </div>
  <div id="app" style="margin-top:50px;">
    <div class="grid-container">
      <div v-for="item in items" :key="item.desertionNo" class="grid-item">
        <div class="card">
          <div>
            <img :src="item.popfile" :alt="item.careNm" @click="handleItemClick(item.desertionNo)"
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
  <div style="margin-top: 2%;">
    <button v-on:click="previousPage" class="custom-button">이전</button>
    <button v-for="pageNumber in displayedPages" :key="pageNumber" v-on:click="goToPage(pageNumber)"
      class="custom-button">
      {{ pageNumber }}
    </button>
    <button v-on:click="nextPage" class="custom-button">다음</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      items: [],
      placeButtons: [],
      pageNo: 1,
      pageSize: 24,
      totalItems: 0,
      totalPages: 0,
      kindCd: '',
      displayedPages: [],
      startPage: 1,
      endPage: 10,
    };
  },

  created() {
    this.fetchData(); // 페이지가 생성될 때 데이터 가져오기
  },
  methods: {
    fetchData() {
      // 기존 코드
      const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
      axios.get(apiUrl)
        .then((response) => {
          const data = response.data.response.body;
          this.items = data.items.item;
          this.totalItems = data.totalCount;
          this.totalPages = 20;
          this.updateDisplayedPages();
        })
        .catch((error) => {
          console.error(error);
        });

      // 새로운 코드 - 장소 데이터 가져오기
      this.place();
    },
    place() {
  const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido?_type=json&pageNo=1&numOfRows=100&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
  axios.get(apiUrl)
    .then((response) => {
      const placeData = response.data.response.body;
      const items = placeData.items.item;

      // orgdownNm 값을 추출하여 버튼 배열을 생성합니다
      this.orgdownNms = items.map(item => item.orgdownNm);

      // placeButtons 배열을 생성하여 각 버튼을 저장합니다
      this.placeButtons = this.orgdownNms.map(orgdownNm => ({
        label: orgdownNm,
        onClick: () => {
          // 버튼 클릭 시 동작할 로직을 여기에 작성합니다
          // 예: 선택한 지역에 따라 다른 데이터를 가져오거나 처리합니다
        }
      }));
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
      } else {
        alert("이전 페이지가 없습니다");
      }

    },
    nextPage() {
      const totalPages = Math.ceil(this.totalItems / this.pageSize);
      if (this.pageNo < totalPages && this.pageNo < 20) {
        this.pageNo++;
        this.fetchData();
      }
      else {
        alert("다음 페이지가 없습니다")
      }

    },
    goToPage(pageNumber) {
      this.pageNo = pageNumber;
      this.fetchData();
    },

    updateDisplayedPages() {
      const maxDisplayedPages = 10; // 한 번에 표시할 페이지 버튼의 최대 개수
      const halfMaxDisplayedPages = Math.floor(maxDisplayedPages / 2);
      let startPage = this.pageNo - halfMaxDisplayedPages;
      let endPage = this.pageNo + halfMaxDisplayedPages;

      if (startPage < 1) {
        startPage = 1;
        endPage = Math.min(maxDisplayedPages, this.totalPages);
      }
      if (endPage > this.totalPages) {
        endPage = this.totalPages;
        startPage = Math.max(1, endPage - maxDisplayedPages + 1);
      }

      this.startPage = startPage;
      this.endPage = endPage;
      this.displayedPages = Array.from(
        { length: endPage - startPage + 1 },
        (_, i) => startPage + i
      );
    }
  },
};
</script>


<style>
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
}</style>