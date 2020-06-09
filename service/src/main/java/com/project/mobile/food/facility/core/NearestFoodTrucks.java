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
        currLatitude = Double.valueOf(latitude);
        currLongitude = Double.valueOf(longitude);

        Location mid = midPoint(csvList);

        if(csvList.size() <= count)
            return csvList;

        Double radius = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, mid.latitude, mid.longitude);

        // set to remove equal objects that have same latitude and longitude.
        Set<FoodTruck> set = new HashSet<>();
        for(FoodTruck t : csvList) {
            set.add(t);
        }

        List<FoodTruck> list = new ArrayList<>();
        for(FoodTruck t : set) {
            list.add(t);
        }


        List<FoodTruck> res = new ArrayList<>();
        for (FoodTruck truck : list) {
            Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
            if (dist <= radius) {
                res.add(truck);
            }
        }

        if(res.size() > count) {
            // try to reduce dataset
            radius = radius / 2;
            list = new ArrayList<>(res);
            res.clear();
            while(list.size() >= count){
                for (FoodTruck truck : list) {
                    Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
                    if (dist <= radius ) {
                        res.add(truck);
                    }
                }
                if(res.size() >= count){
                    radius = radius / 2;
                    list = new ArrayList<>(res);
                    res.clear();
                } else {
                    break;
                }
            }
        } else {
            radius = radius * 2;
            list = new ArrayList<>(res);
            res.clear();
            while(list.size() < count) {
                for (FoodTruck truck : list) {
                    Double dist = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truck.getLatitude(), truck.getLongitude());
                    if (dist <= radius) {
                        res.add(truck);
                    }
                }
                if (res.size() < count) {
                    radius = radius / 2;
                    list = new ArrayList<>(res);
                    res.clear();
                } else {
                    break;
                }
            }
        }

        // i can sort this list to retrieve exact count.
        return list;

//        List<FoodTruck> csvList1 = new ArrayList<>(csvList);
//        Collections.sort(csvList1, new FoodTruck.FoodTruckComparatorEuclidean(currLatitude, currLongitude));
//        List<FoodTruck> res = new ArrayList<>();
//        for (FoodTruck t: csvList1) {
//            res.add(t);
//            if(res.size() == count)
//                break;
//        }
//        return res;


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

    /*private List<FoodTruck> doBinarySearch(List<FoodTruck> csvList, Double distance, int k) {
        int l = 0, r = csvList.size() - 1, m = -1;
        while (r >= l) {
            m = l + (r - l) / 2;
            Double mDist = csvList.get(m).getDistance();
            if (mDist.equals(distance)) {
                break;
            } else if (mDist > distance) {
                r = m - 1;
            } else {
                l = m + 1;
            }

        }
        List<FoodTruck> res = new ArrayList<>();

        int f = r;
        int e = l;

        while (res.size() != k && f > -1 && e < csvList.size()) {
            FoodTruck truckF = csvList.get(f);
            FoodTruck truckE = csvList.get(e);
//            Double distF = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truckF.getLatitude(), truckF.getLongitude());
//            Double distE = FoodTruck.doHaversineAlgorithm(currLatitude, currLongitude, truckE.getLatitude(), truckE.getLongitude());

            Double distF = Math.sqrt((truckF.getLatitude() - currLatitude) * (truckF.getLatitude() - currLatitude)
                    + (truckF.getLongitude() - currLongitude) * (truckF.getLongitude() - currLongitude));
            Double distE = Math.sqrt((truckE.getLatitude() - currLatitude) * (truckE.getLatitude() - currLatitude)
                    + (truckE.getLongitude() - currLongitude) * (truckE.getLongitude() - currLongitude));

            Double diff1 = Math.abs(distance - distF);
            Double diff2 = Math.abs(distance - distE);

            if (diff1 <= diff2) {
                res.add(csvList.get(f));
                f--;
            } else {
                res.add(csvList.get(e));
                e++;
            }
        }

        if (res.size() == k)
            return res;
        else {
            if (f > -1) {
                int i = csvList.size() - k;
                while (i < csvList.size()) {
                    res.add(csvList.get(i++));
                }
            } else {
                int i = 0;
                while (i < csvList.size() && i < k) {
                    res.add(csvList.get(i++));
                }
            }
        }

        return res;
    } */
}
