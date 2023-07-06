<template>
  <div class="col-8" style="margin-left: 250px; text-align: left; margin-top: 30px">
    <br>
    <svg viewBox="0 0 400 400" style="position: absolute; top: 0; left: 0; z-index: 9999; margin-top:-430px;  margin-left:900px ">
    <text>PETMILY</text>
  </svg>
      <h2><span style="color: #f0cf81;"><strong>PETMILY를 소개합니다</strong></span>&nbsp;</h2>
      <img src="@/assets/images/family.jpg" alt="가족강아지 이미지" style="width: 100%; height: 500px; padding-bottom: 10px;">
      <p>PETMILY의 의미는 반려동물(PET) + 가족(FAMILY)의 합성어로 반려동물은 가족이라는 모토를 가지고 있습니다</p>
      <p>PETMILY에서는 해당 모토를 유지하며 반려동물과 고객분의 행복하고 올바른 반려동물문화를 지향하고 있으며</p>
      <p>유기동물들을 구조하고 보호 및 관리하며 새로운 가족들을 찾아주고 있습니다</p>

      <hr>

      <img src="@/assets/images/dogwalk.webp" alt="보호소 이미지" style="width: 600px; height: 500px; margin-left: 200px; padding-bottom: 10px;">
      <p>오늘의 날씨를 알려드려 산책 지향 및 산책 주의사항을 알려드립니다</p>
      
      <hr>
      
      <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong></strong></span></h3>
      <img src="@/assets/images/careplace.jpg" @click="gotogroups" alt="보호소 이미지" style="width: 600px; height: 500px; margin-left: 200px; padding-bottom: 10px;">
      <p>언제 어디서든 치료 및 보호를 받을 수 있게 전국 보호소 및 병원을 알려드리고 있으며</p>
      <p>저희 PETMILY에서도 고객분들과 반려동물을 위해 최선을 다할 것입니다</p>

      <hr>
      <p>더 궁금하신게 있다면 아래 연락처로 연락해주시면 성심성의껏 돕겠습니다</p>
      <img src="@/assets/images/call.png" alt="전화기 이미지" style="width: 20px; height: 20px;">
      <p style="display: inline; padding-left: 10px;">전화번호 : 257-pet-mily</p>

      <br>
      
      <img src="@/assets/images/email.png" alt="이메일 이미지" style="width: 20px; height: 20px;">
      <p style="display: inline; padding-left: 10px;">이메일 : petmily@petmily.kr</p>

      <hr>

      <img src="@/assets/images/dogcat.jpg" @click="gotogroups" alt="내가 원하는 이미지" style="width: 600px; height: 500px; margin-left: 200px; padding-bottom: 10px;">
      <p>PETMILY는 여러분의 행복한 반려생활을 언제나 응원합니다</p>
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
  },

  gotogroups() {
    this.$router.push({ name: 'Groups'});
  },
  
}
};
</script>

<style scoped>
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

@font-face {
    font-family: 'Tmon';
    font-weight: normal;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/tmon/Tmon.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/tmon/Tmon.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/tmon/Tmon.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/tmon/Tmon.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/tmon/Tmon.ttf') format("truetype");
    font-display: swap;
} 
body {
  background-color: #e3f2fd;
}
svg {
  font-family: 'Tmon';
  font-size: 20px;
  position: absolute;
  width: 100%;
  height: 100%;
  text-transform: uppercase;
  animation: stroke 5s 1 alternate;
  fill: #f0cf81;;
}
@keyframes stroke {
  0%   {
    stroke-dashoffset: 25%;
    stroke-dasharray: 0 50%;
    fill: white;
    stroke: #fae7ba;
    stroke-width: 2;
  }
  70%  {
    fill: white;
    stroke: #fae7ba;
  }
  80%  {
    fill: white;
    stroke: #fae7ba;
  }
  100% {
    stroke-dashoffset: -25%;
    stroke-dasharray: 50% 0;
    fill: white;
    stroke: #fae7ba;
    stroke-width: 0;
  }
}
</style>
