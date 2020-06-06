package com.project.mobile.food.facility.core;

import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NearestFoodTrucks {

    Double currLatitude;
    Double currLongitude;

    public List<FoodTruck> findNearestTrucks(List<FoodTruck> csvList, String latitude, String longitude) {
        currLatitude = Double.valueOf(latitude);
        currLongitude = Double.valueOf(longitude);

//        List<FoodTruck> csvList1 = new ArrayList<>(csvList);
//        Collections.sort(csvList1, new FoodTruck.FoodTruckComparatorHaversine(currLatitude, currLongitude));
//        List<FoodTruck> res = new ArrayList<>();
//        for (FoodTruck t: csvList1) {
//            res.add(t);
//            if(res.size() == 50)
//                break;
//        }


        List<FoodTruck> csvList2 = new ArrayList<>(csvList);
        Collections.sort(csvList2, new FoodTruck.FoodTruckComparatorVincentys());
        double dist = FoodTruck.doVincentysAlgorithm(currLatitude, currLongitude);
        int k = 50;

        List<FoodTruck> ans = doBinarySearch(csvList2, dist, k);

        return ans;

    }

    private List<FoodTruck> doBinarySearch(List<FoodTruck> csvList, Double distance, int k) {
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
            Double distF = FoodTruck.doVincentysAlgorithm(truckF.getLatitude(), truckF.getLongitude());
            Double distE = FoodTruck.doVincentysAlgorithm(truckE.getLatitude(), truckE.getLongitude());

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
                while (i < k) {
                    res.add(csvList.get(i++));
                }
            }
        }

        return res;
    }
}
