<template>
  
  <div v-if="showSvg"  @click="showDivs">
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <svg viewBox="-150 -60 1000 20" style="position: absolute; top: 0; left: 0; z-index: 9999;">
      <text>PETMILY</text>
    </svg>
  </div>
  <div v-if="showDiv">
    <div class="col-8" style="margin-left: 250px; text-align: center; margin-top: 30px">
    <div class="appear">
    <h2><span style="color: #f0cf81;"><strong>PETMILY를 소개합니다</strong></span>&nbsp;</h2>
    <img src="@/assets/images/family.jpg" alt="가족강아지 이미지" style="width: 100%; height: 500px; padding-bottom: 60px; padding-top: 20px">
    
    <p>PETMILY의 의미는 반려동물(PET) + 가족(FAMILY)의 합성어로 반려동물은 가족이라는 의미를 가지고 있습니다</p>

   <hr>

  </div>

  <div class="appear">
   <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong></strong>펫밀리의 사이트 설명</span></h3>
   <img src="@/assets/images/dogwalk.webp" alt="보호소 이미지" style="width: 300px; height: 300px; margin-left:30px; padding-bottom: 20px; padding-top: 20px">
   <img src="@/assets/images/careplace.jpg" @click="gotogroups" alt="보호소 이미지" style="width: 300px; height: 300px; padding-bottom: 20px; padding-top: 20px">
   <img src="@/assets/images/입양사진.jpeg" @click="gotoallpets" alt="보호소 이미지" style="width: 300px; height: 300px; padding-bottom: 20px; padding-top: 20px">
   <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong></strong></span></h3>
   <p>오늘의 날씨를 알려드려 산책 지향 및 산책 주의사항을 알려드립니다</p>
   <p>언제 어디서든 치료 및 보호를 받을 수 있게 전국 보호소 및 병원을 알려드리고 있으며</p>
   <p>잃어버린 반려동물을 찾거나 반려동물 입양 희망 시 저희 사이트를 통해 찾아보실수 있습니다</p>
   
   <hr>
   
   </div>

   <div class="appear">
   <h3><span style="color: #f0cf81; padding: bottom 20px;"><strong></strong>펫밀리의 모토</span></h3>
   <div class="wrapper">
         <div class="sub-title wowrap">
           <p class="title_bold wow fadeInUp" data-wow-delay="0.25s" style="visibility: visible; animation-delay: 0.25s; animation-name: fadeInUp;">MOTTO</p>
         </div>
         <ul class="promise_list clearfix wowrap" style="margin-left:140px;">
           <div class="wow fadeInUp" data-wow-delay="0s" style="visibility: visible; animation-delay: 0s; animation-name: fadeInUp;">
             <img src="@/assets/images/봉사.png" alt="봉사" style="width: 100px; height: 100px; padding-bottom: 10px;">
             <p class="tit">봉사</p>
             <p class="txt">주기적인 유기동물보호소 봉사와 후원으로 사회적 공헌 및 활동</p>
           </div>
           <div class="wow fadeInUp" data-wow-delay="0.25s" style="visibility: visible; animation-delay: 0.25s; animation-name: fadeInUp;">
             <img src="@/assets/images/전문.png" alt="전문" style="width: 100px; height: 100px; padding-bottom: 10px;">
             <p class="tit">전문</p>
             <p class="txt">반려동물 관련분야 전문가들과 유기적인 협력으로 반려문화 선진화에 기여</p>
           </div>
           <div class="wow fadeInUp" data-wow-delay="0.5s" style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInUp;">
             <img src="@/assets/images/소통.png" alt="소통" style="width: 100px; height: 100px; padding-bottom: 10px;">
             <p class="tit">소통</p>
             <p class="txt">입양플래너, 수의사 등 전문가와  함께하는 실시간 소통, 지속적인 사후케어서비스</p>
           </div>
           <div class="wow fadeInUp" data-wow-delay="0.75s" style="visibility: visible; animation-delay: 0.75s; animation-name: fadeInUp;">
             <img src="@/assets/images/진심.png" alt="봉사" style="width: 100px; height: 100px; padding-bottom: 10px;">
             <p class="tit">진심</p>
             <p class="txt">진실된 마음으로 반려동물과 고객을 대하며, 반려인의 목소리에 귀를 기울이는 자세</p>
           </div>
         </ul>
 

       </div>
         
   <p>PETMILY에서는 해당 모토를 유지하며 반려동물과 고객분의 행복하고 올바른 반려동물문화를 지향하고 있으며</p>
   <p>유기동물들을 구조하고 보호 및 관리하며 새로운 가족들을 찾아주고 있습니다</p>
   
   <hr>

  </div>

  <div class="appear">
   <img src="@/assets/images/dogcat.jpg" @click="gotogroups" alt="내가 원하는 이미지" style="width: 600px; height: 500px; padding-bottom: 50px; padding-top: 20px">
   <p>저희 PETMILY에서도 고객분들과 반려동물을 위해 최선을 다할 것입니다</p>
   <p>변함없이 노력하는 펫밀리가 될 것을 약속 드립니다</p>

   <div class="dog" @click="game()" style="margin-left:1000px; margin-top: -90px;">
 <div class="dog-body">
   <div class="dog-tail">
     <div class="dog-tail">
       <div class="dog-tail">
         <div class="dog-tail">
           <div class="dog-tail">
             <div class="dog-tail">
               <div class="dog-tail">
                 <div class="dog-tail"></div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </div>
 <div class="dog-torso"></div>
 <div class="dog-head">
   <div class="dog-ears">
     <div class="dog-ear"></div>
     <div class="dog-ear"></div>
     <div class="dog-ear"></div>
   </div>
   <div class="dog-eyes">
     <div class="dog-eye"></div>
     <div class="dog-eye"></div>
   </div>
   <div class="dog-muzzle">
     <div class="dog-tongue"></div>
   </div>
   <div class="dog-earline">
     <div class="dog-earliner"></div>
     <div class="dog-earliner"></div>
     <div class="dog-earliner"></div>
   </div>
 </div>
