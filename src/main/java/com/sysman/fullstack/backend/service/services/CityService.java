package com.sysman.fullstack.backend.service.services;

import com.sysman.fullstack.backend.entity.City;
import com.sysman.fullstack.backend.repository.CityRepository;
import com.sysman.fullstack.backend.service.abstract_services.ICityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService implements ICityService {

    private final CityRepository cityRepository;

    public Set<City> findCityName() {
        return new HashSet<>(cityRepository.findAll());
    }
}
