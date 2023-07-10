<template>
  <div>
    <div>

      <div class="form-container">

        <div class="list-container">
          <p><label class="section01-title"><u>
            <span>봉사모집게시판현황</span>
            <button @click="$router.push('/volboardadd')" style="font-size: 17px; text-decoration:none; margin-left:800px;">글등록</button>
            </u></label></p>
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="11">
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
                </tr>
                <tr v-for="vboard in list" :key="vboard.num" @click="detail(vboard.num, vboard.address)">
                  <td colspan="1"><label><span class="badge text-bg-danger" style="font-size: 17px;"
                        v-if="calculateDateDifference(vboard.deadline).difference < 0">모집마감</span>
                      <span class="badge text-bg-primary" style="font-size: 17px;" v-else>마감 D-{{
                        calculateDateDifference(vboard.deadline).days }}</span>&nbsp;</label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ vboard.place }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">({{ vboard.count }} / {{ vboard.vol_number }})</span></label></td>
                  <td colspan="2"><label><span style=" cursor: pointer;">{{ vboard.title }}</span></label></td>
                  <td colspan="3"><label><span style=" cursor: pointer;">{{ vboard.address }}</span></label></td>
                  <td colspan="2"><label><span style=" cursor: pointer;">{{ formatDate(vboard.vol_date) }}</span></label></td>
                  <td colspan="1"><label><span style=" cursor: pointer;">{{ formatDate(vboard.deadline) }}까지</span></label></td>
                  <button v-if="this.loginId==='admin'" @click="del(dto.num)" style="font-size: 17px;">삭제</button>
                </tr>
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
  name: 'VolList',
  data() {
    return {
      loginId: sessionStorage.getItem('loginId'),
      list: [],
      count: 0,
      state: '',
      sysdate: new Date(),
      currentPage: 1,
      pageSize: 8,
      searchKeyword: '',
      list2: [],
      participants: [],
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.list.length / this.pageSize);
    },
    paginatedList() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.list.slice(startIndex, endIndex);
    }
  },
  methods: {
    del: function(num) {
  const self = this;
  self.$axios.delete('http://localhost:8082/volboard/' + num)
    .then(function (res) {
      if(res.status == 200){
        if(res.data.flag){
          alert('삭제가 정상적으로 되었습니다');
          self.$router.push({ name: 'VolBoardHome' });
        }else{
          alert('삭제 처리 에러');
        }
      }else{
        alert('에러코드: '+ res.status);
      }
    })
},
    search() {
      const self = this
      let address = self.searchKeyword
      self.$axios.get('http://localhost:8082/volboard/address/' + address)
        .then(function (response) {
          if (response.status == 200) {
            self.list = response.data.list
          } else {
            alert('에러')
          }
        })
    },
    detail(num, address) {
      this.$router.push({
        name: 'VolBoardDetail',
        query: { num: num, address: address },
      })
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
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    goToPage(pageNumber) {
      this.currentPage = pageNumber;
    },
    getParticipants(num) {
      const self = this;
      self.$axios.get('http://localhost:8082/participants/' + num)
        .then(function (res) {
          if (res.status === 200) {
            let list2 = res.data.list;
            if (list2 === null) {
              alert('참가자 없음');
            } else {
              return list2;
            }
          } else {
            alert('에러');
          }
        });
    }
  },
  created: function () {
    this.loginId = sessionStorage.getItem('loginId')
    const self = this
    self.$axios.get('http://localhost:8082/volboard').then(function (res) {
      if (res.status == 200) {
        console.log(res);
        self.list = res.data.list
      } else {
        alert('에러')
      }
    })
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

button{
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 10px;
  text-align: center;
  vertical-align: center;
  width: 80px;
  height: 30px;
  background: rgb(244, 191, 79);
  color: white;
  border-radius: 10px;
  border:none;
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

.v-title {
  margin-top: 120px;
  margin-bottom: 120px;
}

.vbody {
  border-bottom: 1px solid #666666;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  cursor: pointer;
}

.vcount {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 70px;
  height: 70px;
  border: 2px solid #666666;
  font-size: 15px;
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
}

.vtitle {
  font-size: 30px;
}

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}</style>