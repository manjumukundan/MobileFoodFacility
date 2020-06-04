import Vue from 'vue'
import App from './App.vue'
import JQuery from 'jquery'
import 'bootstrap/dist/css/bootstrap.css'
import { library } from "@fortawesome/fontawesome-svg-core";
import { faTruck } from "@fortawesome/free-solid-svg-icons";
library.add(faTruck);
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

import * as VueGoogleMaps from "vue2-google-maps";
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyCLsQP5d7wRNZhCIzJqEIre8hp0kRwI0kc",
    installComponents: true
  }
});

Vue.component('font-awesome-icon', FontAwesomeIcon)
window.$ = JQuery
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
