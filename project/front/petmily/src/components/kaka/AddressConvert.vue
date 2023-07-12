<template>
  <div id="carouselExampleAutoplaying" class="carousel slide">
    <div class="carousel-inner">
      <h3 style="margin-bottom: 30px; margin-top:30px; color:white; font-weight:400; font-size: 36px;">모집중인 자원봉사</h3>
      <div class="container-box">
        <div v-for="(vboardGroup, index) in groupedItems" :key="index" class="carousel-item" :class="{ 'active': index === 0 }">
          <div class="d-flex">
            <div v-for="(vboard, innerIndex) in vboardGroup" :key="innerIndex" class="mx-auto" :class="{ 'd-none': innerIndex >= 3 }">
              <div v-if="calculateDateDifference(vboard.deadline).difference >= 0" @click="goDetail(vboard.num, vboard.address)" style="cursor: pointer; background-color:white; text-align:left; font-weight: 500; width:240px; height:275px; border-radius: 10px;">
                <div style="width:240px; height:20px; background-color:#789e20; border-top-left-radius:10px; border-top-right-radius:10px;">
                </div>
                <div class="box-white" style=" padding-left: 10px; padding-right: 10px; ">
                  <div class="txt-align">
                    <em style="color:#789e20; font-weight:900; font-size:30px;">유기견봉사</em><span style="margin-top: 20px;">({{ slice2(vboard.address) }})</span><br/>
                  </div>
                  <div style="margin-top: 10px; margin-left: 9px;"><a style="font-size:17px; font-weight:600;">{{ vboard.title.length > 12 ? vboard.title.substring(0, 12) + '...' : vboard.title }}</a></div>
                  <dl style="margin-top: 20px;">
                    <dt>모집인원: {{ vboard.vol_number }} </dt>
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
    <div class="more" @click="moreVol()">
      <label>더보기 ></label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      sysdate: new Date(),
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
    calculateDateDifference(deadline) {
      const deadlineDate = new Date(deadline)
      const difference = deadlineDate.getTime() - this.sysdate.getTime()
      const days = Math.ceil(difference / (1000 * 60 * 60 * 24))
      return { difference, days }
    },
    moreVol() {
      this.$router.push('/volboardhome');
    },
    goDetail(num, address) {
      console.log(num + 'address: ' + address);
      const self = this;
      self.$axios.get('http://localhost:8082/volboard/cnt/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.$router.push({
              name: 'VolBoardDetail',
              query: { num: num, address: address },
            })
          }else{
            alert('디테일에러')
          }
        })
    },
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

#carouselExampleAutoplaying {
  display: block;
  position: relative;
  width: 100%;
  background:#5e584a;
  padding-bottom: 130px;
  font-family: 'IBMPlexSansKR-Medium';
}

dl{
  font-size:14px;
  padding-left:8px;
}

h3 {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 25px;
  color: white;
  padding-top: 30px;
}

.container-box {
  margin-top: 80px;
  padding-left: 150px;
  padding-right: 150px;
}

.txt-align {
  display: flex;
  justify-content: space-between;
}

.more {
  font-family: 'IBMPlexSansKR-Regular';
  font-size: 12px;
  color: white;
  padding-top: 30px;
  display: block;
  width: 100%;
  position: relative;
  text-align: right;
  justify-content: right;
  padding-right: 100px;
}

.more > label {
  font-family: 'IBMPlexSansKR-Regular';
  font-size: 12px;
  color: white;
  position: relative;
  width: 100px;
  cursor: pointer;
  z-index: 2;
}
</style>