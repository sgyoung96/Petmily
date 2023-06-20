<template>
  <div>
    <h2>선택된 항목</h2>
    <p>desertionNo: {{ selectedItem.desertionNo }}</p>
    <p>filename: {{ selectedItem.filename }}</p>
    <p>happenDt: {{ selectedItem.happenDt }}</p>
    <p>Happen Place: {{ selectedItem.happenPlace }}</p>
    <p>kindCd: {{ selectedItem.kindCd }}</p>
    <p>colorCd: {{ selectedItem.colorCd }}</p>
    <p>age: {{ selectedItem.age }}</p>
    <p>weight: {{ selectedItem.weight }}</p>
    <p>noticeNo: {{ selectedItem.noticeNo }}</p>
    <p>noticeSdt: {{ selectedItem.noticeSdt }}</p>
    <p>noticeEdt: {{ selectedItem.noticeEdt }}</p>
    <p>processState: {{ selectedItem.processState }}</p>
    <p>sexCd: {{ selectedItem.sexCd }}</p>
    <p>neuterYn: {{ selectedItem.neuterYn }}</p>
    <p>specialMark: {{ selectedItem.specialMark }}</p>
    <p>careNm: {{ selectedItem.careNm }}</p>
    <p>careTel: {{ selectedItem.careTel }}</p>
    <p>careAddr: {{ selectedItem.careAddr }}</p>
    <p>orgNm: {{ selectedItem.orgNm }}</p>
    <p>chargeNm: {{ selectedItem.chargeNm }}</p>
    <p>officetel: {{ selectedItem.officetel }}</p>
</div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'DetailsItem',
  data() {
  return {
    selectedItem: {},
  };
},

created() {
  const desertionNo = this.$route.params.desertionNo;
  this.fetchData(desertionNo);
},
  methods: {
    fetchData(desertionNo) {
  axios
    .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic', {
      params: {
        _type: 'json',
        pageNo: 1,
        numOfRows: 24,
        serviceKey: 'JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D',
        desertionNo: desertionNo,
      },
    })
    .then((response) => {
      const data = response.data;
      const items = data.response.body.items.item;
      this.selectedItem = items.find((item) => item.desertionNo === desertionNo);
    })
    .catch((error) => {
      console.error(error);
    });
}}
};
</script>