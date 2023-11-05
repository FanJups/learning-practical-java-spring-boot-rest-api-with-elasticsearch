package com.course.practicaljava.api.server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CarApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @Test
    void testRandom() throws Exception {
        final var endpoint = "/api/car/v1/random";
        final var requestBuilders = MockMvcRequestBuilders.get(endpoint);
        final var mockResult = mockMvc.perform(requestBuilders).andReturn();
        final var content = mockResult.getResponse().getContentAsString();

        final var car = objectMapper.readValue(content, Car.class);

        for (int i = 0; i < 100; i++) {
            assertTrue(CarService.BRANDS.contains(car.getBrand()));
            assertTrue(CarService.COLORS.contains(car.getColor()));
        }
    }

    //  @Test
    void testEcho() {
        fail("Not yet implemented");
    }

    //  @Test
    void testRandomCarArray() {
        fail("Not yet implemented");
    }

    //  @Test
    void testCountCar() {
        fail("Not yet implemented");
    }

    @Test
    void testCreateNewCar() throws Exception {
        final var randomCar = carService.generateCar();
        final var carString = objectMapper.writeValueAsString(randomCar);
        final var endpoint = "/api/car/v1";
        final var requestBuilders = MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(carString);

        for (int i = 0; i < 100; i++) {
            assertTimeout(Duration.ofSeconds(1), () -> {
                mockMvc.perform(requestBuilders).andExpect(status().is2xxSuccessful());
            });
        }
    }

    //  @Test
    void testFindCarByID() {
        fail("Not yet implemented");
    }

    //  @Test
    void testUpdateCar() {
        fail("Not yet implemented");
    }

    //  @Test
    void testFindCarsByBrandAndColor() {
        fail("Not yet implemented");
    }

    @Test
    void testFindCarsByPath() throws Exception {
        final var size = 5;

        for (var brand : CarService.BRANDS) {
            for (var color : CarService.COLORS) {
                final var endpoint = StringUtils.join("/api/car/v1/cars/", brand, "/", color);
                final var requestBuilders = MockMvcRequestBuilders.get(endpoint).param("size", Integer.toString(size))
                        .param("page", "0");
                final var mockResult = mockMvc.perform(requestBuilders).andReturn();
                final var responseBody = mockResult.getResponse().getContentAsString();
                final var listCars = objectMapper.readValue(responseBody, new TypeReference<List<Car>>() {
                });

                assertNotNull(listCars);
                assertTrue(listCars.size() <= size);
            }
        }
    }

    //  @Test
    void testFindCarsByParam() {
        fail("Not yet implemented");
    }

    //  @Test
    void testHandleInvalidColorException() {
        fail("Not yet implemented");
    }

    //  @Test
    void testFindCarsReleasedAfter() {
        fail("Not yet implemented");
    }

    //  @Test
    void testFindCarsReleasedBefore() {
        fail("Not yet implemented");
    }

}
