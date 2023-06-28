<template>
  <div id="p" class="col-1">
  </div>
  <div class="col-10"
    style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 130px; text-align: left; background-color:white;">
    <div>
      <button v-on:click="PlacetoggleButtons" class="custom-button">전체시도조회</button><br>
      <div v-if="showPlaceButtons">
        <div class="place-container">
          <button v-on:click="PlaceAll" class="place-button" style="width:160px; height:40px">전체시도조회</button>
          <div v-for="button in placeButtons" :key="button.label">
            <button v-on:click="button.onClick" class="place-button" style="width:160px; height:40px">{{ button.label }}</button>
          </div>
        </div>
      </div>
    </div>
    <div>
      <button v-on:click="NeutertoggleButtons" class="custom-button">중성화여부</button>
      <div v-if="showNeuterButtons">
        <div class="Neuter-container">
          <button v-on:click="NeuterAll" class="Neuter-button" style="width:160px; height:40px">전체</button>
          <button v-on:click="NeuterY" class="Neuter-button" style="width:160px; height:40px">중성화O</button>
          <button v-on:click="NeuterN" class="Neuter-button" style="width:160px; height:40px">중성화X</button>
          <button v-on:click="NeuterU" class="Neuter-button" style="width:160px; height:40px">중성화?</button>
        </div>
      </div>
    </div>
    <button v-on:click="search" class="custom-button">검색</button>
  </div>
  <div id="app" style="margin-top:50px;">
    <div class="grid-container">
      <div v-for="item in items" :key="item.desertionNo" class="grid-item">
        <div class="card">
          <div>
            <img :src="item.popfile" :alt="item.careNm" @click="handleItemClick(item.desertionNo, item.careAddr)"
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
            <div class="item-info">
              <div id="orgNm">
                {{ item.orgNm }}
              </div>
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
      displayedPages: [], // 현재 표시되는 페이지 버튼
      startPage: 1, // 시작 페이지 번호
      endPage: 10, // 끝 페이지 번호
      careAddr: '',
      showPlaceButtons: false,
      showNeuterButtons: false,
      orgCd: '',
      neuter_yn: 'null'
    };
  },

  created() {
    window.onpopstate = () => {
      // 뒤로 가기 버튼 클릭 시에 실행될 코드를 여기에 작성합니다.
      // 예를 들어, 이전 페이지로 이동하는 코드를 작성할 수 있습니다
    };

    const orgCd = this.$route.query.orgCd;
    const pageNo = this.$route.query.pageNo || 1;
    this.pageNo = parseInt(pageNo);
    this.fetchData(orgCd);
  },

  methods: {
    fetchData(orgCd, neuter_yn) {
  let apiUrl;
  if (orgCd && neuter_yn) {
    apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&upr_cd=${orgCd}&neuter_yn=${neuter_yn}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
  } else if (orgCd) {
    apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&upr_cd=${orgCd}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
  } else if (neuter_yn) {
    apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&neuter_yn=${neuter_yn}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
  } else {
    apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=${this.pageNo}&numOfRows=${this.pageSize}&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
  }

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
    })
        .catch((error) => {
          console.error(error);
        });


      const routeParams = {
        path: 'Api',
        query: {
          orgCd: orgCd,
          
        }
      };
      this.$router.push(routeParams);

      // 장소 데이터 가져오기
      this.place();
    },

    place() {
      const apiUrl = `http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido?_type=json&pageNo=1&numOfRows=100&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D`;
      axios.get(apiUrl)
        .then((response) => {
          const placeData = response.data.response.body;
          const items = placeData.items.item;

          // orgdownNm 값을 추출하여 버튼 배열을 생성합니다
          this.orgdownNms = items.map(item => ({
            orgCd: item.orgCd,
            orgdownNm: item.orgdownNm
          }));

          // placeButtons 배열을 생성하여 각 버튼을 저장합니다
          this.placeButtons = this.orgdownNms.map(org => ({
            label: org.orgdownNm,
            onClick: () => {
              this.orgCd = org.orgCd;
            }
          }));
        })
        .catch((error) => {
          console.error(error);
        });
    },
    NeuterAll() {
      this.neuter_yn = '';
    },
    NeuterY() {
      this.neuter_yn = 'Y';
    },
    NeuterN() {
      this.neuter_yn = 'N';
    },
    NeuterU() {
      this.neuter_yn = 'U';
    },
    PlaceAll() {
      this.orgCd = '';
    },
    search() {
      this.fetchData(this.orgCd, this.neuter_yn);
    },
    PlacetoggleButtons() {
      this.showPlaceButtons = !this.showPlaceButtons; // showPlaceButtons 값을 토글
    },
    NeutertoggleButtons() {
      this.showNeuterButtons = !this.showNeuterButtons; // showNeuterButtons 값을 토글
    },
    handleItemClick(desertionNo) {
      console.log(desertionNo); // desertionNo 값 확인
      this.desertionNo = desertionNo; // desertionNo 값을 설정
      this.$router.push({ name: 'Detail', query: { desertionNo: desertionNo, careAddr: this.careAddr } });
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
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.place-button {
  background-color: #f0cf81;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.Neuter-button {
  background-color: #f0cf81;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.card {
  border-radius: 10px;
  /* 모서리를 둥글게 만듭니다 */
  /* 다른 스타일을 추가로 적용할 수 있습니다 */
}

.place-container {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  padding: 0;

}

.place-container:focus {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  padding: 0;  
}

.Neutered-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  padding: 0;

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

#orgNm {
  display: inline-block;
  margin-right: 10px;
  background-color: rgb(125, 182, 243);
  color: white;
}</style>