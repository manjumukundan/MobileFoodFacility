<template>
  <div>
    <br>
    <gmap-map ref="mapRef"
            :center="currentLocation"
            :zoom="18"
            style="width:100%;  height: 100vh;"
    >
      <gmap-marker
              :key="index"
              v-for="(m, index) in markers"
              :position="m.position"
              :icon="m.icon"
              @click="visit(m)"
      ></gmap-marker>

    </gmap-map>
  </div>
</template>

<script>
  import {gmapApi} from 'vue2-google-maps'
  import api from '../api'

  export default {
    name: "MapComponent",
    computed: {
      google: gmapApi,
      markerImage(){
        return require('../assets/images/food-truck.png');
      }
    },
    data() {
      return {
        // default to Montreal to keep it simple
        // change this to whatever makes sense
        currentLocation: { lat: 37.792252, lng: -122.403793 },
        markers: [],
        places: [],
        currentPlace: null
      };
    },

    mounted() {
      this.$refs.mapRef.$mapPromise.then(() => {
        this.geolocate();
      })

    },

    methods: {
      geolocate: function() {
        //let size = new this.google.maps.Size(30, 30, 0, 0);
        navigator.geolocation.getCurrentPosition(() => {
          // position.coords.latitude = 37.792252;
          // position.coords.longitude = -122.403793
          this.currentLocation = {
            lat: 37.792252,
            lng: -122.403793
          };
          const marker = {
            lat: this.currentLocation.lat,
            lng: this.currentLocation.lng
          };
          this.markers.push({
            position: marker,
          });

          this.findFoodTrucks(marker);
        });
      },
      findFoodTrucks(position) {
        api.findFoodTrucks(position).then((response) => {
          if(response && response.data){
            let trucks = response.data;
            if(trucks && trucks.length > 0){
              let size = new this.google.maps.Size(30, 30, 0, 0);
              trucks.forEach(item => {
                console.log(item.latitude + " , " + item.longitude);
                this.markers.push({
                  name: item.name,
                  position: {
                    lat: parseFloat(item.latitude),
                    lng: parseFloat(item.longitude)
                  },
                  icon: {
                    url: this.markerImage,
                    origin: new this.google.maps.Point(0, 0),
                    anchor: new this.google.maps.Point(17, 34),
                    scaledSize: size
                  }
                })
              })
            }
          }
        }).catch(error => {
          console.log(error);
        });
      },
      visit(truck){
        console.log("Visited " + truck.name);
      }
    }
  };
</script>