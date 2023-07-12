<template>
<div>
  <div>
      <div class="form-container">
        <div class="list-container">
          <span style="font-weight:600; font-size:24px; margin-bottom: 10px;">봉사모집게시판 관심목록</span>
          <!-- <p><label class="section01" >SECTION 1</label></p>
            <p><label class="section01-title"><u><span>간편 승인/반려 영역</span></u></label></p> -->
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="12">
                  <th colspan="1">
                    <label><span>모집상태</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>모집기관</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>신청인원</span></label>
                  </th>
                  <th colspan="2">
                    <label><span>봉사제목</span></label>
                  </th>
                  <th colspan="3">
                    <label><span>봉사장소</span></label>
                  </th>
                  <th colspan="2">
                    <label><span>봉사날짜</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>모집기간</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>삭제</span></label>
                  </th>
                </tr>
                <template v-if="wlist.length">
                  <tr v-for="vboard in wlist" :key="vboard.num">
                  <td colspan="1"><label><span class="badge text-bg-danger" style="font-size: 17px;"
                        v-if="calculateDateDifference(vboard.num.deadline).difference < 0">모집마감</span>
                      <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
                        calculateDateDifference(vboard.num.deadline).days }}</span>&nbsp;</label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ vboard.num.place }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">({{ vboard.num.count }} / {{ vboard.num.vol_number }})</span></label></td>
                  <td colspan="2"><label><span style=" cursor: pointer;" @click="detail(vboard.num.num, vboard.num.address)">{{ vboard.num.title }}</span></label></td>
                  <td colspan="3"><label><span style=" cursor: pointer;">{{ vboard.num.address }}</span></label></td>
                  <td colspan="2"><label><span style=" cursor: pointer;">{{ formatDate(vboard.num.vol_date) }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ formatDate(vboard.num.deadline) }}까지</span></label></td>
                  <td colspan="1" @click="delWatch(vboard.num.num)"><label><span style=" cursor: pointer;">❌</span></label></td>
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
          <span style="font-weight:600; font-size:24px; margin-bottom: 10px;">{{ this.id }}가 신청한 봉사모집게시판 목록</span>
          <!-- <p><label class="section01" >SECTION 1</label></p>
            <p><label class="section01-title"><u><span>간편 승인/반려 영역</span></u></label></p> -->
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="12">
                  <th colspan="1">
                    <label><span>모집상태</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>모집기관</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>신청인원</span></label>
                  </th>
                  <th colspan="2">
                    <label><span>봉사제목</span></label>
                  </th>
                  <th colspan="3">
                    <label><span>봉사장소</span></label>
                  </th>
                  <th colspan="2">
                    <label><span>봉사날짜</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>모집기간</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>삭제</span></label>
                  </th>
                </tr>
                <template v-if="wlist.length">
                  <tr v-for="vboard in alist" :key="vboard.num">
                  <td colspan="1"><label><span class="badge text-bg-danger" style="font-size: 17px;"
                        v-if="calculateDateDifference(vboard.boardnum.deadline).difference < 0">모집마감</span>
                      <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
                        calculateDateDifference(vboard.boardnum.deadline).days }}</span>&nbsp;</label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ vboard.boardnum.place }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">({{ vboard.boardnum.count }} / {{ vboard.boardnum.vol_number }})</span></label></td>
                  <td colspan="2" @click="detail(vboard.boardnum.num, vboard.boardnum.address)"><label><span style=" cursor: pointer;">{{ vboard.boardnum.title }}</span></label></td>
                  <td colspan="3"><label><span style=" cursor: pointer;">{{ vboard.boardnum.address }}</span></label></td>
                  <td colspan="2"><label><span style=" cursor: pointer;">{{ formatDate(vboard.boardnum.vol_date) }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ formatDate(vboard.boardnum.deadline) }}까지</span></label></td>
                  <td colspan="1" @click="delList(vboard.boardnum.num)"><label><span style=" cursor: pointer;">❌</span></label></td>
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
  name: 'App',
  data() {
    return {
      id: sessionStorage.getItem('loginId'),
      wlist: [],
      alist: [],
      sysdate: new Date(),
    };
  },
  created() {
    this.getWatchList();
    this.getParticipants();
  },
  methods: {
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
    getWatchList(){
      const self = this
      self.$axios.get('http://localhost:8082/watchlist/id2/' + this.id)
      .then(function (res) {
          if (res.status == 200) {
            self.wlist = res.data.list
          }
        })
    },
    getParticipants(){
      const self = this
      self.$axios.get('http://localhost:8082/participants/id2/' + this.id)
      .then(function (res) {
          if (res.status == 200) {
            self.alist = res.data.list
          }
        })
    },
    delList(num){
      const self = this
      self.$axios.delete('http://localhost:8082/participants?boardnum=' + num + '&id=' + self.id)
      .then(function (res) {
        if(res.status == 200){
          console.log(res.data.flag)
          self.getParticipants();
        }
      })
    },
    delWatch(num){
      const self = this
      self.$axios.delete('http://localhost:8082/watchlist?num=' + num + '&id=' + self.id)
      .then(function (res) {
        if(res.status == 200){
          console.log(res.data.flag)
          self.getWatchList();
        }
      })
    },
    detail(num, address) {
      this.$router.push({
        name: 'VolBoardDetail',
        query: { num: num, address: address },
      })
    },

  },


};
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