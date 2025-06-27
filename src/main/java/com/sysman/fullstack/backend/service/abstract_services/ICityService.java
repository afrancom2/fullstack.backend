package com.sysman.fullstack.backend.service.abstract_services;

import com.sysman.fullstack.backend.entity.City;

import java.util.Set;

public interface ICityService {
    Set<City> findCityName();
}
