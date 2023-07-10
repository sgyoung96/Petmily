<template>
  <canvas ref="canvas"></canvas>
</template>

<script>
export default {
  name: 'ImgSl',
  mounted() {
    this.initCanvas();
    this.loadImages();
    this.createEnemy();
    this.setupKeyboardListener();
    this.main();
  },
  data() {
    return {
      spaceshipX: 0,
      spaceshipY: 0,
      keysDown: {},
      bulletList: [],
      enemyList: [],
      backgroundImage: null,
      spaceshipImage: null,
      bulletImage: null,
      enemyImage: null,
      gameoverImage: null,
      gameOver: false,
      score: 0
    };
  },
  methods: {
    initCanvas() {
      const canvas = this.$refs.canvas;
      canvas.width = 400;
      canvas.height = 700;
      this.spaceshipX = canvas.width / 2 - 32;
      this.spaceshipY = canvas.height - 64;
    },
    loadImages() {
      this.backgroundImage = new Image();
      this.backgroundImage.src = require('../../assets/images/우주배경.gif');

      this.spaceshipImage = new Image();
      this.spaceshipImage.src = require('../../assets/images/우주선.png');

      this.bulletImage = new Image();
      this.bulletImage.src = require('../../assets/images/총알.png');

      this.enemyImage = new Image();
      this.enemyImage.src = require('../../assets/images/적군1.png');

      this.gameoverImage = new Image();
      this.gameoverImage.src = require('../../assets/images/게임오버.jpg');

      // 이미지 로드 완료 이벤트 처리
      const imagesLoaded = [];
      let loadedCount = 0;

      const checkAllImagesLoaded = () => {
        loadedCount++;
        if (loadedCount === 5) {
          this.render();
        }
      };

      this.backgroundImage.onload = checkAllImagesLoaded;
      this.spaceshipImage.onload = checkAllImagesLoaded;
      this.bulletImage.onload = checkAllImagesLoaded;
      this.enemyImage.onload = checkAllImagesLoaded;
      this.gameoverImage.onload = checkAllImagesLoaded;

      imagesLoaded.push(this.backgroundImage);
      imagesLoaded.push(this.spaceshipImage);
      imagesLoaded.push(this.bulletImage);
      imagesLoaded.push(this.enemyImage);
      imagesLoaded.push(this.gameoverImage);
    },
    render() {
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext('2d');
      ctx.drawImage(this.backgroundImage, 0, 0, canvas.width, canvas.height);
      ctx.drawImage(this.spaceshipImage, this.spaceshipX, this.spaceshipY);

      for (let i = 0; i < this.bulletList.length; i++) {
        if (this.bulletList[i].alive) {
          ctx.drawImage(this.bulletImage, this.bulletList[i].x, this.bulletList[i].y);
        }
      }

      for (let i = 0; i < this.enemyList.length; i++) {
        ctx.drawImage(this.enemyImage, this.enemyList[i].x, this.enemyList[i].y);
      }

      if (this.gameOver) {
        ctx.drawImage(this.gameoverImage, 10, 100, 380, 380);
      }
    },
    main() {
      requestAnimationFrame(() => {
        if (!this.gameOver) {
          this.update();
          this.render();
          this.main();
        }
      });
    },
    setupKeyboardListener() {
      window.addEventListener('keydown', (event) => {
        event.preventDefault();
        this.keysDown[event.keyCode] = true;
      });
      window.addEventListener('keyup', (event) => {
        delete this.keysDown[event.keyCode];

        if (event.keyCode === 32) {
          this.createBullet();
        }
      });
    },
    createBullet() {
      console.log('총알 생성');
      let bullet = {
        x: this.spaceshipX + 20,
        y: this.spaceshipY,
        alive: true
      };
      this.bulletList.push(bullet);
    },
    generateRandomValue(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    createEnemy() {
      setInterval(() => {
        let enemy = {
          x: this.generateRandomValue(0, 400 - 64),
          y: 0
        };
        this.enemyList.push(enemy);
      }, 1000);
    },
    update() {
      if (39 in this.keysDown) {
        // right
        this.spaceshipX += 5;
      }
      if (37 in this.keysDown) {
        // left
        this.spaceshipX -= 5;
      }

      const canvas = this.$refs.canvas;
      if (this.spaceshipX <= 0) {
        this.spaceshipX = 0;
      }
      if (this.spaceshipX >= canvas.width - 64) {
        this.spaceshipX = canvas.width - 64;
      }

      for (let i = 0; i < this.bulletList.length; i++) {
        let bullet = this.bulletList[i];
        bullet.y -= 7;
        if(this.bulletList[i].alive){
        this.checkHit(bullet);
        }

        if (bullet.y <= 0) {
          this.bulletList.splice(i, 1);
        }
      }

      for (let i = 0; i < this.enemyList.length; i++) {
        let enemy = this.enemyList[i];
        enemy.y += 2;

        if (enemy.y >= canvas.height - 64) {
          this.gameOver = true;
          console.log('gameover');
        }

        if (enemy.y >= canvas.height) {
          this.enemyList.splice(i, 1);
        }
      }
    },
    checkHit(bullet) {
      for (let i = 0; i < this.enemyList.length; i++) {
        let enemy = this.enemyList[i];
        if (bullet.y <= enemy.y && bullet.x >= enemy.x && bullet.x <= enemy.x + 40) {
          this.score++;
          bullet.alive = false;
          this.enemyList.splice(i, 1);
        }
      }
    },
  },
};
</script>

<style scoped>
/* 필요한 스타일을 추가하세요 */
</style>