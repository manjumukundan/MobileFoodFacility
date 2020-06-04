<template>
    <div>
        <GmapMap ref="mapRef"
                :center="{lat:37.792252, lng:-122.403793}"
                :zoom="8"
                style="width: 1500px; height: 1500px"
        >
            <GmapMarker
                    :key="index"
                    v-for="(m, index) in markers"
                    :position="m.position"
                    :clickable="true"
                    :draggable="true"
                    @click="center=m.position"
                    :icon="m.icon"
                    :label="m.label"
            />
        </GmapMap>
    </div>
</template>

<script>
    import {gmapApi} from 'vue2-google-maps'
    export default {
        data: () => ({
            markers: [],
        }),
        mounted () {
            this.$refs.mapRef.$mapPromise.then((map) => {
               map.panTo({lat: 37.792252, lng: -122.403793});
                this.initialize();
            })

        },
        computed: {
            google: gmapApi
        },
        methods: {
            initialize () {
                this.markers = [
                    {
                        position:
                            {
                                lat: 19.636226,
                                lng: 42.806212
                            },
                        icon: {
                            url: 'https://image.flaticon.com/teams/slug/google.jpg',
                            scaledSize:  this.gmapApi && new this.gmapApi.maps.Size(28, 28),
                            labelOrigin: this.gmapApi && new this.gmapApi.maps.Point(16,-10),
                        },
                        title: 'title',
                        label: {
                            text: 'label',
                            color: "black",
                            fontWeight: "bold",
                            fontSize: "12px"
                        }
                    },
                    {
                        position:
                            {
                                lat: 18.445320,
                                lng: 47.989452
                            },
                        icon: {
                            url: 'https://image.flaticon.com/teams/slug/google.jpg',
                            scaledSize: this.gmapApi && new this.gmapApi.maps.Size(28, 28),
                            labelOrigin: this.gmapApi && new this.gmapApi.maps.Point(16,-10),
                        },
                        title: 'title',
                        label: {
                            text: 'label',
                            color: "black",
                            fontWeight: "bold",
                            fontSize: "12px"
                        }
                    },
                    {
                        position:
                            {
                                lat: 19.128580,
                                lng: 47.698405
                            },
                        icon: {
                            url: 'https://image.flaticon.com/teams/slug/google.jpg',
                            scaledSize: this.gmapApi && new this.gmapApi.maps.Size(28, 28),
                            labelOrigin: this.gmapApi && new this.gmapApi.maps.Point(16,-10),
                        },
                        title: 'title',
                        label: {
                            text: 'label',
                            color: "black",
                            fontWeight: "bold",
                            fontSize: "12px"
                        }
                    },
                    {
                        position:
                            {
                                lat: 19.881369,
                                lng: 43.367863
                            },
                        icon: {
                            url: 'https://image.flaticon.com/teams/slug/google.jpg',
                            scaledSize: this.gmapApi && new this.gmapApi.maps.Size(28, 28),
                            labelOrigin: this.gmapApi && new this.gmapApi.maps.Point(16,-10),
                        },
                        title: 'title',
                        label: {
                            text: 'label',
                            color: "black",
                            fontWeight: "bold",
                            fontSize: "12px"
                        }
                    },
                ]
            },
        },
    }
</script>

<style scoped>

</style>