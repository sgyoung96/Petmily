<template>
  <div>
    <div>

      <div class="form-container">

        <div class="list-container">
          <!-- <p><label class="section01" >SECTION 1</label></p>
          <p><label class="section01-title"><u><span>간편 승인/반려 영역</span></u></label></p> -->
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
                  <th colspan="1"> 
                    <label><span>APPROVE</span></label> |
                    <label><span>REFUSE</span></label>
                  </th>
                </tr>
                <tr v-for="(apply, index) in list" :key="index" aria-colspan="12" @click="goDetail(index, apply.num)">
                  <td colspan="1" :id="index"><label><span>{{ index + 1 }}</span></label></td>
                  <td colspan="1"><label><span>{{ apply.id.id }}</span></label></td>
                  <td colspan="1"><label><span>{{ apply.id.name }}</span></label></td>
                  <td colspan="5" width="450px"><label><span>{{ apply.reason }}</span></label></td>
                  <td colspan="2"><label><span>{{apply.wdate.split('T')[0]}}</span></label></td>
                  <td colspan="1"><label><span v-if="apply.ischeck == 0">요청</span><span v-else-if="apply.ischeck == 1">승인</span><span v-else-if="apply.ischeck==2">반려</span><span v-else>오류</span></label></td>
                  <td colspan="1">
                    <button @click="apply_form(apply.num)" style="background-color:#FFD65B; border-radius:10px;">승인</button>
                    <button @click="refuse_form(apply.num)" style="background-color:#FFD65B; border-radius:10px;">거부</button>
                  </td>
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
  name: 'AdminAppliedList',
  data() {
    return {
      list: [],
      num: '',
      id: '',
      wdate: '',
      agreement: '',
      another: '',
      reason: '',
      feeding: '',
      ischeck: '',
      petCd: '',

      index: '',
    }
  },
  created: function () {
    const self = this;
    self.$axios.get('http://localhost:8082/Applyform')
      .then(function (res) {
        if (res.status == 200) {
          self.list = res.data.list;
          self.setData();
        } else {
          alert('에러코드' + res.status)
        }
      });
  },
  methods: {
    apply_form(num) {
      console.log(num);
      const self = this;
      //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
      self.$axios.patch('http://localhost:8082/Applyform/apply/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.list = res.data.list;
          } else {
            alert('에러코드' + res.status);
          }
        });
    },
    refuse_form(num) {
      console.log(num);
      const self = this;
      //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
      self.$axios.patch('http://localhost:8082/Applyform/refuse/' + num)
        .then(function (res) {
          if (res.status == 200) {
            self.list = res.data.list;
          } else {
            alert('에러코드' + res.status);
          }
        });
    },
    setData() { // 데이터 가공
   
    },
    goDetail(rowIndex, formNum) {
      console.log(rowIndex, formNum);
    }
  },
  components: {

  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
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

</style>

