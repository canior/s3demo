package com.datastax.demo.yadi.s3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "s3", ignoreUnknownFields = false)
public class AmazonS3Properties {

    private String apiKey;
    private String apiSecret;
    private String bucketName;
    private String region;
    private Integer urlExpirySeconds;

}
