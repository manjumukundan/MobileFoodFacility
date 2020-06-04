<template>
  <div id="app">
    <map-component/>
  </div>
</template>

<script>
import MapComponent from './components/MapComponent.vue'
//import GoogleMapComponent from './components/GoogleMapComponent.vue'
export default {
  name: 'App',
  components: {
    MapComponent,
    //GoogleMapComponent
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
