package com.datastax.demo.yadi.s3.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmazonS3ServiceTest {

    @MockBean
    protected AmazonS3Service amazonS3Service;

    @Before
    public void setUp() {
        Mockito.reset(amazonS3Service);
    }

    @Test
    public void getPreSignedUploadUrlTest() throws URISyntaxException {
        String fileName = String.valueOf(new Random().nextLong());
        String uploadUrl = "http://this.is.an.upload.url";
        when(amazonS3Service.getPreSignedUploadUrl(fileName)).thenReturn(uploadUrl);
        assertThat(amazonS3Service.getPreSignedUploadUrl(fileName), equalTo(uploadUrl));
    }

    @Test
    public void getSignedDownloadUrlTest() throws URISyntaxException {
        String fileName = String.valueOf(new Random().nextLong());
        Long timeout = new Random().nextLong();
        String downloadUrl = "http://this.is.an.download.url";
        when(amazonS3Service.getSignedDownloadUrl(fileName, timeout)).thenReturn(downloadUrl);
        assertThat(amazonS3Service.getSignedDownloadUrl(fileName, timeout), equalTo(downloadUrl));
    }
}
