package com.course.practicaljava.api.server;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DefaultRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testWelcome() throws Exception {
        final var endpoint = "/api/welcome";
        final var requestBuilders = MockMvcRequestBuilders.get(endpoint);

        mockMvc.perform(requestBuilders).andExpectAll(status().isOk(),
                content().string(Matchers.equalToIgnoringCase("welcome to spring boot")));
    }

    @Test
    void testTime() throws Exception {
        final var endpoint = "/api/time";
        final var requestBuilders = MockMvcRequestBuilders.get(endpoint);

        var mockResult = mockMvc.perform(requestBuilders).andReturn();
        var content = mockResult.getResponse().getContentAsString();

        var contentLocalTime = LocalTime.parse(content);
        var currentLocalTime = LocalTime.now();
        var localTimeMinus30Seconds = currentLocalTime.minusSeconds(30);

        assertTrue(contentLocalTime.isAfter(localTimeMinus30Seconds)
                && contentLocalTime.isBefore(currentLocalTime));
    }

    @Test
    void testHeaderByAnnotation() throws Exception {
        final var endpoint = "/api/header-one";
        final var headerOne = "MockMvc";
        final var headerTwo = "MyJava";

        final var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT, headerOne);
        httpHeaders.add("Practical-Java", headerTwo);

        final var requestBuilders = MockMvcRequestBuilders.get(endpoint).headers(httpHeaders);

        mockMvc.perform(requestBuilders)
                .andExpectAll(
                        content().string(containsStringIgnoringCase(headerOne)),
                        content().string(containsStringIgnoringCase(headerTwo))
                );
    }

    //  @Test
    void testHeaderByRequest() {
        fail("Not yet implemented");
    }

}
