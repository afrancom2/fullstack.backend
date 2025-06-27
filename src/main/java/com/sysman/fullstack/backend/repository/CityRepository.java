package com.sysman.fullstack.backend.repository;

import com.sysman.fullstack.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
