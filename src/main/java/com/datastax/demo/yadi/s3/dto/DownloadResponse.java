package com.datastax.demo.yadi.s3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DownloadResponse {
    @JsonProperty("Download_url")
    private String downloadUrl;

    public DownloadResponse(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
