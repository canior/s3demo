package com.datastax.demo.yadi.s3.service;

import com.datastax.demo.yadi.s3.dto.DownloadResponse;
import com.datastax.demo.yadi.s3.dto.UploadCompletedResponse;
import com.datastax.demo.yadi.s3.dto.UploadPreparationResponse;

public interface AssetService {
    UploadPreparationResponse prepareUpload();

    UploadCompletedResponse completeUpload(Long assetId);

    DownloadResponse getDownload(Long assetId, Long timeout);
}
