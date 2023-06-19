<template>
  <div>
    <h1>분양게시판 페이지</h1>
    
    <AdoptList />
  </div>
</template>

<script>
import AdoptList from '@/components/adopt/AdoptList.vue'

export default {
  name: 'AdoptHome',
  components: {
    AdoptList
  }
}
</script>
