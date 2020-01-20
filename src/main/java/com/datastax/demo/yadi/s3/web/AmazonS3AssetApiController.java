package com.datastax.demo.yadi.s3.web;

import com.datastax.demo.yadi.s3.dto.DownloadResponse;
import com.datastax.demo.yadi.s3.dto.UploadCompletedResponse;
import com.datastax.demo.yadi.s3.dto.UploadPreparationResponse;
import com.datastax.demo.yadi.s3.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class AmazonS3AssetApiController {

    private static final Logger log = LoggerFactory.getLogger(AmazonS3AssetApiController.class);

    @Autowired
    AssetService assetService;


    @PostMapping("/asset")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UploadPreparationResponse prepareUpload() {
        log.info("received s3 upload request");
        UploadPreparationResponse response =  assetService.prepareUpload();
        log.info("responded {}", response);
        return response;
    }

    @PutMapping("/asset/{assetId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UploadCompletedResponse completeUpload(@PathVariable Long assetId) {
        log.info("received complete upload request for asset: {}", assetId);
        UploadCompletedResponse response = assetService.completeUpload(assetId);
        log.info("responded {}", response);
        return response;
    }

    @GetMapping("/asset/{assetId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DownloadResponse getDownload(@PathVariable Long assetId, @RequestParam(required=false) Long timeout) {
        log.info("received download request for asset: {}", assetId);
        DownloadResponse response = assetService.getDownload(assetId, timeout);
        log.info("responded {}", response);
        return response;
    }
}
