<template>
  <div>
    <h1>분양게시판 페이지</h1>
    <MemberList />
    <AdoptList />
  </div>
</template>

<script>
import AdoptList from '@/components/adopt/AdoptList.vue'
import MemberList from '@/components/adopt/MemberList.vue'

export default {
  name: 'AdoptHome',
  components: {
    AdoptList, MemberList
  }
}
</script>
