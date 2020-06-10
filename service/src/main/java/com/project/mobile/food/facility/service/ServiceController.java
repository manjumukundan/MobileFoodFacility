package com.project.mobile.food.facility.service;

import com.project.mobile.food.facility.core.NearestFoodTrucks;
import com.project.mobile.food.facility.csvreader.CsvReader;
import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    CsvReader csvReader;

    @Autowired
    NearestFoodTrucks nearestFoodTrucks;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value="/findfoodtrucks")
    public List<FoodTruck> getFoodTrucks(@RequestParam("lat") String latitude,
                                         @RequestParam("lng") String longitude,
                                         @RequestParam("count") String count) throws IOException {
        List<FoodTruck> csvList = csvReader.processCSV();
        return nearestFoodTrucks.findNearestTrucks(csvList, latitude, longitude, Integer.valueOf(count));
    }
}
