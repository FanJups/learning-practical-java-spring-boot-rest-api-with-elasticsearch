package com.course.practicaljava.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ErrorResponse {
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Jakarta")
    private ZonedDateTime timestamp;

    public ErrorResponse() {

    }

    public ErrorResponse(String message, ZonedDateTime timestamp) {
        super();
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}