package com.sparta.spartacoding.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ErrorResponse(String timestamp, int status, String error, String message, String path){
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public String convertToJson() throws JsonProcessingException{
        return objectMapper.writeValueAsString(this);
    }
}
