package com.course.practicaljava.datasource;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;

@Component
public class CarElasticDataSource {

    @Autowired
    private CarElasticRepository carRepository;

    @Autowired
    @Qualifier("randomCarService")
    private CarService carService;

    private static final Logger LOG = LoggerFactory.getLogger(CarElasticDataSource.class);

    @Autowired
    @Qualifier("restTemplateElasticsearch")
    private RestTemplate restTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void populateData(){
        var url = "http://localhost:9200/practicaljava";
        var username = "elastic";
        var password = "zHbRzazN98l1_2J7s*zh";

        // set basic authentication
        var headers = new HttpHeaders();
        var authCredential = username + ":" + password;
        var authHeader = "Basic " + Base64.getEncoder().encodeToString(authCredential.getBytes());
        headers.set(HttpHeaders.AUTHORIZATION, authHeader);

        // consume API
        var entity = new HttpEntity<String>(headers);
        var response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        LOG.info(response.getBody());

        // create the cars
        var cars = new ArrayList<Car>();
        for (int i = 0; i<5000;i++) {
            cars.add(carService.generateCar());
        }

        // save the cars to elasticsearch
        carRepository.saveAll(cars);

        LOG.info("Saved {} cars", carRepository.count());

    }
}
