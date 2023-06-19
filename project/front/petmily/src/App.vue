<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    <router-link to="/diaryboardhome">입양일기게시판</router-link>
    <br>
    <router-link to="/adopt">분양게시판홈</router-link>
=======
    <router-link to="/diaryboardhome">입양일기게시판</router-link> |
    <router-link to="/kakaomaphome">카카오맵</router-link>
>>>>>>> develop
=======
    <router-link to="/diaryboardhome">입양일기게시판</router-link> |
    <router-link to="/volboardhome">봉사모집게시판</router-link>
>>>>>>> 332eb10dff6d05428f38826a8ae74405d34a0118
=======
    <router-link to="/applyform">Apply</router-link> |
    <router-link to="/api">Api</router-link>
>>>>>>> 0801ff5d1df1c063466acc099989a66b609aee22
  </nav>
  <router-view/>
  <footer>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/diaryboardhome">입양일기게시판</router-link> |
    <router-link to="/volboardhome">봉사모집게시판</router-link>
  </footer>
</template>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
