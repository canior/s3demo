package com.datastax.demo.yadi.s3.web;

import com.datastax.demo.yadi.s3.dto.DownloadResponse;
import com.datastax.demo.yadi.s3.dto.UploadCompletedResponse;
import com.datastax.demo.yadi.s3.dto.UploadPreparationResponse;
import com.datastax.demo.yadi.s3.model.AssetStatus;
import org.junit.Test;
import org.springframework.http.MediaType;
import java.util.Random;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AmazonS3AssetApiControllerTest extends AbstractControllerTest {

    @Test
    public void shouldReturnUploadPreparationResponse() throws Exception {
        String assetId = String.valueOf(new Random().nextLong());
        String url = "http://this.is.a.test.url.com";
        UploadPreparationResponse mockUploadPreparationResponse = new UploadPreparationResponse(assetId, url);
        when(assetService.prepareUpload()).thenReturn(mockUploadPreparationResponse);
        mockMvc.perform(post("/asset")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnUploadCompletedResponse() throws Exception {
        String assetId = String.valueOf(new Random().nextLong());
        UploadCompletedResponse mockUploadCompletedResponse = new UploadCompletedResponse(AssetStatus.UPLOADED.toString());
        when(assetService.completeUpload(Long.valueOf(assetId))).thenReturn(mockUploadCompletedResponse);
        mockMvc.perform(put("/asset/" + assetId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnDownloadResponse() throws Exception {
        String assetId = String.valueOf(new Random().nextLong());
        String url = "http://this.is.a.download.url.com";
        Long timeout = new Random().nextLong();
        DownloadResponse mockDownloadResponse = new DownloadResponse(url);
        when(assetService.getDownload(Long.valueOf(assetId), timeout)).thenReturn(mockDownloadResponse);
        mockMvc.perform(get("/asset/" + assetId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