</div>
   <hr>
   
  </div>

  <div class="appear">
   <h3><span style="color: #f0cf81;"><strong>사용 API</strong></span></h3>
   <ul>
   <li style="font-size: 20px" @click="gotoanimal">전국유기동물 API</li>
   <li style="font-size: 20px" @click="gotoplace">공간정보(지도) API</li>
   <li style="font-size: 20px" @click="gotohospital">전국동물병원 API</li>
   <li style="font-size: 20px" @click="gotochart">그래프 API</li>
   <li style="font-size: 20px" @click="gotoweather">날씨정보 API</li>
   </ul>
   <br>
  </div>
 </div>
</div>
</template>

<script>

export default {
  data() {
    return {
      showSvg: true,
      showDiv: false
    };
  },
  mounted() {
    setTimeout(() => {
      this.showSvg = false;
      this.showDiv = true;
    }, 6000);
    window.scrollTo({ top: 0, behavior: 'auto' });
  },
methods: {
  game(){
      this.$router.push({ name: 'ImgSl'})
  },
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

  gotoallpets() {
    this.$router.push('/api');
  },

  showDivs() {
    this.showSvg = false;
    this.showDiv = true;
  }
  
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
  font-size:150px;
  position: absolute;
  width: 100%;
  height: 100%;
  text-transform: uppercase;
  animation: stroke 5s 1 alternate;
  fill: #f0cf81;
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
    fill: #f0cf81;
    stroke: #fae7ba;
    stroke-width: 0;
  }
}

body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

