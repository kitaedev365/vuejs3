<template>
  <div id="app">
    <ServiceList />
  </div>
</template>

<script>
import ServiceList from './components/ServiceList.vue';

export default {
  name: 'App',
  components: {
    ServiceList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
