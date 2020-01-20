package com.datastax.demo.yadi.s3.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    private final AmazonS3Properties amazonS3Properties;

    public AmazonS3Config(AmazonS3Properties amazonS3Properties) {
        this.amazonS3Properties = amazonS3Properties;
    }

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.amazonS3Properties.getApiKey(), this.amazonS3Properties.getApiSecret());
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(this.amazonS3Properties.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}
