package com.datastax.demo.yadi.s3.dto;

import lombok.Data;

@Data
public class UploadPreparationResponse {
    private String assetId;
    private String uploadUrl;

    public UploadPreparationResponse(String assetId, String uploadUrl) {
        this.assetId = assetId;
        this.uploadUrl = uploadUrl;
    }
}
