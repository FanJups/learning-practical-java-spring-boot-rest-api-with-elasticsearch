package com.course.practicaljava.repository;

import com.course.practicaljava.entity.CarPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPromotionElasticRepository extends ElasticsearchRepository<CarPromotion, String> {

    public Page<CarPromotion> findByType(String type, Pageable pageable);

}
