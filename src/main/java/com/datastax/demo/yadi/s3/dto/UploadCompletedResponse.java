package com.datastax.demo.yadi.s3.dto;

import lombok.Data;

@Data
public class UploadCompletedResponse {
    private String status;

    public UploadCompletedResponse(String status) {
        this.status = status;
    }
}
