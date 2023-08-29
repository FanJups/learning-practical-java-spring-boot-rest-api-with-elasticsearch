package com.course.practicaljava.service.impl;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.service.CarService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCarService implements CarService {
    @Override
    public Car generateCar() {
        var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(BRANDS.size()));
        var color = COLORS.get(ThreadLocalRandom.current().nextInt(COLORS.size()));
        var type = TYPES.get(ThreadLocalRandom.current().nextInt(TYPES.size()));

        return new Car(brand,color,type);
    }
}
