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
                <th style="padding-left: 10px">{{ list.sexCd }}</th>
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
                <th style="padding-left: 10px">{{ list.neuterYn }}</th>
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
    
    <table style="text-align: left; margin-bottom:50px; margin-left: 280px; width: 910px" border="1px" >
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9;">이름</th>
                <th style="padding-top: 5px;padding-left: 5px;">{{ list.id.name }}</th>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양신청일</th>
                <th style="padding-top: 5px;padding-left: 5px;"> {{ list.wdate }}</th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9" colspan="1">반려 동물 여부</th>
                <th style="padding-top: 5px;padding-left: 5px;" colspan="3">{{ list.another }}</th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">입양 계기</th>
                <th style="padding-top: 5px;padding-left: 5px;" colspan="3">{{ list.reason }}</th>
            </tr>
            <tr>
                <th style="padding-top: 5px;padding-left: 5px; background-color:#bab9b9">앞으로의 다짐</th>
                <th style="padding-top: 5px;padding-left: 5px;" colspan="3">{{ list.feeding }}</th>
            </tr>
        </table>
</div>
</template>
    
<script>
export default {
    name: 'ApplyDetail',
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




    }
}
</script>
    
    <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
}

#map {
    width: 300px;
    height: 300px;
}

#block {
    border: #42b983;
}


</style>
    