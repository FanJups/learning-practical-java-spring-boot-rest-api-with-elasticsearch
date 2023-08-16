package com.course.practicaljava.api.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api")
public class DefaultRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);


    @GetMapping("/welcome")
    public String welcome(){
        var text = StringUtils.join("Hello, ","this is ","Spring Boot ","REST API");
        LOG.info(text);
        return "Welcome to Spring Boot";
    }

    @GetMapping("/time")
    public String time(){
        return LocalTime.now().toString();
    }
}
