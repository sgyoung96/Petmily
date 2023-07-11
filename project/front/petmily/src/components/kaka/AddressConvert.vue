<template>
  <div style="background-color:#5e584a; height:500px;" id="carouselExampleAutoplaying" class="carousel slide">
    <div class="carousel-inner">
      <h3 style="margin-bottom: 30px; margin-top:30px; color:white; font-weight:400; font-size: 36px;">모집중인 자원봉사</h3>
      <div v-for="(vboardGroup, index) in groupedItems" :key="index" class="carousel-item" :class="{ 'active': index === 0 }">
        <div class="d-flex">
          <div v-for="(vboard, innerIndex) in vboardGroup" :key="innerIndex" class="mx-auto" :class="{ 'd-none': innerIndex >= 3 }">
            <div style="background-color:white; text-align:left; font-weight: 500; width:240px; height:245px; border-radius: 10px;">
              <div style="width:240px; height:20px; background-color:#789e20; border-top-left-radius:10px; border-top-right-radius:10px;">
              </div>
              <em style="color:#789e20; font-weight:900; font-size:30px;">유기견봉사</em><span style="text-align: right;">({{ slice2(vboard.address) }})</span><br/>
              <a style="padding-left:20px; font-size:17px; font-weight:600;">{{ vboard.title }}</a>
              <dl>
                <dt>모집인원:</dt>
                <dd>{{ vboard.vol_number }}</dd>
              </dl>
              <dl>
                <dt>모집마감일:</dt>
                <dd>{{ formatDate(vboard.deadline) }}</dd>
              </dl>
              <dl style="padding-bottom: 5px;">
                <dt>봉사일자:</dt>
                <dd>{{ formatDate(vboard.vol_date) }}</dd>
              </dl>
            </div>
          </div>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">이전</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">다음</span>
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      currentIndex: 0
    };
  },
  mounted() {},
  computed: {
    groupedItems() {
      const groups = [];
      for (let i = 0; i < this.list.length; i += 3) {
        groups.push(this.list.slice(i, i + 3));
      }
      return groups;
    }
  },
  methods: {
    checkListLength() {
      if (this.list.length === 0) {
        const self = this;
        self.$axios.get('http://localhost:8082/volboard').then(function (res) {
          if (res.status === 200) {
            self.list = res.data.list;
          } else {
            alert('에러');
          }
        });
      }
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      const formattedDate = new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
      const year = formattedDate.slice(0, 4);
      const month = formattedDate.slice(4, 6);
      const day = formattedDate.slice(6, 8);

  return `${year}년 ${month}월 ${day}일`;
    },
    slice2(str){
      return str.substring(0, 2);
    },
    prevSlide() {
      this.currentIndex = (this.currentIndex === 0) ? this.groupedItems.length - 1 : this.currentIndex - 1;
    },
    nextSlide() {
      this.currentIndex = (this.currentIndex === this.groupedItems.length - 1) ? 0 : this.currentIndex + 1;
    }
  },
  created() {
    this.loginId = sessionStorage.getItem('loginId');
    this.checkListLength();
  }
};
</script>

<style scoped>
dl{
  font-size:14px;
  padding-left:8px;
}
</style>