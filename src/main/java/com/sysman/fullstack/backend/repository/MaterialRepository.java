package com.sysman.fullstack.backend.repository;

import com.sysman.fullstack.backend.entity.MaterialEntity;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
    Set<MaterialEntity> findByType(MaterialType type);

    Optional<Set<MaterialEntity>> findByDateSale(LocalDate dateSale);

    Set<MaterialEntity> findByCity_Name(String city);
}
