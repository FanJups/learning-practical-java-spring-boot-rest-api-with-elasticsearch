package com.course.practicaljava.api.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api")
@Tag(name = "Default REST API", description = "Documentation for Default REST API")
public class DefaultRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);


    @GetMapping("/welcome")
    @Operation(summary = "Welcome", description = "Description for API welcome")
    public String welcome(){
        var text = StringUtils.join("Hello, ","this is ","Spring Boot ","REST API");
        LOG.info(text);
        return "Welcome to Spring Boot";
    }

    @GetMapping("/time")
    public String time(){
        return LocalTime.now().toString();
    }

    @GetMapping(value = "/header-one", produces = MediaType.TEXT_PLAIN_VALUE)
    public String headerByAnnotation(@RequestHeader(name = HttpHeaders.USER_AGENT) String headerUserAgent,
                                     @RequestHeader(name = "Practical-Java", required = false) String headerPracticalJava) {
        return headerUserAgent + "        " + headerPracticalJava;
    }

    @GetMapping(value = "/header-two", produces = MediaType.TEXT_PLAIN_VALUE)
    public String headerByRequest(HttpServletRequest request) {
        var sb = new StringBuilder();
        var headersUserAgent = request.getHeaders(HttpHeaders.USER_AGENT);
        var headersPracticalJava = request.getHeaders("Practical-Java");

        sb.append(HttpHeaders.USER_AGENT + "\n");
        while (headersUserAgent.hasMoreElements()) {
            sb.append(headersUserAgent.nextElement());
            sb.append("\n");
        }

        sb.append("Practical-Java" + "\n");
        while (headersPracticalJava.hasMoreElements()) {
            sb.append(headersPracticalJava.nextElement());
            sb.append("\n");
        }

        return sb.toString();
    }

    @GetMapping("/random-error")
    public ResponseEntity<String> randomError() {
        int remainder = ThreadLocalRandom.current().nextInt() % 5;
        var body = "Kibana";

        switch (remainder) {
            case 0:
                return ResponseEntity.ok().body(body);
            case 1:
            case 2:
                return ResponseEntity.badRequest().body(body);
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
