package com.example.fillu.sensor.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataErrorResponse {
    private String message;
    private long timestamp;

    public DataErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
