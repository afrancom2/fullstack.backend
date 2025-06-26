package com.sysman.fullstack.backend.repository;

import com.sysman.fullstack.backend.entity.MaterialEntity;
import com.sysman.fullstack.backend.util.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
    Set<MaterialEntity> findByType(MaterialType type);

    Set<MaterialEntity> findByDateSale(Date dateSale);

    Set<MaterialEntity> findByCity_Name(String city);
}
