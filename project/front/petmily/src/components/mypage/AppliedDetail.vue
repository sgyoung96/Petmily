<template>
    <div class="base-info-container">
        <div class="base-info-pet">
            <p>입양하실 아가의 기본정보예요.</p>
            <table style="text-align: left; margin-left: 300px;">
                <tr>
                    <th rowspan="7"><img :src="popfile"
                            style="width: 300px; height: 300px; border-radius: 20px; margin-left:-10px; padding-right:10px">
                    </th>
                    <th>견종</th>
                    <th style="padding-left: 10px">{{ kindCd }}</th>
                </tr>
                <tr>
                    <th>성별</th>
                    <th class="g-color-black" style="padding-left: 10px" v-if="sexCd === 'M'">수컷</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="sexCd === 'F'">암컷</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else>미상</th>
                </tr>
                <tr>
                    <th>나이</th>
                    <th style="padding-left: 10px">{{ age }}</th>
                </tr>
                <tr>
                    <th>색</th>
                    <th style="padding-left: 10px">{{ colorCd }}</th>
                </tr>
                <tr>
                    <th>중성화여부</th>
                    <th class="g-color-black" style="padding-left: 10px" v-if="neuterYn === 'Y'">중성화O</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="neuterYn === 'N'">중성화X</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="neuterYn === 'U'">미상</th>
                </tr>
                <tr>
                    <th>동물보호센터명</th>
                    <th style="padding-left: 10px">{{ careNm }}</th>
                </tr>
                <tr>
                    <th>보호장소</th>
                    <th style="padding-left: 10px">{{ careAddr }}</th>
                </tr>
            </table>
        </div>
    </div>
    <div style="margin-top: 30px;"></div>
    <div>
        <p>{{ id }}님의 입양신청 양식</p>

        <table style="text-align: left; margin-bottom:50px; margin-left: 280px; width: 910px" border="1px">
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; width: 130px; background-color:#bab9b9;">이름</th>
                <th style="padding-top: 5px;padding-left: 5px;">{{ name }}</th>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양신청일</th>
                <th style="padding-top: 5px;padding-left: 5px;"> {{ wdate }}</th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9" colspan="1">반려 동물 여부</th>
                <th colspan="3"><input type="text"
                        style="width: 100%; padding-left: 5px; border:0px; font-family: IBMPlexSansKR-Bold;"
                        v-model="another">
                </th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양 계기</th>
                <th style="padding-top: 5px;padding-left: 5px; font-family: 'IBMPlexSansKR-Bold';" colspan="3">
                    <textarea style="border:0px; width: 100%;" v-model="reason"></textarea>
                </th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">앞으로의 다짐</th>
                <th style="padding-top: 5px;padding-left: 5px; font-family: 'IBMPlexSansKR-Bold';" colspan="3">
                    <textarea style="border:0px; width: 100%;" v-model="feeding"></textarea>
                </th>
            </tr>
        </table>
    </div>
    <button @click="edit_form()" class="txt-form">수정</button>
    <button @click="delete_form(num)" class="txt-form">삭제</button>
</template>

<script>
export default {
    name: 'ApplyDetail',
    data() {
        return {
            num: '',
            id: '',
            name: '',
            wdate: '',
            another: '',
            reason: '',
            feeding: '',
            kindCd: '',
            sexCd: '',
            age: '',
            colorCd: '',
            neuterYn: '',
            careNm: '',
            careAddr: '',
            popfile: ''
        }
    },
    created: function () {
        this.$data.num = this.$route.query.num;
        this.fetchApplyFormData();
    },
    methods: {
        fetchApplyFormData() {
            const self = this;
            const num = this.$data.num;
            self.$axios
                .get('http://localhost:8082/Applyform/getbynum/' + num)
                .then(function (res) {
                    if (res.status == 200) {
                        const data = res.data;
                        self.num = data.num;
                        self.id = data.id.id;
                        self.name = data.id.name;
                        self.wdate = data.wdate;
                        self.another = data.another;
                        self.reason = data.reason;
                        self.feeding = data.feeding;
                        self.kindCd = data.kindCd;
                        self.sexCd = data.sexCd;
                        self.age = data.age;
                        self.colorCd = data.colorCd;
                        self.neuterYn = data.neuterYn;
                        self.careNm = data.careNm;
                        self.careAddr = data.careAddr;
                        self.popfile = data.popfile;
                    } else {
                        alert('에러코드' + res.status)
                    }
                });
        },

        edit_form() {
            const self = this;
            let formdata = new FormData();
            formdata.append('num', self.num);
            formdata.append('another', self.another);
            formdata.append('reason', self.reason);
            formdata.append('feeding', self.feeding);

            if (self.another == '') {
            alert("반려동물여부가 비었습니다")
            return;
          } else {
          if (self.reason == '') {
            alert("입양신청 계기가 비었습니다")
            return;
          }  else {
          if (self.feeding == '') {
            alert("앞으로의 다짐이 비었습니다")
            return;
          } else{
            self.$axios.put(`http://localhost:8082/Applyform` , formdata, {
                    headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (res) {
                    if (res.status == 200) {
                        self.$router.go(-1);
                    } else {
                        alert('에러코드 ' + res.status);
                    }
                });
            }}}
        },


        delete_form(num) {
            console.log(num);
            const self = this;
            //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
            self.$axios.delete('http://localhost:8082/Applyform/' + num)
                .then(function (res) {
                    if (res.status == 200) {
                        self.$router.go(-1);
                    } else {
                        alert('에러코드' + res.status);
                    }
                });
        },
    }
}
</script>

<style scoped>
h3 {
    margin: 40px 0 0;
    font-family: 'IBMPlexSansKR-Bold';
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
}

#map {
    width: 300px;
    height: 300px;
}

#block {
    border: #42b983;
    
}

.txt-form {
  width: 50px;
  height: 30px;
  font-family: 'IBMPlexSansKR-Bold';
  font-size: 18px;
  background-color: rgb(244, 191, 79);
  border-radius: 30px;
  color: white;
  cursor: pointer;
  border-color: white;
}</style>