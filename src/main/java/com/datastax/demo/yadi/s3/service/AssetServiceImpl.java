package com.datastax.demo.yadi.s3.service;

import com.datastax.demo.yadi.s3.dto.DownloadResponse;
import com.datastax.demo.yadi.s3.dto.UploadCompletedResponse;
import com.datastax.demo.yadi.s3.dto.UploadPreparationResponse;
import com.datastax.demo.yadi.s3.exception.UploadFailedException;
import com.datastax.demo.yadi.s3.model.Asset;
import com.datastax.demo.yadi.s3.model.AssetStatus;
import com.datastax.demo.yadi.s3.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    AmazonS3Service amazonS3Service;

    @Override
    public UploadPreparationResponse prepareUpload(){
        Asset asset = new Asset();
        this.assetRepository.save(asset);
        String assetId = String.valueOf(asset.getId());
        String uploadUrl;
        try {
            uploadUrl = this.amazonS3Service.getPreSignedUploadUrl(assetId);
        } catch (URISyntaxException e) {
            throw new UploadFailedException();
        }
        return new UploadPreparationResponse(assetId, uploadUrl);
    }

    @Override
    public UploadCompletedResponse completeUpload(Long assetId) {
        Asset asset = this.assetRepository.findById(assetId).orElseThrow(IllegalArgumentException::new);
        asset.setStatus(AssetStatus.UPLOADED);
        return new UploadCompletedResponse(AssetStatus.UPLOADED.toString());
    }

    @Override
    public DownloadResponse getDownload(Long assetId, Long timeout) {
        Asset asset = this.assetRepository.findById(assetId).orElseThrow(IllegalArgumentException::new);
        if (!asset.isUploaded()) {
            throw new UploadFailedException();
        }
        String downloadUrl;
        try {
            downloadUrl = amazonS3Service.getSignedDownloadUrl(String.valueOf(assetId), timeout);
        } catch (URISyntaxException e) {
            throw new UploadFailedException();
        }
        return new DownloadResponse(downloadUrl);
    }
}
