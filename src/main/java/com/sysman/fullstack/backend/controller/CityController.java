package com.sysman.fullstack.backend.controller;

import com.sysman.fullstack.backend.entity.City;
import com.sysman.fullstack.backend.service.abstract_services.ICityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final ICityService cityService;

    @Operation(summary = "Get cities name")
    @GetMapping("/name")
    public Set<City> getTypeMaterials() {
        return cityService.findCityName();
    }
}
