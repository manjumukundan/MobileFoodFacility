package com.project.mobile.food.facility.csvreader;

import com.opencsv.CSVReader;
import com.project.mobile.food.facility.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvReader {

    public static List<FoodTruck> processCSV() throws IOException {

        ClassLoader loader = CsvReader.class.getClassLoader();
        File file = new File(loader.getResource("static/data.csv").getFile());

        FileInputStream is = new FileInputStream(file.getPath());
        CSVReader reader = new CSVReader(new InputStreamReader(is, "UTF-8"));

        //Read all data.
        List<String[]> allRows = reader.readAll();

        List<FoodTruck> trucks = new ArrayList<FoodTruck>();
        for(String[] row : allRows){
            if(row[10].equals("APPROVED") && !row[14].equals("0") && !row[15].equals("0")){
                FoodTruck truck = new FoodTruck();
                truck.setId(row[0]);
                truck.setName(row[1]);
                truck.setType(row[2]);
                truck.setDescription(row[4]);
                truck.setAddress(row[5]);
                truck.setLot(row[8]);
                truck.setStatus(row[10]);
                truck.setItems(row[11]);
                truck.setLatitude(Double.valueOf(row[14]));
                truck.setLongitude(Double.valueOf(row[15]));
                truck.setSchedule(row[16]);
                truck.setLocation(row[23]);
                trucks.add(truck);
            }
        }

        return trucks;
    }
}
