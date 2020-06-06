<template>
    <gmap-map ref="mapRef" id="map"
              :center="currentLocation"
              :zoom="15"
              style="width:100%;  height: 100vh;">
    </gmap-map>
</template>

<script>
    import {gmapApi} from 'vue2-google-maps'
    import api from '../api'

    export default {
        name: "MapComponent",
        computed: {
            google: gmapApi,
            foodTruckImage() {
                return require('../assets/images/food-truck.png');
            },
            pushCartImage() {
                return require('../assets/images/push-cart.png');
            }
        },
        data() {
            return {
                currentLocation: {lat: 37.792252, lng: -122.403793},
                markers: [],
                places: [],
                currentPlace: null,
                map: null
            };
        },

        mounted() {
            this.$refs.mapRef.$mapPromise.then(() => {
                this.geolocate();
            })
        },

        methods: {
            geolocate: function () {
                navigator.geolocation.getCurrentPosition(() => {
                    // position.coords.latitude = 37.792252;
                    // position.coords.longitude = -122.403793
                    this.currentLocation = {
                        lat: 37.792252,
                        lng: -122.403793
                    };

                    this.map = new this.google.maps.Map(document.getElementById('map'), {
                        zoom: 17,
                        center: new this.google.maps.LatLng(this.currentLocation.lat, this.currentLocation.lng)
                    });
                    let infowindow = new this.google.maps.InfoWindow({
                        content: 'You are here!'
                    });

                    let marker = new this.google.maps.Marker({
                        position: this.currentLocation,
                        map: this.map,
                        title: 'You'
                    });
                    marker.addListener('click', function () {
                        infowindow.open(this.map, marker);
                    });
                    this.findFoodTrucks(this.currentLocation);
                });
            },
            findFoodTrucks(position) {
                api.findFoodTrucks(position).then((response) => {
                    if (response && response.data) {
                        let trucks = response.data;
                        if (trucks && trucks.length > 0) {
                            trucks.forEach(item => {
                                let infowindow = new this.google.maps.InfoWindow({
                                    content: item.address
                                });
                                let image;
                                if(item.type == 'Push Cart'){
                                    image = this.pushCartImage;
                                } else {
                                    image = this.foodTruckImage;
                                }
                                let marker = new this.google.maps.Marker({
                                    position: new this.google.maps.LatLng(item.latitude, item.longitude),
                                    map: this.map,
                                    title: item.name,
                                    icon: {
                                        url: image,
                                        origin: new this.google.maps.Point(0, 0),
                                        anchor: new this.google.maps.Point(17, 34),
                                        scaledSize: new this.google.maps.Size(30, 30, 0, 0)
                                    }
                                });
                                marker.addListener('click', function () {
                                    infowindow.open(this.map, marker);
                                });
                            })
                        }
                    }
                }).catch(error => {
                    console.log(error);
                });
            },
            visit(truck) {
                console.log("Visited " + truck.name);
            }
        }
    };
</script>