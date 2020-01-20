package com.datastax.demo.yadi.s3.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Asset {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private String uploadUrl;

    private String downloadUrl;

    public boolean isUploaded() {
        return AssetStatus.UPLOADED.equals(this.status);
    }
}
