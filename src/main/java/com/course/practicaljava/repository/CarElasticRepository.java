package com.course.practicaljava.repository;

import com.course.practicaljava.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

    Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);

    List<Car> findByFirstReleaseDateAfter(LocalDate date);

    @Query(value = """
      {
          "bool": {
              "must": [
                  {
                      "range": {
                          "firstReleaseDate": {
                              "lt": "?0"
                          }
                      }
                  }
              ]
          }
      }
            """)
    List<Car> customQuery(LocalDate dateParam);
}
