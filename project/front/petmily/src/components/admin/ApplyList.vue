<template>
  <div>
    <div>

      <div class="form-container">

        <div class="list-container">
          <p style="padding-right:30px"><label class="section01-title"><u><span>입양신청명단</span></u></label></p>
          <div class="section01-content">
            <div class="data-header">
              <table>
                <tr aria-colspan="12">
                  <th colspan="1">
                    <label><span>NO</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>ID</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>NAME</span></label>
                  </th>
                  <th colspan="5">
                    <label><span>REASON</span></label>
                  </th>
                  <th colspan="2">
                    <label><span>DATE</span></label>
                  </th>
                  <th colspan="1">
                    <label><span>STATE</span></label>
                  </th>
                </tr>
                <tr class="clickable-row" v-for="(apply, index) in pagedData" :key="index"
                  :aria-colspan="tableColumns.length" @click="goDetail(apply.num)" style="cursor: pointer;">
                  <td colspan="1" :id="index"><label><span>{{ index + 1 }}</span></label></td>
                  <td colspan="1"><label><span>{{ apply.id.id }}</span></label></td>
                  <td colspan="1"><label><span>{{ apply.id.name }}</span></label></td>
                  <td colspan="5" width="450px"><label><span>{{ apply.reason }}</span></label></td>
                  <td colspan="2"><label><span>{{ apply.wdate.split('T')[0] }}</span></label></td>
                  <td colspan="1">
                    <label>
                      <span v-if="apply.ischeck == 0">요청</span>
                      <span v-else-if="apply.ischeck == 1">승인</span>
                      <span v-else-if="apply.ischeck == 2">반려</span>
                      <span v-else>오류</span>
                    </label>
                  </td>
                </tr>
              </table>
            </div>
            <ul class="pagination" style="display: inline-block; padding-top:40px">
              <!-- 이전 페이지 버튼 -->
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous" @click="previousPage">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <!-- 페이지 번호 -->
              <li class="page-item" v-for="pageNumber in totalPages" :key="pageNumber"
                :class="{ active: pageNumber === currentPage }">
                <a class="page-link" href="#" @click="goToPage(pageNumber)">{{ pageNumber }}</a>
              </li>
              <!-- 다음 페이지 버튼 -->
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next" @click="nextPage">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminAppliedList',
  data() {
    return {
      list: [],
      currentPage: 1,
      pageSize: 5, // 페이지당 데이터 개수 설정
      tableColumns: [],
      searchTerm: '' // 검색어
    }
  },
  created() {
    const self = this;
    self.$axios.get('http://localhost:8082/Applyform/getAllbyischeck')
      .then(function (res) {
        if (res.status === 200) {
          self.list = res.data.list;
        } else {
          alert('에러코드' + res.status)
        }
      });
  },
  computed: {
    totalPages() {
      return Math.ceil(this.list.length / this.pageSize);
    },
    pagedData() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.list.slice(start, end);
    },
  },
  methods: {
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
    setData() { // 데이터 가공

    },
    goDetail(num) {
      console.log(num);
      this.$router.push({ name: 'ApplyDetail', query: { num: num } });
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.pagination {
  display: inline-block;
  margin-top: 20px;
  margin-bottom: 20px;
}

.page-item {
  display: inline-block;
  margin-right: 5px;
}

.page-item a {
  color: black;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  text-decoration: none;
}

.page-item a:hover {
  background-color: #f2f2f2;
}

.page-item.active a {
  background-color: rgb(244, 191, 79);
  color: white;
}
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

