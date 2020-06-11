package com.project.mobile.food.facility.service;

import com.project.mobile.food.facility.core.NearestFoodTrucks;
import com.project.mobile.food.facility.model.FoodTruck;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit38ClassRunner.class)
class ServiceApplicationTest {
	static List<FoodTruck> list = new ArrayList<>();

	@BeforeAll
	public static void setup(){
		JSONParser parser = new JSONParser();

		try {
			InputStream in = ServiceApplicationTest.class.getResourceAsStream("/test-data.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Object obj = parser.parse(reader);

			JSONArray jsonArray =  (JSONArray) obj;
			for(int i = 0 ; i < jsonArray.size(); i++){
				JSONObject ob = (JSONObject) jsonArray.get(i);
				FoodTruck truck = new FoodTruck();
				truck.setName((String)ob.get("name"));
				truck.setType((String)ob.get("type"));
				truck.setLatitude((Double)ob.get("latitude"));
				truck.setLongitude((Double)ob.get("longitude"));
				list.add(truck);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindNearestTrucks1() throws IOException {
		NearestFoodTrucks nearestFoodTrucks = new NearestFoodTrucks();
		List<FoodTruck> ans = nearestFoodTrucks.findNearestTrucks(list, "37.792252", "-122.403793", 2);
		assertTrue(ans.size() >= 2);
		assertTrue(containsList(37.7922616341775, -122.403485955391)); // "id": "1181500", "name": "John's Catering #5",
	}

	@Test
	public void testFindNearestTrucks2() throws IOException {
		NearestFoodTrucks nearestFoodTrucks = new NearestFoodTrucks();
		List<FoodTruck> ans = nearestFoodTrucks.findNearestTrucks(list, "37.793929", "122.394476", 2);
		assertTrue(ans.size() >= 2);
		assertTrue(containsList(37.7938715071506, -122.394865238621)); // "id": "1181498","name": "John's Catering #5",

	}

	@Test
	public void testFindNearestTrucks3() throws IOException {
		NearestFoodTrucks nearestFoodTrucks = new NearestFoodTrucks();
		List<FoodTruck> ans = nearestFoodTrucks.findNearestTrucks(list, "37.784822", "-122.3914947", 2);
		assertTrue(ans.size() >= 2);
		assertTrue(containsList(37.7860625210972, -122.392176463949));// "id": "947734", "name": "Halal Cart of San Francisco",
	}

	public boolean containsList(Double latitude, Double longitude){
		for(FoodTruck tr : list){
			if(tr.getLatitude().equals(latitude) && tr.getLongitude().equals(longitude))
				return true;
		}
		return false;
	}
}
