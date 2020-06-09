package com.project.mobile.food.facility.model;

import java.util.Comparator;

public class FoodTruck {
    private String id;
    private String name;
    private String type;
    private String address;
    private String description;
    private String lot;
    private String status;
    private String items;
    private String x;
    private String y;
    private Double latitude;
    private Double longitude;
    private String location;
    private String schedule;
    private String scheduleUrl;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private Double distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public static double doHaversineAlgorithm(double lat1, double lon1, double lat2, double lon2)
    {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);

        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    public static class FoodTruckComparatorEuclidean implements Comparator<FoodTruck> {

        Double lat = 0.0;
        Double lng = 0.0;

        public FoodTruckComparatorEuclidean(Double latitude, Double longitude) {
            this.lat = latitude;
            this.lng = longitude;
        }

        @Override
        public int compare(FoodTruck o1, FoodTruck o2) {
            Double d1Lat = Double.valueOf(o1.getLatitude());
            Double d1Lng = Double.valueOf(o1.getLongitude());
            Double d2Lat = Double.valueOf(o2.getLatitude());
            Double d2Lng = Double.valueOf(o2.getLongitude());


            Double distance1 = Math.sqrt((d1Lat - lat) * (d1Lat - lat) + (d1Lng - lng) * (d1Lng - lng));
            o1.setDistance(distance1);
            Double distance2 =  Math.sqrt((d2Lat - lat) * (d2Lat - lat) + (d2Lng - lng) * (d2Lng - lng));
            o2.setDistance(distance2);

            return distance1.compareTo(distance2);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof FoodTruck)) {
            return false;
        }

        FoodTruck c = (FoodTruck) o;

        return Double.compare(latitude, c.latitude) == 0
                && Double.compare(longitude, c.longitude) == 0;

    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    public static class FoodTruckComparatorHaversine implements Comparator<FoodTruck> {

        Double lat = 0.0;
        Double lng = 0.0;

        public FoodTruckComparatorHaversine(Double latitude, Double longitude) {
            this.lat = latitude;
            this.lng = longitude;
        }

        @Override
        public int compare(FoodTruck o1, FoodTruck o2) {
            Double d1Lat = Double.valueOf(o1.getLatitude());
            Double d1Lng = Double.valueOf(o1.getLongitude());
            Double d2Lat = Double.valueOf(o2.getLatitude());
            Double d2Lng = Double.valueOf(o2.getLongitude());

            Double distance1 = doHaversineAlgorithm(lat, lng, d1Lat, d1Lng);
            o1.setDistance(distance1);
            Double distance2 = doHaversineAlgorithm(lat, lng, d2Lat, d2Lng);
            o2.setDistance(distance2);

            return distance1.compareTo(distance2);
        }
    }

}
