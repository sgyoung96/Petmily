<template>
    <div class="base-info-container">
        <div class="base-info-pet">
            <div>
                <p>입양하실 아가의 기본정보예요.</p>
                </div>
                <div class="row" v-for="item in filteredItems" :key="item.petCd">
                    <div class="row">
                        <div class="col-3">
                        </div>
                        <div class="col-8">
                            <div class="row" style="display: flex; text-align: left; align-items: center;">
                                <div class="col-sm-3">
                                    <img :src="item.popfile"
                                        style="width: 300px; height: 300px; border-radius: 20px; margin-left:-10px; padding-right:10px">
                                </div>
                                <div class="col-sm-2" style="text-align: left; align-items: center;">
                                    <strong class="g-color-black" style="padding-left: 30px;">견종</strong><br />
                                    <strong class="g-color-black" style="padding-left: 30px;">성별</strong><br />
                                    <strong class="g-color-black" style="padding-left: 30px;">나이</strong><br />
                                    <strong class="g-color-black" style="padding-left: 30px;">색</strong><br />
                                    <strong class="g-color-black" style="padding-left: 30px;">중성화여부</strong><br />
                                    <strong class="g-color-black" style="padding-left: 30px;">동물보호센터명</strong><br/>
                                    <strong class="g-color-black" style="padding-left: 30px;">보호장소</strong><br/>
                                </div>
                                <div class="col-sm-6" style="text-align: left; align-items: center;">
                                    <strong class="g-color-black">{{ item.kindCd }}</strong><br />
                                    <strong class="g-color-black" v-if="item.sexCd === 'M'">수컷</strong>
                                    <Strong class="g-color-black" v-else-if="item.sexCd === 'F'">암컷</strong>
                                    <Strong class="g-color-black" v-else>미상</strong><br />
                                    <strong class="g-color-black">{{ item.age }}</strong><br />
                                    <strong class="g-color-black">{{ item.colorCd }}</strong><br />
                                    <strong class="g-color-black" v-if="item.neuterYn === 'Y'">중성화O</strong>
                                    <Strong class="g-color-black" v-else-if="item.neuterYn === 'N'">중성화X</strong>
                                    <Strong class="g-color-black" v-else-if="item.neuterYn === 'U'">미상</strong><br />
                                    <strong class="g-color-black">{{ item.careNm }}</strong><br/>
                                    <strong class="g-color-black">{{ item.careAddr }}</strong><br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <div class="applier-container" v-for="list in list" :key="list.num">

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">입양신청일: {{ dateonly }}</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">Id: {{ list.id.id }}</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">이름: {{ list.id.name }}</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">반려 동물 여부: {{ list.another }}</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">입양을 결심하신 이유를 알려주세요.</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">{{ list.reason }}</div>
    </div>

    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">반려 동물로 맞이한 후에 아가와 함께할 시간들을 생각하며, 입양 후 다짐을 자유롭게 기술해 주세요.</div>
    </div>
    
    <div class="row" style="text-align: left;">
      <div class="col-3"></div>
      <div class="col-4">{{ list.feeding }}</div>
    </div>

  </div>
</template>
    
<script>
import axios from 'axios';
export default {
    name: 'ApplyDetail',
    data() {
        return {
            items: [],
            list: [],
        }
    },
    created: function () {
        this.$data.petCd = this.$route.query.petCd;
        this.$data.num = this.$route.query.num;
        this.fetchItems();
        this.fetchApplyFormData();
    },
    computed: {
        filteredItems() {
            if (!this.petCd) {
                return [];
            }
            this.items.filter(item => item.desertionNo === this.petCd)[0];

            return this.items.filter(item => item.desertionNo === this.petCd);
        },
        dateonly(){
            return this.list.wdate.split('T')[0];
        }   
        
    },

    methods: {
        fetchItems() {
            axios
                .get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=1000&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
                .then((response) => {
                    const data = response.data;
                    const items = data.response.body.items.item;
                    this.items = items; // 데이터를 items 배열에 할당
                })
                .catch(this.handleError); // handleError 함수 호출
        },
        fetchApplyFormData() {
            const self = this; // self 변수에 컴포넌트 인스턴스를 할당
            const num = this.$data.num;
            self.$axios
                .get('http://localhost:8082/Applyform/getbynum/'+num) // 변경된 URL로 변경 필요
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
    