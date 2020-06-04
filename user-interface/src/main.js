import Vue from 'vue'
import App from './App.vue'
import JQuery from 'jquery'
window.$ = JQuery
import 'bootstrap/dist/css/bootstrap.css'

import * as VueGoogleMaps from "vue2-google-maps";
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyCLsQP5d7wRNZhCIzJqEIre8hp0kRwI0kc",
    installComponents: true
  }
});
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
