<template>
    <div id="myjoin">
        <h3>join form</h3>
        id:<input type="text" v-model="id"><button v-on:click="idcheck">중복체크</button><br/>
        {{msg}}<br/>
        pwd:<input type="password" v-model="pwd"><br/>
        name:<input type="text" v-model="name"><br/>
        email:<input type="text" v-model="email"><br/>
        gender:<input name="g" type="radio" v-model="gender" value="1">남 / 
            <input name="g" type="radio" v-model="gender" value="2">여 <br/>
        phone:<input type="text" v-model="phone"><br/>
        address:<input type="text" v-model="address"><br/>
        <button v-on:click="join">가입</button>
    </div>
  </template>
 