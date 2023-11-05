package com.course.practicaljava.datasource;

import com.course.practicaljava.entity.CarPromotion;
import com.course.practicaljava.repository.CarPromotionElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CarPromotionElasticDataSource {

    @Autowired
    private CarPromotionElasticRepository repository;

    private static final Logger LOG = LoggerFactory.getLogger(CarPromotionElasticDataSource.class);

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        repository.deleteAll();

        var carPromotions = new ArrayList<CarPromotion>();

        var promotion1 = new CarPromotion();
        promotion1.setType("bonus");
        promotion1.setDescription("Purchase in cash and get free luxury dinner");
        carPromotions.add(promotion1);

        var promotion2 = new CarPromotion();
        promotion2.setType("bonus");
        promotion2.setDescription("Purchase luxury car and get free trip to Japan");
        carPromotions.add(promotion2);

        var promotion3 = new CarPromotion();
        promotion3.setType("bonus");
        promotion3.setDescription("Purchase two cars and get 20 gram of gold");
        carPromotions.add(promotion3);

        var promotion4 = new CarPromotion();
        promotion4.setType("discount");
        promotion4.setDescription("Purchase in cash and 1% discount");
        carPromotions.add(promotion4);

        var promotion5 = new CarPromotion();
        promotion5.setType("discount");
        promotion5.setDescription("Purchase before end of month to get 1.5% discount");
        carPromotions.add(promotion5);

        var promotion6 = new CarPromotion();
        promotion6.setType("discount");
        promotion6.setDescription("Today only! We gives 0.5% additional discount");
        carPromotions.add(promotion6);

        repository.saveAll(carPromotions);

        LOG.info("Saved {} car promotions", repository.count());
    }

}
