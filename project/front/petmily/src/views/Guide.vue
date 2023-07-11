<template>
  <div>
    <img style="width:100%;" src="../assets/images/입양가이드1.png">

    <div class="slider-2">
      <div class="side-btns">
        <div><span><i class="fas fa-caret-left"><img style="width:50px; height:50px;"
                src="https://cdn-icons-png.flaticon.com/512/5602/5602104.png"></i></span></div>
        <div><span><i class="fas fa-caret-right"><img style="width:50px; height:50px;"
                src="https://cdn-icons-png.flaticon.com/512/5602/5602376.png"></i></span></div>
      </div>

      <div class="slides">
        <div class="active"><img src="../assets/images/1가이드.jpg" alt=""></div>
        <div><img src="../assets/images/2가이드.jpg" alt=""></div>
        <div><img src="../assets/images/3가이드.jpg" alt=""></div>
        <div><img src="../assets/images/4가이드.jpg" alt=""></div>
        <div><img src="../assets/images/5가이드.jpg" alt=""></div>
        <div><img src="../assets/images/6가이드.jpg" alt=""></div>
      </div>

      <div class="page-nav">
        <div class="active"></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
      </div>
    </div>

    <div id="app">
      <!-- ... Existing code ... -->

      <div class="form-group">
        <input type="hidden" class="form-control" v-model="videoUrl">
      </div>

      <!-- Display YouTube video -->
      <!-- Display YouTube video -->
      <div class="form-group">
        <div v-if="isValidVideoUrl">
          <iframe :src="embeddedVideoUrl" width="1290" height="700" frameborder="0" allowfullscreen></iframe>
        </div>
        <div v-else>
          <p>유효한 YouTube 동영상 URL을 입력하세요.</p>
        </div>
      </div>

      <!-- ... Existing code ... -->
    </div>

    <div class="rounded">
      <p class="hash-tit" style="padding-top: 20px;">
        #
        <span>입양 시 꼭 체크하세요</span>
      </p>
      <p class="txt" style="font-size:18px;">
        국내법상으로는 태어난 지 2개월 이후의 반려견만을 입양할 수 있습니다. 최소한의 면역력이 형성되면서 유동식을 먹을 수 있는 나이가 되어야만 입양이 가능한 것인데요. 출생일을
        정확히 잘 모르기 때문에 이 부분은 분양을 하는 분의 말을 전적으로 믿을 수밖에 없습니다.
      </p>
      <p class="txt"></p>
      <div class="box">
        <span class="img">
          <img class="boximg" alt src="https://www.kca.go.kr/webzine/resources/images/webzine/sub/2009/recommend_08.jpg">
        </span>
        <div class="txt_wrap">
          <p class="tt">
            <em>1</em>
            <span>세심한 위생관리 필요</span>
          </p>
          <p class="txt">
            6주 정도면 1차 예방접종을 하게 되는데요. 예방접종 주사는 반드시 동물병원을 개설한 수의사가 접종해야 합니다, 하지만 국내는 아직 이러한 부분에 대한 인식이 부족하여 수의사의
            확인이 들어간 접종 내역 없이 구두로만 했다라고 하는 곳들이 많이 있습니다. 첫 입양 시 반드시 수의사의 접종 내역을 확인하는 것이 중요합니다. 또한 이 시기의 반려견은 전염병에
            굉장히 취약하기 때문에 세심한 위생관리가 필요합니다. 항체가 형성되기 전에는 산책을 하지 않는다거나 반려견이 많은 장소에는 가는 것을 자제하는 등의 조심스러움이 필요하죠. 이와
            함께 입양 초기에 오랜 기간 집을 비우는 일이 있다면 꼭 입양을 미뤄야 합니다.
          </p>
        </div>
      </div>
      <div class="box">
        <span class="img">
          <img class="boximg" alt src="https://www.kca.go.kr/webzine/resources/images/webzine/sub/2009/recommend_09.jpg">
        </span>
        <div class="txt_wrap">
          <p class="tt">
            <em>2</em>
            <span>반드시 계약서를 작성하고 꼼꼼히 확인</span>
          </p>
          <p class="txt">
            입양 시 계약서에 [소비자기본법 시행령] 8조 3항에 따른 내용이 기재 되어있는지 확인해야 합니다. 소비자분쟁해결기준에 따르면 15일 이내 반려견이 사망하면 같은 종류의 반려견을
            받거나 구입가격을 환급받을 수 있도록 권고하고 있습니다. 또한 입양 직후, 동물에게 건강상의 문제가 생기면 판매업체에서 치료를 한 후 소비자에게 돌려주어야한다는 규정도 있는데요.
            그러나 건강상의 문제가 생긴 경우의 처리방법에 있어 ‘배상 제외 질병’이나 ‘지정 병원’을 정하는 내용이 포함되어 있다면, 판매업체가 배상 책임을 지지 않을 가능성이 커 유의해야
            합니다.
          </p>
        </div>
      </div>
      <div class="box">
        <span class="img">
          <img class="boximg" alt src="https://www.kca.go.kr/webzine/resources/images/webzine/sub/2009/recommend_10.jpg">
        </span>
        <div class="txt_wrap">
          <p class="tt">
            <em>3</em>
            <span>예방접종내역을 정확하게 확인</span>
          </p>
          <p class="txt">
            보통의 보호자들은 동물병원에 내원 후, “2차 접종까지 했다고 합니다”라고 이야기하곤 합니다. 그러나 접종의 종류는 혼합백신, 코로나 장염백신,
            전염성기관지염백신, 광견병 백신, 신종플루 백신이 있습니다. 단순히 2차라고 한다면 총 5차를 접종해야하는 혼합백신만 2번 맞았다로 생각이 되기 때문에 어떤 접종을 몇 번 맞았는지
            정확히 확인하는 것이 중요합니다.
          </p>
        </div>
      </div>
      <div class="box">
        <span class="img">
          <img class="boximg" alt src="https://www.kca.go.kr/webzine/resources/images/webzine/sub/2009/recommend_11.jpg">
        </span>
        <div class="txt_wrap">
          <p class="tt">
            <em>4</em>
            <span>반려견 물품 구매</span>
          </p>
          <p class="txt">
            입양 시, 입양을 하는 곳에서 서비스로 이것저것 챙겨주기는 하지만, 가장 중요한 것은 입양한 반려견에게 맞는 물건을 선택하는 것입니다. 그래서 입양 결정을 했다면 추후 접종을
            진행할 동물병원을 찾아놓고, 미리 내원하여 수의사 선생님께 자문을 구하는 것을 추천 드립니다. 입양 시 반드시 필요한 물품으로는 사료, 사료그릇, 배변패드, 방석, 장난감
            정도이고요. 추후 1주일 정도 관리해보고 발톱깎이, 귀 세정제, 샴푸, 빗, 목걸이, 줄, 이동장, 장난감 등의 제품을 구매하는 것이 좋습니다. 또 급하게 구매하기 보다는 입양 후
            안정을 취하시고 물품을 구매하길 바랍니다.
          </p>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-7">
        <table class="table" v-if="witems.length">
          <thead>
            <tr>
              <th scope="col">#{{ this.date }}일자 산책</th>
              <th scope="col" v-if="witems.length">{{ witems[0].fcstTime }}시</th>
              <th scope="col" v-if="witems.length">{{ witems[12].fcstTime }}시</th>
              <th scope="col" v-if="witems.length">{{ witems[24].fcstTime }}시</th>
              <th scope="col" v-if="witems.length">{{ witems[36].fcstTime }}시</th>
              <th scope="col" v-if="witems.length">{{ witems[48].fcstTime }}시</th>
            </tr>
          </thead>
          <tbody v-if="witems.length">
            <tr>
              <th scope="row">기온</th>
              <td>{{ witems[0].fcstValue }}℃</td>
              <td>{{ witems[12].fcstValue }}℃</td>
              <td>{{ witems[24].fcstValue }}℃</td>
              <td>{{ witems[36].fcstValue }}℃</td>
              <td>{{ witems[48].fcstValue }}℃</td>
            </tr>
            <tr>
              <th scope="row">날씨</th>
              <td v-if="witems.length">
                <span v-if="witems[9].fcstValue != '강수없음'">☔</span>
                <span v-else>
                  <span v-if="witems[5].fcstValue === '1'">🌞</span>
                  <span v-else-if="witems[5].fcstValue === '3'">⛅</span>
                  <span v-else-if="witems[5].fcstValue === '4'">☁</span>
                </span>
              </td>
              <td v-if="witems.length">
                <span v-if="witems[21].fcstValue != '강수없음'">☔</span>
                <span v-else>
                  <span v-if="witems[17].fcstValue === '1'">🌞</span>
                  <span v-else-if="witems[17].fcstValue === '3'">⛅</span>
                  <span v-else-if="witems[17].fcstValue === '4'">☁</span>
                </span>
              </td>
              <td v-if="witems.length">
                <span v-if="witems[33].fcstValue != '강수없음'">☔</span>
                <span v-else>
                  <span v-if="witems[29].fcstValue === '1'">🌞</span>
                  <span v-else-if="witems[29].fcstValue === '3'">⛅</span>
                  <span v-else-if="witems[29].fcstValue === '4'">☁</span>
                </span>
              </td>
              <td v-if="witems.length">
                <span v-if="witems[45].fcstValue != '강수없음'">☔</span>
                <span v-else>
                  <span v-if="witems[41].fcstValue === '1'">🌞</span>
                  <span v-else-if="witems[41].fcstValue === '3'">⛅</span>
                  <span v-else-if="witems[41].fcstValue === '4'">☁</span>
                </span>
              </td>
              <td v-if="witems.length">
                <span v-if="witems[45].fcstValue != '강수없음'">☔</span>
                <span v-else>
                  <span v-if="witems[41].fcstValue === '1'">🌞</span>
                  <span v-else-if="witems[41].fcstValue === '3'">⛅</span>
                  <span v-else-if="witems[41].fcstValue === '4'">☁</span>
                </span>
              </td>
            </tr>
            <tr>
              <th scope="row">강수량</th>
              <td v-if="witems.length">{{ witems[9].fcstValue }}</td>
              <td v-if="witems.length">{{ witems[21].fcstValue }}</td>
              <td v-if="witems.length">{{ witems[33].fcstValue }}</td>
              <td v-if="witems.length">{{ witems[45].fcstValue }}</td>
              <td v-if="witems.length">{{ witems[45].fcstValue }}</td>
            </tr>
            <tr>
              <th scope="row">갱얼쥐 산책</th>
              <td>
                <span v-if="witems[0].fcstValue < '18'">양호🐾</span>
                <span v-else-if="witems[0].fcstValue >= '18' && witems[0].fcstValue < '26'">약간주의💦</span>
                <span v-else-if="witems[0].fcstValue >= '26' && witems[0].fcstValue < '29'">주의🚨</span>
                <span v-else-if="witems[0].fcstValue >= '29' && witems[0].fcstValue < '32'">위험🔥</span>
                <span v-else-if="witems[0].fcstValue >= '32'">매우위험🚷</span>
              </td>
              <td>
                <span v-if="witems[12].fcstValue < '18'">양호🐾</span>
                <span v-else-if="witems[12].fcstValue >= '18' && witems[12].fcstValue < '26'">약간주의💦</span>
                <span v-else-if="witems[12].fcstValue >= '26' && witems[12].fcstValue < '29'">주의🚨</span>
                <span v-else-if="witems[12].fcstValue >= '29' && witems[12].fcstValue < '32'">위험🔥</span>
                <span v-else-if="witems[12].fcstValue >= '32'">매우위험🚷</span>
              </td>
              <td>
                <span v-if="witems[24].fcstValue < '18'">양호🐾</span>
                <span v-else-if="witems[24].fcstValue >= '18' && witems[48].fcstValue < '26'">약간주의💦</span>
                <span v-else-if="witems[24].fcstValue >= '26' && witems[24].fcstValue < '29'">주의🚨</span>
                <span v-else-if="witems[24].fcstValue >= '29' && witems[24].fcstValue < '32'">위험🔥</span>
                <span v-else-if="witems[24].fcstValue >= '32'">매우위험🚷</span>
              </td>
              <td>
                <span v-if="witems[36].fcstValue < '18'">양호🐾</span>
                <span v-else-if="witems[36].fcstValue >= '18' && witems[36].fcstValue < '26'">약간주의💦</span>
                <span v-else-if="witems[36].fcstValue >= '26' && witems[36].fcstValue < '29'">주의🚨</span>
                <span v-else-if="witems[36].fcstValue >= '29' && witems[36].fcstValue < '32'">위험🔥</span>
                <span v-else-if="witems[36].fcstValue >= '32'">매우위험🚷</span>
              </td>
              <td>
                <span v-if="witems[48].fcstValue < '18'">양호🐾</span>
                <span v-else-if="witems[48].fcstValue >= '18' && witems[48].fcstValue < '26'">약간주의💦</span>
                <span v-else-if="witems[48].fcstValue >= '26' && witems[48].fcstValue < '29'">주의🚨</span>
                <span v-else-if="witems[48].fcstValue >= '29' && witems[48].fcstValue < '32'">위험🔥</span>
                <span v-else-if="witems[48].fcstValue >= '32'">매우위험🚷</span>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="6">데이터가 없습니다.</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col-5">
        <table class="table">
          <thead>
            <tr>
              <th scope="row">#</th>
              <th scope="col">여름철산책정도</th>
              <th scope="col">기온</th>
            </tr>
          </thead>
          <tbody>
            <tr class="table-info">
              <th scope="row">1</th>
              <th>양호🐾</th>
              <th>18℃미만</th>
            </tr>
            <tr class="table-primary">
              <th scope="row">2</th>
              <th>약간주의💦</th>
              <th>18℃이상 26℃미만</th>
            </tr>
            <tr class="table-warning">
              <th scope="row">3</th>
              <th>주의🚨</th>
              <th>26℃이상 29℃미만</th>
            </tr>
            <tr class="table-danger">
              <th scope="row">4</th>
              <th>위험🔥</th>
              <th>29℃이상 32℃미만</th>
            </tr>
            <tr class="table-secondary">
              <th scope="row">5</th>
              <th>매우위험🚷</th>
              <th>32℃이상</th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'ImgSl',

  data() {
    return {
      videoUrl: 'https://www.youtube.com/watch?v=yYKrcrUoFnY',
      witems: [],
      datetime: new Date(),
      date: this.formatDate(new Date()),
      time: '',
    }
  },

  created: function () {
    if (8 <= this.datetime.getHours() && this.datetime.getHours() <= 14) {
      this.time = '0800';
    } else if (14 <= this.datetime.getHours() && this.datetime.getHours() < 20) {
      this.time = '1400';
    } else {
      this.time = '2000';
    }

    this.fetchData2();
  },

  mounted() {
    this.initSlider();
  },
  computed: {
    embeddedVideoUrl() {
      // Extract video ID from the URL
      const videoId = this.extractVideoId(this.videoUrl);
      // Create the embedded video URL
      return `https://www.youtube.com/embed/${videoId}`;
    },
    isValidVideoUrl() {
      // Validate the YouTube video URL
      return this.extractVideoId(this.videoUrl) !== null;
    }
  },
  methods: {
    fetchData2() {


      console.log(this.time);
      console.log(this.date);
      const self = this;
      self.$axios.get(`https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?dataType=json&serviceKey=hqbUzbZx%2BbQR6OgVCNvZDXGGWIVTWAIawDhN2Y9fbW6Pndu%2BrU9e1NaR9UpW7%2BPotKdwoD9cXlkHbSS7tzFRJQ%3D%3D&numOfRows=50&pageNo=1&base_date=${self.date}&base_time=${self.time}&nx=62&ny=122`)
        .then(function (res) {
          if (res.status == 200) {
            const data = res.data.response.body
            self.witems = data.items.item;
          } else {
            alert(res.status)
          }
        })
    },
    formatDate(date) {
      const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
      };

      return new Date(date).toLocaleString('ko-KR', options).replace(/\D/g, '');
    },
    initSlider() {
      const slider = document.querySelector('.slider-2');
      const slides = slider.querySelectorAll('.slides > div');
      const navItems = slider.querySelectorAll('.page-nav > div');

      navItems.forEach((item, index) => {
        item.addEventListener('click', () => {
          navItems.forEach((navItem) => navItem.classList.remove('active'));
          item.classList.add('active');

          slides.forEach((slide) => slide.classList.remove('active'));
          slides[index].classList.add('active');
        });
      });

      const prevBtn = slider.querySelector('.side-btns > div:first-child');
      const nextBtn = slider.querySelector('.side-btns > div:last-child');

      prevBtn.addEventListener('click', () => {
        const current = slider.querySelector('.page-nav > div.active');
        let prev = current.previousElementSibling;

        if (!prev) {
          prev = slider.querySelector('.page-nav > div:last-child');
        }

        prev.click();
      });

      nextBtn.addEventListener('click', () => {
        const current = slider.querySelector('.page-nav > div.active');
        let next = current.nextElementSibling;

        if (!next) {
          next = slider.querySelector('.page-nav > div:first-child');
        }

        next.click();
      });
    },
    extractVideoId(url) {
      // Regular expression to extract the video ID from YouTube URL
      const regex = /[?&]v=([^&#]+)/;
      const match = url.match(regex);

      return match ? match[1] : null;
    },
  },
};
</script>

