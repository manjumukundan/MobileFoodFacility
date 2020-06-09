package com.project.mobile.food.facility.core;

import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NearestFoodTrucks {

    Double currLatitude;
    Double currLongitude;

    static class Location {
        Double latitude;
        Double longitude;

        Location(Double lat, Double lng){
            latitude = lat;
            longitude = lng;
        }
    }

    public List<FoodTruck> findNearestTrucks(List<FoodTruck> csvList, String latitude, String longitude, int count) {

        if(csvList.size() <= count)
            return csvList;

        currLatitude = Double.valueOf(latitude);
        currLongitude = Double.valueOf(longitude);

        // find mid point of all the available points.
        Location mid = midPoint(csvList);

        Double radius = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, mid.latitude, mid.longitude);

        // set to remove equal objects that have same latitude and longitude, filtering out those.
        Set<FoodTruck> set = new HashSet<>();
        for(FoodTruck t : csvList) {
            set.add(t);
        }

        List<FoodTruck> list = new ArrayList<>();
        list.addAll(set);

        // find all points inside and on circle of radius
        List<FoodTruck> res = new ArrayList<>();
        for (FoodTruck truck : list) {
            Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
            if (dist <= radius) {
                res.add(truck);
            }
        }

        // if the count of points is greater than what is required, reduce the dataset by reducing radius.
        if(res.size() > count) {
            while(res.size() >= count){
                radius = radius / 2;
                list = new ArrayList<>(res);
                res.clear();
                for (FoodTruck truck : list) {
                    Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
                    if (dist <= radius) {
                        res.add(truck);
                    }
                }
            }
        } else if(res.size() < count) {
            // if the count of points is lesser than what is required,
            // increase the dataset by reducing radius to see if there is more
            while(res.size() < count){
                radius = radius * 2;
                list = new ArrayList<>(res);
                res.clear();
                for (FoodTruck truck : list) {
                    Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
                    if (dist <= radius) {
                        res.add(truck);
                    }
                }
            }
        } else {
            return res;
        }

        // can sort this list to retrieve exact count.
        return list;

        // Sort list based on distance between points and user location and then get first count.
//        List<FoodTruck> csvList1 = new ArrayList<>(csvList);
//        Collections.sort(csvList1, new FoodTruck.FoodTruckComparatorHaversine(currLatitude, currLongitude));
//        List<FoodTruck> res = new ArrayList<>();
//        for (FoodTruck t: csvList1) {
//            res.add(t);
//            if(res.size() == count)
//                break;
//        }
//        return res;



        // Sort list based on distance between relative distances of points and user location.
//        List<FoodTruck> csvList2 = new ArrayList<>(csvList);
//        Collections.sort(csvList2, new FoodTruck.FoodTruckComparatorEuclidean(0.0, 0.0));
//
//        double dist = Math.sqrt((currLatitude - 0.0) * (currLatitude - 0.0) + (currLongitude - 0.0) * (currLongitude - 0.0));
//        List<FoodTruck> ans = doBinarySearch(csvList2, dist, count);
//
//        return ans;

    }

    public static Location midPoint(List<FoodTruck> csvList){
        if (csvList.size() == 1) {
            return new Location(csvList.get(0).getLatitude(), csvList.get(0).getLongitude());
        }

        Double x = 0.0;
        Double y = 0.0;
        Double z = 0.0;

        for (FoodTruck truck : csvList) {
            Double latitude = truck.getLatitude() * Math.PI / 180;
            Double longitude = truck.getLongitude() * Math.PI / 180;

            x += Math.cos(latitude) * Math.cos(longitude);
            y += Math.cos(latitude) * Math.sin(longitude);
            z += Math.sin(latitude);
        }

        int total = csvList.size();

        x = x / total;
        y = y / total;
        z = z / total;

        Double centralLongitude = Math.atan2(y, x);
        Double centralSquareRoot = Math.sqrt(x * x + y * y);
        Double centralLatitude = Math.atan2(z, centralSquareRoot);

        return new Location(centralLatitude * 180 / Math.PI, centralLongitude * 180 / Math.PI);
    }

//    private List<FoodTruck> doBinarySearch(List<FoodTruck> csvList, Double distance, int k) {
//        int l = 0, r = csvList.size() - 1, m = -1;
//        while (r >= l) {
//            m = l + (r - l) / 2;
//            Double mDist = csvList.get(m).getDistance();
//            if (mDist.equals(distance)) {
//                break;
//            } else if (mDist > distance) {
//                r = m - 1;
//            } else {
//                l = m + 1;
//            }
//
//        }
//        List<FoodTruck> res = new ArrayList<>();
//
//        int f = r;
//        int e = l;
//
//        while (res.size() != k && f > -1 && e < csvList.size()) {
//            FoodTruck truckF = csvList.get(f);
//            FoodTruck truckE = csvList.get(e);
//            Double distF = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truckF.getLatitude(), truckF.getLongitude());
//            Double distE = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truckE.getLatitude(), truckE.getLongitude());
//
//            Double diff1 = Math.abs(distance - distF);
//            Double diff2 = Math.abs(distance - distE);
//
//            if (diff1 <= diff2) {
//                res.add(csvList.get(f));
//                f--;
//            } else {
//                res.add(csvList.get(e));
//                e++;
//            }
//        }
//
//        if (res.size() == k)
//            return res;
//        else {
//            if (f > -1) {
//                int i = csvList.size() - k;
//                while (i < csvList.size()) {
//                    res.add(csvList.get(i++));
//                }
//            } else {
//                int i = 0;
//                while (i < csvList.size() && i < k) {
//                    res.add(csvList.get(i++));
//                }
//            }
//        }
//
//        return res;
//    }
}
