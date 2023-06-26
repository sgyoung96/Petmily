<template>
  <div id="DetailsItem" style="background-color:#e5e7eb;">
    <div>
      <div class="container text-center">
        <div class="row" v-for="item in filteredItems" :key="item.desertionNo">
          <div class="col-1">
          </div>
          <div class="col-10" style="border: solid #e5e7eb; margin-top: 100px; border-radius: 20px;">
            <div style="margin-left: -12px;">
              <img :src="item.popfile" :alt="item.careNm" style="width: 1095px; height: 400px; border-radius: 20px;">
            </div>
          </div>
          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h3>유기동물정보</h3>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div>
                  <h3>품종</h3>
                  <span>{{ item.kindCd }}</span>
                </div>
                <div>
                  <h3>나이</h3>
                  <span>{{ item.age }}</span>
                </div>
                <div>
                  <h3>체중</h3>
                  <span>{{ item.weight }}</span>
                </div>
                <div>
                  <h3>상태</h3>
                  <span>{{ item.processState }}</span>
                </div>
              </div>
              <div style="flex: 1;">
                <div>
                  <h3>성별</h3>
                  <span v-if="item.sexCd === 'M'">수컷</span>
                  <span v-else-if="item.sexCd === 'F'">암컷</span>
                  <span v-else>몰루</span>
                </div>
                <div>
                  <h3>색상</h3>
                  <span>{{ item.colorCd }}</span>
                </div>
                <div>
                  <h3>중성화</h3>
                  <span v-if="item.neuterYn === 'Y'">네</span>
                  <span v-else>아니요</span>
                </div>
              </div>
            </div>
            <span>특별사항 : {{ item.specialMark }}</span>
          </div>

          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h3>구조정보</h3>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div>
                  <h3>접수일시</h3>
                  <span>{{ item.happenDt }}</span>
                </div>
              </div>
              <div style="flex: 1;">
                <div>
                  <h3>발견장소</h3>
                  <span>{{ item.happenPlace }}</span>
                </div>
              </div>
            </div>
            <span>지도위치</span>
          </div>

          <div class="col-1">
          </div>
          <div class="col-10"
            style="border: solid #e5e7eb; border-radius: 20px; margin-top: 50px; position: relative; margin-left: 110px; text-align: left; background-color:white;">
            <h3>동물보호센터</h3>
            <div style="display: flex;">
              <div style="flex: 1;">
                <div>
                  <h3>보호센터명</h3>
                  <span>{{ item.careNm }}</span>
                </div>
                <div>
                  <h3>보호장소</h3>
                  <span>{{ item.careAddr }}</span>
                </div>
                <div>
                  <h3>담당자</h3>
                  <span>{{ item.orgNm }}</span>
                </div>
              </div>
              <div style="flex: 1;">
                <div>
                  <h3>보호소전화번호</h3>
                  <span>{{ item.careTel }}</span>
                </div>
                <div style="margin-top: 65px;"> <!-- 간격을 띄우는 div 요소 -->
                </div>
                <div>
                  <h3>담담자전화번호</h3>
                  <span>{{ item.officetel }}</span>
                </div>
              </div>
            </div>
            <span>지도위치</span>
          </div>
        </div>
      </div>
    </div>
  </div>
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
        .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=500&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
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

<style>
.grid-container {
  display: grid;
  margin-left: 150px;
  margin-right: 200px;
  position: relative;
  grid-template-columns: repeat(1, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 10px;
}

.grid-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
</style>

<div class="container text-center">
  <div class="row">
    <div class="col-1">
    </div>
<div class="col-10" style="border: solid green">
      <div v-if="dto.id" style="border-bottom:solid green">
      {{dto.title}}{{ dto.id.id }}{{ dto.w_date }}
      </div>
<div style="border-bottom: solid green">
      <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/1'">
      <img :src="'http://localhost:8082/dboard/imgs/' + dto.num + '/2'">
      </div>
<div style="padding:50px; border-bottom:solid green">
        {{ dto.content }}
      </div>  
<div style="float:right">
        <router-link to="/diaryboardedit">수정하기</router-link>
     <button v-on:click="boarddelete">삭제하기</button>
    </div><br/>