<style scoped>
/* 스타일 코드는 동일하게 유지 */
</style>  


<style scoped>
.rounded {
  justify-content: center;
  width: 90%;
  background-color: #e5f3f3;
  padding: 20px;
  line-height: 1rem;
  border-radius: 25px;
  margin: 20px auto;
}

.box {
  margin: 20px auto;
  text-align: left;
  justify-content: center;
  background-color: #f8f8f8;
  display: flex;
  width: 90%;
  max-width: 1100px;
  height: 320px;
  align-items: baseline;
  margin-bottom: 5px;
}

.boximg {
  margin: auto;
  display: block;
  height: auto;
}

.img {
  width: 282px;
  height: 244px;
  margin: auto;
  display: block;
}

.txt_wrap {
  width: 812px;
  height: 244px;
  margin: auto;
  display: block;
}

.txt {
  text-align: left;
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  margin: auto;
  padding: 1rem;
  font-family: 'Nanum Gothic', sans-serif;
  line-height: 30px;
}

.tt>em {
  width: 25px;
  height: 25px;
  border-radius: 50%;
  background-color: rgb(208, 208, 16);
  color: white;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 1.5rem;
  margin-right: 1rem;
}

.tt>span {
  font-size: 28px;
  font-family: 'Black Han Sans', sans-serif;
  font-weight: bolder;
}

