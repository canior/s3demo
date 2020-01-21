package com.datastax.demo.yadi.s3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UploadPreparationResponse {
    @JsonProperty("id")
    private String assetId;
    @JsonProperty("upload_url")
    private String uploadUrl;

    public UploadPreparationResponse(String assetId, String uploadUrl) {
        this.assetId = assetId;
        this.uploadUrl = uploadUrl;
    }
}
