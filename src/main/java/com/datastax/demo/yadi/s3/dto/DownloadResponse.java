package com.datastax.demo.yadi.s3.dto;

import lombok.Data;

@Data
public class DownloadResponse {
    private String downloadUrl;

    public DownloadResponse(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
