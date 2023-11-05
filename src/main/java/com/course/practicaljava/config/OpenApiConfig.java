package com.course.practicaljava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        var info = new Info().title("Practical Java API")
                .description("Open API documentation auto-generated from Spring")
                .version("3.0.0");

        return new OpenAPI().info(info);
    }

}
