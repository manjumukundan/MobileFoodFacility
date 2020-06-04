package com.project.mobile.food.facility.core;

import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class NearestFoodTrucks {

    public List<FoodTruck> findNearestTrucks(List<FoodTruck> csvList) {

        List<FoodTruck> result = new ArrayList<>();

        double fromLat = 37.792252;
        double fromLon = -122.403793;
        for(FoodTruck truck : csvList){
            Double distance = doHaversineAlgorithm(fromLat, fromLon,
                    Double.valueOf(truck.getLatitude()), Double.valueOf(truck.getLongitude()));
            truck.setDistance(distance);
        }
        Collections.sort(csvList, new FoodTruck.FoodTruckComparator());
        for(FoodTruck truck : csvList){
            result.add(truck);
//            if(result.size() == 10)
//                break;
        }
        return result;
    }

    double doHaversineAlgorithm(double lat1, double lon1,
                     double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
