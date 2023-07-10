<template>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<img src="@/assets/images/contactus2.jpg" alt="컨택트 이미지" style="width: 100%; padding-bottom: 10px;">



<div class="info">
      
     
<div class="info_box">
      
<div class="info_tit">이메일</div >
<span class="material-symbols-outlined">
alternate_email
</span>    
<p>petmily@petmily.com</p>
  
</div>
      
     
<div class="info_box">
  
<div  class="info_tit">전화번호</div >
  <div class="info_phones">
    <div class="info_phone">
      <span class="material-symbols-outlined">
      phone_in_talk
      </span>    
      <p>031-5678-1234</p>
      
    </div>
    <div class="info_phone">
      <span class="material-symbols-outlined">
      phone_iphone
      </span>
      <p>010-1234-5678</p>
    </div>
  </div>     
</div>
    
<div class="info_box">
     
<div  class="info_tit">운영시간</div >
<span class="material-symbols-outlined">
schedule
</span>       
<p>월요일-금요일: 9:00 AM - 5:00 PM</p>    
<p>토요일: 10:00 AM - 2:00 PM</p>
<p>일요일/공휴일: Closed</p>
   
</div>
    
   

  
 
</div>
</template>

<script>
export default {
    data() {
      return {
    
      };
    },
    mounted() {
      
    },
  methods: {
    
    
  }
  };
  </script>
  <style scoped>
  

.material-symbols-outlined{
  font-size:x-large;
  font-weight: bold;
  color: rgb(244, 191, 79);
}
.info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  margin: 0 auto;
  max-width: 1400px;
}
.info_tit{
  margin-bottom:15px;
  font-weight: bold;
  font-size: large;
}

.info_box {
  /* display: flex; */
  flex-direction:column;


  width: 30%;
  height: 170px;
  margin: 10px;
  padding: 20px;
  /* background-color: #f2f2f2; */
  /* border-radius: 5px; */
  text-align: center;
  text-align: center;
  align-items: center;
  border:none;border-right:0px; border-top:0px; border-left:0px; 
  border-top : 2px solid;
}

h3 {
  font-size: 20px;
  margin-bottom: 10px;
}

p {
  font-size: 16px;
  margin: 0;
}
.info_phones{
 display: flex;
}
.info_phone{

  width: 50%;
  /* text-align: center; */


  display: inline;
  /* flex-direction: column; */
  /* justify-content: space-between;
  align-items: center; */
  margin-bottom: 10px;
  /* margin:0 auto; */
}
  </style>