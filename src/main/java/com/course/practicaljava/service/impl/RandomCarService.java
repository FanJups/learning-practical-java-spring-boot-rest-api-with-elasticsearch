package com.course.practicaljava.service.impl;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.entity.Engine;
import com.course.practicaljava.entity.Tire;
import com.course.practicaljava.service.CarService;
import com.course.practicaljava.util.RandomDateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCarService implements CarService {
    @Override
    public Car generateCar() {
        var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(BRANDS.size()));
        var color = COLORS.get(ThreadLocalRandom.current().nextInt(COLORS.size()));
        var type = TYPES.get(ThreadLocalRandom.current().nextInt(TYPES.size()));
        var available = ThreadLocalRandom.current().nextBoolean();
        var price = ThreadLocalRandom.current().nextInt(5000, 12001);
        var firstReleaseDate = RandomDateUtil.generateRandomLocalDate();
        var randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURES.size());
        var additionalFeatures = new ArrayList<String>();

        for (int i = 0; i < randomCount; i++) {
            additionalFeatures.add(ADDITIONAL_FEATURES.get(i));
        }

        var fuel = FUELS.get(ThreadLocalRandom.current().nextInt(FUELS.size()));
        var horsePower = ThreadLocalRandom.current().nextInt(100, 221);

        var engine = new Engine();
        engine.setFuelType(fuel);
        engine.setHorsePower(horsePower);

        var tires = new ArrayList<Tire>();

        for (int i = 0; i<3;i++) {
            var tire = new Tire();
            var manufacturer = TIRE_MANUFACTURERS.get(ThreadLocalRandom.current().nextInt(TIRE_MANUFACTURERS.size()));
            var size = ThreadLocalRandom.current().nextInt(15, 18);
            var tirePrice = ThreadLocalRandom.current().nextInt(200, 401);

            tire.setManufacturer(manufacturer);
            tire.setSize(size);
            tire.setPrice(tirePrice);

            tires.add(tire);
        }

        var secretFeature = ThreadLocalRandom.current().nextBoolean() ? null : "Can fly";

        var car = new Car(brand, color, type);
        car.setAvailable(available);
        car.setPrice(price);
        car.setFirstReleaseDate(firstReleaseDate);
        car.setAdditionalFeatures(additionalFeatures);
        car.setEngine(engine);
        car.setTires(tires);
        car.setSecretFeature(secretFeature);

        return car;
    }
}
