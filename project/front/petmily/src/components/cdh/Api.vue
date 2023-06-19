<template>
    <div id="app">
  <table border="1px">
    <thead>
      <tr>
        <th>filename</th>
        <th>Care Name</th>
        <th>Happen Place</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="item in items.response.body.items.item" v-bind:key="item.careNm">
        <td><img :src="item.filename._text" :alt="item.careNm._text" /></td>
        <td>{{ item.careNm._text }}</td>
        <td>{{ item.happenPlace._text }}</td>
      </tr>
    </tbody>
  </table>
</div>
</template>

<script>
var convert = require('xml-js')
export default {
  name: 'App',
  data () {
    return {
      items: []
    }
  },
  created () {
    this.$http.get('http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?_type=json&pageNo=1&numOfRows=10&serviceKey=JkjPRne8oXZTCJTyLN9579FQZI6%2FkhepY9kJhsmdEpdiEjyDUj8HjiEo8ba4BAa8AOGXfQWZA7AAHiljNzoOBA%3D%3D')
      .then((response) => {
        var xml = response.data
        var json = convert.xml2json(xml, { compact: true })
        this.items = JSON.parse(json)
      })
  }
}
</script>
