package com.datastax.demo.yadi.s3.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.datastax.demo.yadi.s3.config.AmazonS3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Date;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    AmazonS3Properties amazonS3Properties;

    private Date getExpirationDate(Long timeout) {
        Date expiration = new Date();
        long milliSeconds = expiration.getTime();
        if (timeout == null) {
            milliSeconds += this.amazonS3Properties.getUrlExpirySeconds() * 1000;
        } else {
            milliSeconds += timeout * 1000;
        }
        expiration.setTime(milliSeconds);
        return expiration;
    }

    @Override
    public String getPreSignedUploadUrl(String fileName) throws URISyntaxException {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(amazonS3Properties.getBucketName(), fileName);
        request.setMethod(HttpMethod.PUT);
        request.setExpiration(this.getExpirationDate(null));
        return this.amazonS3.generatePresignedUrl(request).toURI().toASCIIString();
    }

    @Override
    public String getSignedDownloadUrl(String fileName, Long timeout) throws URISyntaxException {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(amazonS3Properties.getBucketName(), fileName);
        request.setMethod(HttpMethod.GET);
        request.setExpiration(this.getExpirationDate(timeout));
        return this.amazonS3.generatePresignedUrl(request).toURI().toASCIIString();
    }
}
