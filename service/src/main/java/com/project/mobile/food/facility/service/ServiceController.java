package com.project.mobile.food.facility.service;

import com.project.mobile.food.facility.core.NearestFoodTrucks;
import com.project.mobile.food.facility.csvreader.CsvReader;
import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    NearestFoodTrucks nearestFoodTrucks;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value="/findfoodtrucks")
    public List<FoodTruck> getFoodTrucks() throws IOException {
        List<FoodTruck> csvList = CsvReader.processCSV("/static/data.csv");
        return nearestFoodTrucks.findNearestTrucks(csvList);
    }
}
