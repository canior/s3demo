package com.datastax.demo.yadi.s3.web;

import com.amazonaws.services.s3.AmazonS3;
import com.datastax.demo.yadi.s3.service.AmazonS3Service;
import com.datastax.demo.yadi.s3.service.AssetService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected AssetService assetService;

    @MockBean
    protected AmazonS3Service amazonS3Service;

    @MockBean
    protected AmazonS3 amazonS3;

    @Before
    public void setUp() {
        Mockito.reset(assetService, amazonS3, amazonS3Service);
    }

}