.hash-tit {
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.hash-tit>span {
  margin-left: 0.5rem;
}

.hash-tit>span:before {
  content: '';
  display: inline-block;
  width: 1rem;
  height: 1rem;
  background-color: #000;
  margin-right: 0.5rem;
}

body,
ul,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.slider-2 {
  margin: 20px 100px;
  height: 500px;
  position: relative;
}

.slider-2 .slides>div {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  opacity: 0;
  transition: opacity 0.5s;
}

.slider-2 .slides>div.active {
  opacity: 1;
}

@media (max-width: 700px) {
  .slider-2 {
    height: 300px;
  }
}

.img {
  width: 288px;
  height: auto;
  height: 200px;
}

.txt_wrap {
  width: 100%;
}

.slider-2 .page-nav {
  position: absolute;
  width: 100%;
  text-align: center;
  bottom: 0;
  left: 0;
}

.slider-2 .page-nav>div {
  display: inline-block;
  width: 15px;
  height: 15px;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 2px;
  cursor: pointer;
}

.slider-2 .page-nav>div.active {
  background-color: rgba(255, 255, 255, 1);
}

.slider-2>.side-btns>div {
  position: absolute;
  top: 0;
  left: 0;
  width: 30%;
  height: 100%;
  cursor: pointer;
  z-index: 10;
}

.slider-2>.side-btns>div>span:active {
  transform: translateY(-40%);
}

.slider-2>.side-btns>div:last-child {
  left: auto;
  right: 0;
}

.slider-2>.side-btns>div>span {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: inherit;
  right: inherit;
  width: 70px;
  height: 70px;
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 100%;
  margin: 0 10px;
}

.slider-2>.side-btns>div>span>i {
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 3rem;
  color: rgba(0, 0, 0, 0.4);
  transform: translateX(-70%) translateY(-50%);
}

.slider-2>.side-btns>div:last-child>span>i {
  transform: translateX(-20%) translateY(-50%);
}
</style>  