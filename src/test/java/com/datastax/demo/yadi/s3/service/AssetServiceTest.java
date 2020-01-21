package com.datastax.demo.yadi.s3.service;

import com.datastax.demo.yadi.s3.dto.DownloadResponse;
import com.datastax.demo.yadi.s3.dto.UploadCompletedResponse;
import com.datastax.demo.yadi.s3.dto.UploadPreparationResponse;
import com.datastax.demo.yadi.s3.model.Asset;
import com.datastax.demo.yadi.s3.model.AssetStatus;
import com.datastax.demo.yadi.s3.repository.AssetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetServiceTest {

    @MockBean
    AmazonS3Service amazonS3Service;

    @Autowired
    AssetService assetService;

    @Autowired
    AssetRepository assetRepository;

    @Test
    public void prepareUploadTest() throws URISyntaxException {
        String uploadUrl = "http://this.is.an.upload.url";
        when(amazonS3Service.getPreSignedUploadUrl(anyString())).thenReturn(uploadUrl);
        UploadPreparationResponse uploadPreparationResponse = assetService.prepareUpload();
        Long assetId = Long.valueOf(uploadPreparationResponse.getAssetId());
        String actualUploadUrl = uploadPreparationResponse.getUploadUrl();
        assertThat(actualUploadUrl, equalTo(uploadUrl));
        assertNotNull("AssetId should not be null", assetId);

        Asset asset = assetRepository.findById(assetId).get();
        assertFalse("Asset is not uploaded", asset.isUploaded());
    }

    @Test
    public void completeUploadTest() {
        Asset asset = new Asset();
        assetRepository.save(asset);
        Long assetId = asset.getId();
        UploadCompletedResponse uploadCompletedResponse = assetService.completeUpload(assetId);
        assertThat(uploadCompletedResponse.getStatus(), equalTo(AssetStatus.UPLOADED.toString()));
    }

    @Test
    public void getDownloadText() throws URISyntaxException {
        Asset asset = new Asset();
        asset.setStatus(AssetStatus.UPLOADED);
        assetRepository.save(asset);
        Long assetId = asset.getId();
        Long timeout = new Random().nextLong();
        String downloadUrl = "http://this.is.an.download.url";
        when(amazonS3Service.getSignedDownloadUrl(anyString(), anyLong())).thenReturn(downloadUrl);
        DownloadResponse downloadResponse = assetService.getDownload(assetId, timeout);
        assertThat(downloadUrl, equalTo(downloadResponse.getDownloadUrl()));
    }
}
