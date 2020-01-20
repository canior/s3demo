package com.datastax.demo.yadi.s3.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String errorMessage;

    public ErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
