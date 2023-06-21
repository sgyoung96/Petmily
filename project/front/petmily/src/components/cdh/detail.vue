<template>
  <div id="DetailsItem">
    <table border="1px">
      <thead>
        <tr>
          <th>filename</th>
          <th>Care Name</th>
          <th>happenDt</th>
          <th>happenPlace</th>
          <th>kindCd</th>
          <th>colorCd</th>
          <th>age</th>
          <th>weight</th>
          <th>noticeNo</th>
          <th>noticeSdt</th>
          <th>noticeEdt</th>
          <th>processState</th>
          <th>sexCd</th>
          <th>neuterYn</th>
          <th>specialMark</th>
          <th>careNm</th>
          <th>careTel</th>
          <th>careAddr</th>
          <th>orgNm</th>
          <th>chargeNm</th>
          <th>officetel</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in filteredItems" :key="item.desertionNo">
          <td>
            <img :src="item.filename" :alt="item.careNm" />
          </td>
          <td>{{ item.careNm }}</td>
          <td>{{ item.happenDt }}</td>
          <td>{{ item.happenPlace }}</td>
          <td>{{ item.kindCd }}</td>
          <td>{{ item.colorCd }}</td>
          <td>{{ item.age }}</td>
          <td>{{ item.weight }}</td>
          <td>{{ item.noticeNo }}</td>
          <td>{{ item.noticeSdt }}</td>
          <td>{{ item.noticeEdt }}</td>
          <td>{{ item.processState }}</td>
          <td>{{ item.sexCd }}</td>
          <td>{{ item.neuterYn }}</td>
          <td>{{ item.specialMark }}</td>
          <td>{{ item.careNm }}</td>
          <td>{{ item.careTel }}</td>
          <td>{{ item.careAddr }}</td>
          <td>{{ item.orgNm }}</td>
          <td>{{ item.chargeNm }}</td>
          <td>{{ item.officetel }}</td>
        </tr>
      </tbody>
    </table>
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
        .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=240&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
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