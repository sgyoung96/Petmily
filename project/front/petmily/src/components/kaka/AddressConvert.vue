<template>
  <div class="container" style="background-color:brown">
    <div class="row">
      <div
        class="MultiCarousel"
        :data-items="dataItems"
        :data-slide="dataSlide"
        :id="carouselId"
        :data-interval="dataInterval"
        ref="carousel"
      >
        <div class="MultiCarousel-inner">
          <div class="item" v-for="item in items" :key="item.id">
            <div class="pad15">
              <p class="lead">{{ item.title }}</p>
              <p>{{ item.price }}</p>
              <p>{{ item.discount }}</p>
            </div>
          </div>
        </div>
        <button class="leftLst" @click="click(0)">1</button>
        <button class="rightLst" @click="click(1)">2</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        { id: 1, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 2, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 3, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 4, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 5, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 6, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 7, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 8, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 9, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 10, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 11, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" },
        { id: 12, title: "Multi Item Carousel", price: "₹ 1", discount: "50% off" }
      ],
      carouselId: "MultiCarousel",
      dataItems: "1,3,5,6",
      dataSlide: "1",
      dataInterval: "1000"
    };
  },
  mounted() {
    this.resCarouselSize();

    window.addEventListener("resize", this.resCarouselSize);
  },
  methods: {
    click(ell) {
      const carouselEl = this.$refs.carousel;
      const slide = carouselEl.getAttribute("data-slide");
      this.resCarousel(ell, carouselEl, slide);
    },
    resCarouselSize() {
      const itemsMainDiv = this.$refs.carousel;
      const itemsDiv = itemsMainDiv.querySelector(".MultiCarousel-inner");
      const itemClass = ".item";
      const itemsSplit = this.dataItems.split(",");
      const bodyWidth = document.body.clientWidth;
      let itemWidth = 0;

      const incno = {
        0: itemsSplit[0],
        768: itemsSplit[1],
        992: itemsSplit[2],
        1200: itemsSplit[3]
      };

      for (const width in incno) {
        if (bodyWidth >= Number(width)) {
          itemWidth = itemsMainDiv.clientWidth / Number(incno[width]);
        }
      }

      itemsDiv.style.transform = "translateX(0px)";
      itemsDiv.style.width = `${itemWidth * itemsDiv.querySelectorAll(itemClass).length}px`;

      const leftBtn = itemsMainDiv.querySelector(".leftLst");
      const rightBtn = itemsMainDiv.querySelector(".rightLst");
      leftBtn.classList.add("over");
      rightBtn.classList.remove("over");

      itemsDiv.querySelectorAll(itemClass).forEach(item => {
        item.style.width = `${itemWidth}px`;
      });
    },
    resCarousel(e, el, s) {
      const leftBtn = el.querySelector(".leftLst");
      const rightBtn = el.querySelector(".rightLst");
      const itemsDiv = el.querySelector(".MultiCarousel-inner");
      const divStyle = window.getComputedStyle(itemsDiv);
      const transformVal = divStyle.getPropertyValue("transform");
      const values = transformVal.split(/[()]/)[1].split(",");
      const xds = Math.abs(parseInt(values[4].trim()));
      const itemWidth = itemsDiv.querySelector(".item").clientWidth;
      const itemsCondition = itemsDiv.clientWidth - el.clientWidth;
      let translateXval = "";

      if (e === 0) {
        translateXval = xds - itemWidth * s;
        rightBtn.classList.remove("over");

        if (translateXval <= itemWidth / 2) {
          translateXval = 0;
          leftBtn.classList.add("over");
        }
      } else if (e === 1) {
        translateXval = xds + itemWidth * s;
        leftBtn.classList.remove("over");

        if (translateXval >= itemsCondition - itemWidth / 2) {
          translateXval = itemsCondition;
          rightBtn.classList.add("over");
        }
      }
      itemsDiv.style.transform = `translateX(${-translateXval}px)`;
    }
  },
  beforeUnmount() {
    window.removeEventListener('resize', this.updateContainerWidth);
  }
};
</script>

<style scoped>
.MultiCarousel {
  float: left;
  overflow: hidden;
  padding: 15px;
  width: 100%;
  position: relative;
}
.MultiCarousel .MultiCarousel-inner {
  transition: 1s ease all;
  float: left;
}
.MultiCarousel .MultiCarousel-inner .item {
  float: left;
}
.MultiCarousel .MultiCarousel-inner .item > div {
  text-align: center;
  padding: 10px;
  margin: 10px;
  background: #f1f1f1;
  color: #666;
}
.MultiCarousel .leftLst,
.MultiCarousel .rightLst {
  position: absolute;
  border-radius: 50%;
  top: calc(50% - 20px);
}
.MultiCarousel .leftLst {
  left: 0;
}
.MultiCarousel .rightLst {
  right: 0;
}
.MultiCarousel .leftLst.over,
.MultiCarousel .rightLst.over {
  pointer-events: none;
  background: #ccc;
}
</style>