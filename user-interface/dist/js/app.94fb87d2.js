(function(n){function e(e){for(var o,i,c=e[0],u=e[1],l=e[2],p=0,f=[];p<c.length;p++)i=c[p],Object.prototype.hasOwnProperty.call(r,i)&&r[i]&&f.push(r[i][0]),r[i]=0;for(o in u)Object.prototype.hasOwnProperty.call(u,o)&&(n[o]=u[o]);s&&s(e);while(f.length)f.shift()();return a.push.apply(a,l||[]),t()}function t(){for(var n,e=0;e<a.length;e++){for(var t=a[e],o=!0,c=1;c<t.length;c++){var u=t[c];0!==r[u]&&(o=!1)}o&&(a.splice(e--,1),n=i(i.s=t[0]))}return n}var o={},r={app:0},a=[];function i(e){if(o[e])return o[e].exports;var t=o[e]={i:e,l:!1,exports:{}};return n[e].call(t.exports,t,t.exports,i),t.l=!0,t.exports}i.m=n,i.c=o,i.d=function(n,e,t){i.o(n,e)||Object.defineProperty(n,e,{enumerable:!0,get:t})},i.r=function(n){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(n,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(n,"__esModule",{value:!0})},i.t=function(n,e){if(1&e&&(n=i(n)),8&e)return n;if(4&e&&"object"===typeof n&&n&&n.__esModule)return n;var t=Object.create(null);if(i.r(t),Object.defineProperty(t,"default",{enumerable:!0,value:n}),2&e&&"string"!=typeof n)for(var o in n)i.d(t,o,function(e){return n[e]}.bind(null,o));return t},i.n=function(n){var e=n&&n.__esModule?function(){return n["default"]}:function(){return n};return i.d(e,"a",e),e},i.o=function(n,e){return Object.prototype.hasOwnProperty.call(n,e)},i.p="/";var c=window["webpackJsonp"]=window["webpackJsonp"]||[],u=c.push.bind(c);c.push=e,c=c.slice();for(var l=0;l<c.length;l++)e(c[l]);var s=u;a.push([0,"chunk-vendors"]),t()})({0:function(n,e,t){n.exports=t("56d7")},"034f":function(n,e,t){"use strict";var o=t("85ec"),r=t.n(o);r.a},"56d7":function(n,e,t){"use strict";t.r(e);t("e260"),t("e6cf"),t("cca6"),t("a79d");var o=t("2b0e"),r=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",{attrs:{id:"app"}},[t("map-component")],1)},a=[],i=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("br"),t("gmap-map",{ref:"mapRef",staticStyle:{width:"100%",height:"100vh"},attrs:{center:n.currentLocation,zoom:18}},n._l(n.markers,(function(e,o){return t("gmap-marker",{key:o,attrs:{position:e.position,icon:e.icon},on:{click:function(t){return n.visit(e)}}})})),1)],1)},c=[],u=(t("4160"),t("b0c0"),t("159b"),t("755e")),l=t("bc3a"),s=t.n(l),p="http://localhost:8080",f=s.a.create({baseURL:p}),d={"Content-Type":"application/json","Access-Control-Allow-Origin":"*"},g={findFoodTrucks:function(){return f.get("/findfoodtrucks",d)}},m=g,h={name:"MapComponent",computed:{google:u["gmapApi"],markerImage:function(){return t("e5eb")}},data:function(){return{currentLocation:{lat:37.792252,lng:-122.403793},markers:[],places:[],currentPlace:null}},mounted:function(){var n=this;this.$refs.mapRef.$mapPromise.then((function(){n.geolocate()}))},methods:{geolocate:function(){var n=this;navigator.geolocation.getCurrentPosition((function(){n.currentLocation={lat:37.792252,lng:-122.403793};var e={lat:n.currentLocation.lat,lng:n.currentLocation.lng};n.markers.push({position:e}),n.findFoodTrucks(e)}))},findFoodTrucks:function(n){var e=this;m.findFoodTrucks(n).then((function(n){if(n&&n.data){var t=n.data;if(t&&t.length>0){var o=new e.google.maps.Size(30,30,0,0);t.forEach((function(n){console.log(n.latitude+" , "+n.longitude),e.markers.push({name:n.name,position:{lat:parseFloat(n.latitude),lng:parseFloat(n.longitude)},icon:{url:e.markerImage,origin:new e.google.maps.Point(0,0),anchor:new e.google.maps.Point(17,34),scaledSize:o}})}))}}})).catch((function(n){console.log(n)}))},visit:function(n){console.log("Visited "+n.name)}}},v=h,b=t("2877"),y=Object(b["a"])(v,i,c,!1,null,null,null),k=y.exports,w={name:"App",components:{MapComponent:k}},O=w,P=(t("034f"),Object(b["a"])(O,r,a,!1,null,null,null)),j=P.exports,_=t("1157"),x=t.n(_),S=(t("f9e3"),t("ecee")),C=t("c074"),T=t("ad3d");S["c"].add(C["a"]),o["a"].use(u,{load:{key:"AIzaSyCLsQP5d7wRNZhCIzJqEIre8hp0kRwI0kc",installComponents:!0}}),o["a"].component("font-awesome-icon",T["a"]),window.$=x.a,o["a"].config.productionTip=!1,new o["a"]({render:function(n){return n(j)}}).$mount("#app")},"85ec":function(n,e,t){},e5eb:function(n,e,t){n.exports=t.p+"img/food-truck.7d6d47a6.png"}});
//# sourceMappingURL=app.94fb87d2.js.map