html, body {
  background: #b7e3e4;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

*, *:before, *:after {
  box-sizing: border-box;
  position: relative;
}

.dog {
  width: 100px;
  height: 100px;
  z-index: 1;
}

  .dog:before {
  margin-left:-50px;
  margin-top: -20px;
  content: '';
  display: block;
  position: absolute;
  width: 200%;
  height: 200%;
  border-radius: 50%;
  background: rgba(42, 128, 157, 0.5);
  transform: translateY(-30%) scale(1.5);
}

.dog * {
  position: absolute;
}

.dog-body, .dog-head, .dog-torso {
  border-radius: 50%;
  background: white;
  position: absolute;
  width: 100%;
  height: 100%;
}

.dog-body {
  top: -50%;
  box-shadow: inset 0 -15px 0 0 #eaebec;
  animation: dog-body 200ms ease-in-out infinite alternate;
}


@keyframes dog-tail-blur {
  0% {
    transform: rotate(0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    transform: rotate(90deg);
  }
}

@keyframes dog-body {
  from {
    transform: translateX(-10%);
  }
  to {
    transform: translateX(10%);
  }
}

.dog-head {
  border-radius: 80px;
  border-top-right-radius: 200px;
  border-top-left-radius: 200px;
  box-shadow: inset -3px -4px 15px 0 #eaebec;
  animation: dog-head 2000ms 1800ms cubic-bezier(0.11, 0.79, 0, 0.99) infinite;
}

@keyframes dog-head {
  0% {
    transform: rotate(45deg);
  }
  33% {
    transform: rotate(-45deg);
  }
  66% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(45deg);
  }
}

.dog-torso {
  top: -20%;
  box-shadow: inset 0 -15px 0 0 #eaebec;
  animation: dog-torso 200ms ease-in-out infinite alternate-reverse;
}

@keyframes dog-torso {
  0% {
    transform: translateX(-5%);
  }
  100% {
    transform: translateX(5%);
  }
}

.dog-eyes {
  width: 60%;
  top: 55%;
  left: 20%;
  z-index: 1;
}

.dog-eyes:before {
  content: '';
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 40px;
  background: white;
  position: absolute;
  top: -10px;
  left: -10px;
  z-index: 0;
  border: 4px solid white;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  transform: rotate(-45deg);
}

.dog-eye {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: black;
  z-index: 1;
}

.dog-eye:first-child {
  left: 0;
}

.dog-eye:last-child {
  right: 0;
}

.dog-muzzle {
  width: 60%;
  left: 20%;
  height: 50%;
  border-bottom-left-radius: 100%;
  border-bottom-right-radius: 100%;
  bottom: -15%;
  background: white;
}

.dog-muzzle:before, .dog-muzzle:after {
  content: '';
  display: block;
  position: absolute;
}

.dog-muzzle:before {
  width: 6px;
  height: 20px;
 left: calc(50% - 3px);
  bottom: 0;
  background: #eaebec;
}

.dog-muzzle:after {
  background: black;
  width: 20px;
  height: 15px;
  left: calc(50% - 10px);
  bottom: 12px;
  border-bottom-left-radius: 60% 60%;
  border-bottom-right-radius: 60% 60%;
  border-top-left-radius: 50% 40%;
  border-top-right-radius: 50% 40%;
}

.dog-ears {
  width: 40%;
  top: 65%;
  left: 43%;
}



.dog-ear:first-child {
  right: 90%;
  border-radius: 30%;
  border-bottom-left-radius: 50%;
  border-bottom-right-radius: 50%;
  box-shadow: inset 1px 1px 5px 0 #8DAFAF;
}


.dog-ear:last-child {
  border-radius: 30%;
  border-bottom-left-radius: 50%;
  border-bottom-right-radius: 50%;
  box-shadow: inset -1px 1px 5px 0 #8DAFAF;
  left: 20%;
}

.dog-tongue {
  width: 40%;
  height: 100%;
  left: calc(50% - 20px);
  z-index: -1;
  transform-origin: center top;
}

.dog-tongue:before {
  content: '';
  position: absolute;
  left: 8px;
  display: block;
  width: 100%;
  height: 100%;
  border-radius: 40px;
  background: #fd3163;
  animation: dog-tongue-inner 200ms/2 ease-in-out infinite alternate;
}

@keyframes dog-tongue-inner {
  from {
    transform: translateY(5%);
  }
  to {
    transform: translateY(22%);
  }
}

.dog-tail {
  width: 17px;
  height: 18.7px;
  background: white;
  bottom: 40%;
  border-radius: 8.5px;
  left: calc(50% - 8.5px);
  transform-origin: center bottom;
  animation: dog-tail-segment 200ms ease-in-out infinite alternate;
}

.dog-tail .dog-tail {
  animation: none;
}

@keyframes dog-tail-segment {
  0% {
    transform: rotate(-10deg);
  }
  100% {
    transform: rotate(10deg);
  }
}

.dog-body > .dog-tail {
  bottom: 90%;
}

.dog-earline {
  width: 10%;
  top: 5%;
  left: 20%;
  z-index: 2;
}

.wrapper {
    width: 100%;

    position: relative;
    margin: 0 auto;
}
.sub-title {
    text-align: center;
    padding-bottom: 60px;
}

.promise_list div {
    word-break: keep-all;
    text-align: center;
    float: left;
    width: 40%;
    padding: 0 10px;
}
</style>
