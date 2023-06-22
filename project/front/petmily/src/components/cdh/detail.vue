<template>
  <div id="DetailsItem">
    <div>
        <div v-for="item in filteredItems" :key="item.desertionNo">
          <div class="image-wrapper">
            <img :src="item.filename" :alt="item.careNm" style="max-width: 400px; max-height: 400px;" >
          </div>
          <div>
            <h3>유기동물정보</h3>
            <div class="font-secont-title">품종</div>
            <h6>{{ item.kindCd }}</h6>
            <div class="font-secont-title">성별</div>
            <h6>{{ item.sexCd }}</h6>
            <div class="font-secont-title">나이</div>
            <h6>{{ item.age }}</h6>
            <div class="font-secont-title">색상</div>
            <h6>{{ item.colorCd }}</h6>
            <div class="font-secont-title">체중</div>
            <h6>{{ item.weight }}</h6>
            <div class="font-secont-title">중성화여부</div>
            <h6>{{ item.neuterYn }}</h6>
            <div class="font-secont-title">상태</div>
            <h6>{{ item.processState }}</h6>
          </div>
          <div>
          {{ item.careNm }}
          {{ item.happenDt }}
          {{ item.happenPlace }}
          {{ item.kindCd }}
          </div>
          {{ item.colorCd }}
          {{ item.age }}
          {{ item.weight }}
          {{ item.noticeNo }}
          {{ item.noticeSdt }}
          {{ item.noticeEdt }}
          {{ item.processState }}
          {{ item.sexCd }}
          {{ item.neuterYn }}
          {{ item.specialMark }}
          {{ item.careNm }}
          {{ item.careTel }}
          {{ item.careAddr }}
          {{ item.orgNm }}
          {{ item.chargeNm }}
          {{ item.officetel }}
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
.image-wrapper {
  width: 300px;
  height: 200px;
  max-width: 300%;
  max-height: 100%;
}

.image-wrapper img {
  width: 300%;
  height: 300%;
  object-fit: contain; /* 또는 object-fit: cover; */
}
</style>