<template>
    <div class="base-info-container">
        <div class="base-info-pet">
            <p>입양하실 아가의 기본정보예요.</p>
            <table v-for="list in list" :key="list.num" style="text-align: left; margin-left: 300px;">
                <tr>
                    <th rowspan="7"><img :src="list.popfile"
                            style="width: 300px; height: 300px; border-radius: 20px; margin-left:-10px; padding-right:10px">
                    </th>
                    <th>견종</th>
                    <th style="padding-left: 10px">{{ list.kindCd }}</th>
                </tr>
                <tr>
                    <th>성별</th>
                    <th class="g-color-black" style="padding-left: 10px" v-if="list.sexCd === 'M'">수컷</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="list.sexCd === 'F'">암컷</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else>미상</th>
                </tr>
                <tr>
                    <th>나이</th>
                    <th style="padding-left: 10px">{{ list.age }}</th>
                </tr>
                <tr>
                    <th>색</th>
                    <th style="padding-left: 10px">{{ list.colorCd }}</th>
                </tr>
                <tr>
                    <th>중성화여부</th>
                    <th class="g-color-black" style="padding-left: 10px" v-if="list.neuterYn === 'Y'">중성화O</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="list.neuterYn === 'N'">중성화X</th>
                    <th class="g-color-black" style="padding-left: 10px" v-else-if="list.neuterYn === 'U'">미상</th>
                </tr>
                <tr>
                    <th>동물보호센터명</th>
                    <th style="padding-left: 10px">{{ list.careNm }}</th>
                </tr>
                <tr>
                    <th>보호장소</th>
                    <th style="padding-left: 10px">{{ list.careAddr }}</th>
                </tr>
            </table>
        </div>
    </div>
    <div style="margin-top: 30px;">

    </div>
    <div v-for="list in list" :key="list.num">
        <p>{{ list.id.id }}님의 입양신청 양식</p>

        <table style="text-align: left; margin-bottom:50px; margin-left: 280px; width: 910px" border="1px">
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; width: 130px; background-color:#bab9b9;">이름</th>
                <th style="padding-top: 5px;padding-left: 5px;">{{ list.id.name }}</th>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양신청일</th>
                <th style="padding-top: 5px;padding-left: 5px;"> {{ list.wdate }}</th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9" colspan="1">반려 동물 여부</th>
                <th><input type="text" style="width: 100%; padding-left: 5px; border:0px; font-family: 'IBMPlexSansKR-Bold';" v-model="list.another">
                </th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양 계기</th>
                <th style="padding-top: 5px;padding-left: 5px; font-family: 'IBMPlexSansKR-Bold';;" colspan="3">
                    <textarea style="border:0px; width: 100%;" v-model="list.reason"></textarea>
                </th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">앞으로의 다짐</th>
                <th style="padding-top: 5px;padding-left: 5px; font-family: 'IBMPlexSansKR-Bold';" colspan="3" >
                    <textarea style="border:0px; width: 100%;" v-model="list.feeding"></textarea>
                </th>
            </tr>
        </table>
    </div>
    <button @click="edit_form(list.num)" style="background-color:#FFD65B; border-radius:10px; ">수정</button>
    <button @click="delete_form(list.num)" style="background-color:#FFD65B; border-radius:10px; ">삭제</button>
</template>
    
<script>
export default {
    name: 'AppliedDetail',
    data() {
        return {
            list: [],
        }
    },
    created: function () {
        this.$data.num = this.$route.query.num;
        this.fetchApplyFormData();
    },  
    methods: {
        fetchApplyFormData() {
            const self = this; // self 변수에 컴포넌트 인스턴스를 할당
            const num = this.$data.num;
            self.$axios
                .get('http://localhost:8082/Applyform/getbynum/' + num) // 변경된 URL로 변경 필요
                .then(function (res) {
                    if (res.status == 200) {
                        self.list = res.data.list;
                    } else {
                        alert('에러코드' + res.status)
                    }
                });
        },
        edit_form(num) {

            let formdata = new FormData();
            console.log(num);
            const self = this;
            
            formdata.append('id', sessionStorage.getItem('loginId'));
            formdata.append('wdate', self.wdate);
            formdata.append('agreement', self.agreement);
            formdata.append('another', self.another);
            formdata.append('reason', self.reason);
            formdata.append('feeding', self.feeding);
            formdata.append('ischeck', self.ischeck);
            formdata.append('kindCd', self.kindCd);
            formdata.append('sexCd', self.sexCd);
            formdata.append('age', self.age);
            formdata.append('colorCd', self.colorCd);
            formdata.append('neuterYn', self.neuterYn);
            formdata.append('careNm', self.careNm);
            formdata.append('careAddr', self.careAddr);
            formdata.append('popfile', self.popfile);

            //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
            self.$axios.put('http://localhost:8082/Applyform/' + num + formdata)
                .then(function (res) {
                    if (res.status == 200) {
                        self.list = res.data.list;
                    } else {
                        alert('에러코드' + res.status);
                    }
                });
        },

        delete_form(num) {
            console.log(num);
            const self = this;
            //const applyNum = self.list.apply.num; // apply.num 값을 가져옴
            self.$axios.delete('http://localhost:8082/Applyform/' + num)
                .then(function (res) {
                    if (res.status == 200) {
                        self.list = res.data.list;
                    } else {
                        alert('에러코드' + res.status);
                    }
                });
        },



    }
}
</script>
    
    <!-- Add "scoped" attribute to limit CSS to this component only -->
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
</style>
    