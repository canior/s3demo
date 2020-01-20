package com.datastax.demo.yadi.s3;

import com.datastax.demo.yadi.s3.config.AmazonS3Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AmazonS3Properties.class})
public class AmazonS3Application {

	public static void main(String[] args) {
		SpringApplication.run(AmazonS3Application.class, args);
	}

}
