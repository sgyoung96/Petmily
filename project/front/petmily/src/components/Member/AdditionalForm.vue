<template>
  <div class="additional-form-container">
    <div class="form-header">
        <div><span class="form-header-title-msg">서비스 이용을 위해 추가 정보를 기입해 주세요.</span></div>
        <span class="form-header-sub-msg">SNS 로그인시 원활한 서비스 이용을 위해 최초 1회만 작성합니다.</span>
    </div>

    <MemJoinVue />
    
  </div>

</template>

<script>
import MemJoinVue from './MemJoin.vue';

export default {
  name: 'KakaoAdditionalForm',
  data () {
    return {

        kakaoId: this.$route.query.kakaoId,
        kakaoName: this.$route.query.kakaoName,
        loginFlag: this.$route.query.loginFlag,
        name: this.$route.query.kakaoName,
    }
  },
  created: function () {
      if (sessionStorage.getItem('loginFlag') == 'kakao') {
        this.name = sessionStorage.getItem('kakaoName');
      }
  },
  watch : {
   
  },  
  methods:{
    
  },
  components: {
      MemJoinVue,
  }
  
}
</script>

<style scoped>

/* 전체 div */
.additional-form-container {
    font-family: 'IBMPlexSansKR-Regular';
    padding-bottom: 50px;
}

/* 헤더 div */
.form-header {
    padding-top: 50px;
}

/* 안내문구 1 */
.form-header-title-msg {
    font-family: 'IBMPlexSansKR-Bold';    
    font-size: 20px;
}

/* 안내문구 2 */
.form-header-sub-msg {
    font-family: 'IBMPlexSansKR-ExtraLight';    
    font-size: 12px;
    color: black;
}
</style>
