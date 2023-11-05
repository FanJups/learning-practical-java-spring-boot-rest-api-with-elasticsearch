package com.course.practicaljava.api.server;

import com.course.practicaljava.entity.CarPromotion;
import com.course.practicaljava.exception.IllegalApiParamException;
import com.course.practicaljava.repository.CarPromotionElasticRepository;
import com.course.practicaljava.service.CarPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car/v1")
public class CarPromotionApi {

    @Autowired
    private CarPromotionService carPromotionService;

    @Autowired
    private CarPromotionElasticRepository carPromotionRepository;

    @GetMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarPromotion> listAvailablePromotions(
            @RequestParam(name = "type") String promotionType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        if (!carPromotionService.isValidPromotionType(promotionType)) {
            throw new IllegalApiParamException("Invalid promotion type : " + promotionType);
        }

        var pageable = PageRequest.of(page, size);
        return carPromotionRepository.findByType(promotionType, pageable).getContent();
    }

}