package com.datastax.demo.yadi.s3.service;


import java.net.URISyntaxException;

public interface AmazonS3Service {

    String getPreSignedUploadUrl(String fileName) throws URISyntaxException;

    String getSignedDownloadUrl(String fileName, Long timeout) throws URISyntaxException;
}
