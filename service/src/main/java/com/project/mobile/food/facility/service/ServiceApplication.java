package com.project.mobile.food.facility.service;

import com.project.mobile.food.facility.csvreader.CsvReader;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.project.mobile.food.facility")
public class ServiceApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext applicationContext =
				SpringApplication.run(ServiceApplication.class, args);
	}

}
