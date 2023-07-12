<template>
  <div>
    <div>
      <div class="form-container">
        <div class="list-container">
          <span style="font-weight:600; font-size:24px; margin-bottom: 10px;">분양게시판</span>
          <!-- <p><label class="section01" >SECTION 1</label></p>
            <p><label class="section01-title"><u><span>간편 승인/반려 영역</span></u></label></p> -->
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="8">
                  <th colspan="1">
                    <label><span>NO</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Writer's ID</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Title</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Species</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Gender</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Date created</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Address</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Like'CNT</span></label>
                  </th>
                </tr>
                <template v-if="alist.length">
                  <tr class="clickable-row" v-for="adopt in alist" :key="adopt.num" aria-colspan="8"
                    style="cursor: pointer;" @click="detail3(adopt.num)">
                    <td colspan="1" :id="index"><label><span>{{ adopt.num }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.id.id }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.title }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.category }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.gender }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.w_date }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.address }}</span></label></td>
                    <td colspan="1"><label><span>{{ adopt.likecnt }}</span></label></td>
                  </tr>
                </template>
                <template v-else>
                  <tr>
                    <td colspan="8"><label><span>작성한 글이 없습니다...</span></label></td>
                  </tr>
                </template>
              </table>

            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div class="form-container">
        <div class="list-container">
          <span style="font-weight:600; font-size:24px; margin-bottom: 10px;">입양일지</span>
          <!-- <p><label class="section01" >SECTION 1</label></p>
            <p><label class="section01-title"><u><span>간편 승인/반려 영역</span></u></label></p> -->
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="5">
                  <th colspan="1">
                    <label><span>NO</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>TITLE</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Writer'ID</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Date created</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>Like'CNT</span></label>
                  </th>
                </tr>
                <template v-if="dlist.length">
                <tr class="clickable-row" v-for="diary in dlist" :key="diary.num" aria-colspan="5"
                  style="cursor: pointer;" @click="detail2(diary.num)">
                  <td colspan="1" :id="index"><label><span>{{ diary.num }}</span></label></td>
                  <td colspan="1"><label><span>{{ diary.title }}</span></label></td>
                  <td colspan="1"><label><span>{{ diary.id.id }}</span></label></td>
                  <td colspan="1"><label><span>{{ diary.w_date }}</span></label></td>
                  <td colspan="1"><label><span>{{ diary.likecnt }}</span></label></td>
                </tr>
                </template>
                <template v-else>
                  <tr>
                    <td colspan="8"><label><span>작성한 글이 없습니다...</span></label></td>
                  </tr>
                </template>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'SavedList',
  data() {
    return {
      id: sessionStorage.getItem('loginId'),
      alist: [],
      dlist: [],
      wlist: [],
      sysdate: new Date(),
    }
  },
  created: function () {
    this.getAdoptBoard();
    this.getDiaryBoard();
    this.getWatchList();
  },
  methods: {
    getAdoptBoard() {
      const self = this
      self.$axios.get('http://localhost:8082/adopt/id2/' + this.id)
        .then(function (res) {
          if (res.status == 200) {
            self.alist = res.data.list
          }
        })
    },
    getDiaryBoard() {
      const self = this
      self.$axios.get('http://localhost:8082/dboard/id/' + this.id)
        .then(function (res) {
          if (res.status == 200) {
            self.dlist = res.data.list
          }
        })
    },
    getWatchList(){
      const self = this
      self.$axios.get('http://localhost:8082/watchlist/id2/' + this.id)
      .then(function (res) {
          if (res.status == 200) {
            self.wlist = res.data.list
          }
        })
    },
    detail(num, address) {
      this.$router.push({
        name: 'VolBoardDetail',
        query: { num: num, address: address },
      })
    },
    detail2(num){
      this.$router.push({ name: 'DiaryBoardDetail', query: { num: num } })
    },
    detail3(num){
      this.$router.push({ name: 'AdoptDetail', query: { num: num } });
      const self = this;
      self.$axios.get('http://localhost:8082/adopt/cnt/' + num).then(function (res) {
      if (res.status === 200) {
        console.log(res.data.flag);
        // 윈도우 팝업 창 열기
    } else {
      alert('에러');
    }
  });
    },
    calculateDateDifference(deadline) {
      const deadlineDate = new Date(deadline)
      const difference = deadlineDate.getTime() - this.sysdate.getTime()
      const days = Math.ceil(difference / (1000 * 60 * 60 * 24))
      return { difference, days }
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
    },
  },
  components: {

  }
}
</script>
  
<style scoped>
.form-container {
  display: block;
  position: relative;
}

.list-container {
  display: block;
  position: relative;
  margin-left: 50px;
  margin-right: 50px;
  padding-top: 40px;
  padding-bottom: 100px;
  border: 5px solid #efefef;
  border-radius: 10px;
  height: auto;
}

.section01 {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 15px;
  text-align: center;
  vertical-align: center;
  width: 120px;
  height: 25px;
  background: rgb(244, 191, 79);
  color: white;
  border-radius: 10px;
}

.section01-content {
  border: 1px solid #efefef;
  border-radius: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.section01-title u span {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
  color: black;
}

.section01-title u {
  text-decoration-color: rgb(244, 191, 79);
  text-decoration-thickness: 3px;
}

.data-header {
  display: flex;
  justify-content: space-between;
  padding-left: 80px;
  padding-right: 80px;
}

table {
  width: 100%;
}

.data-header table tr th {
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 14px;
  color: black;
  text-align: center;
}

.data-header table tr td {
  font-family: 'IBMPlexSansKR-Regular';
  font-size: 14px;
  color: black;
  text-align: center;
}

.data-contents {
  display: flex;
  justify-content: space-between;
  padding-left: 60px;
  padding-right: 80px;
}

.rows {
  cursor: pointer;
}

.clickable-row {
  cursor: pointer;
}
</style>
