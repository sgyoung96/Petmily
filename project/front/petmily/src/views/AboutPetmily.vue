<template>
    <div class="col-8" style="margin-left: 250px; text-align: left; margin-top: 30px">
        <h2><span style="color: #f0cf81;"><strong>팀 소개</strong></span>&nbsp;</h2>
        <p>PETMILY는 프로젝트 주제를 선정하는 중 유기동물들이 매일 몇십에서 몇백 마리까지 생긴 다는 것을 알게 되었고,
            이 사실을 이 사이트를 보는 사람들이 알 수 있게 만들어 봤습니다.
        </p>
        <p>PETMILY의 의미는 반려동물(PET) + 가족(FAMILY)의 합성어로 반려동물을 가족의 구성원으로 생각한다는 의미에서
            이름을 만들게 되었습니다
        </p>

        <hr>

        <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong>개발 도구</strong></span></h3>
        <p>사용언어 : Java</p>
        <p>개발환경 및 도구 : Git, SourceTree, Oracle, Vue</p>

        <hr>
        
        <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong>사용 API</strong></span></h3>
        <ul>
        <li style="font-size: 20px" @click="gotoanimal">전국유기동물 API</li>
        <li style="font-size: 20px" @click="gotoplace">공간정보(지도) API</li>
        <li style="font-size: 20px" @click="gotohospital">전국동물병원 API</li>
        <li style="font-size: 20px" @click="gotochart">그래프 API</li>
        <li style="font-size: 20px" @click="gotoweather">날씨정보 API</li>
        </ul>
        <br>

        <hr>
        
        <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong></strong></span></h3>

        <hr>

        <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong>팀원</strong></span></h3>
        <p>손선영 | 김진혁 | 송가영 | 전준하 | 최동혁</p>
    </div>
</template>

<script>
export default {
  methods: {
    gotoanimal() {
      window.location.href = 'https://www.data.go.kr/data/15098931/openapi.do#tab_layer_prcuse_exam';
    },

    gotoplace() {
      window.location.href = 'https://www.data.go.kr/data/15059464/openapi.do';
    },

    gotohospital() {
      window.location.href = 'https://www.data.go.kr/data/15098915/openapi.do';
    },

    gotochart() {
      window.location.href = 'https://www.chartjs.org/docs/latest/charts/bar.html';
    },

    gotoweather() {
      window.location.href = 'https://www.data.go.kr/data/15084084/openapi.do';
    }
  }
};
</script>

<style>
p{
    font-size: 20px;
    font-family: 'IBMPlexSansKR-Bold'
}

h3{
    margin-top: 30px;
    font-family: 'IBMPlexSansKR-Bold'
}

h2{
    font-family: 'IBMPlexSansKR-Bold'
}
</style>
