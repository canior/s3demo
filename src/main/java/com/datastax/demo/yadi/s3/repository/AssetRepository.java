package com.datastax.demo.yadi.s3.repository;

import com.datastax.demo.yadi.s3.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {

}
