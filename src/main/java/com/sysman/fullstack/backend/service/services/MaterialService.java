package com.sysman.fullstack.backend.service.services;

import com.sysman.fullstack.backend.entity.City;
import com.sysman.fullstack.backend.entity.MaterialEntity;
import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.CityResponse;
import com.sysman.fullstack.backend.model.response.DepartmentResponse;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.repository.CityRepository;
import com.sysman.fullstack.backend.repository.MaterialRepository;
import com.sysman.fullstack.backend.service.abstract_services.IMaterialService;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import com.sysman.fullstack.backend.util.exception.ForbiddenException;
import com.sysman.fullstack.backend.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialService implements IMaterialService {
    private final MaterialRepository materialRepository;
    private final CityRepository cityRepository;

    @Override
    public Set<MaterialResponse> findAllMaterials() {
        log.info("Find all materials Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findAll().stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find all materials Finish Service");
        return materialResponseSet;
    }

    @Override
    public Set<MaterialResponse> findMaterialsByType(MaterialType materialType) {
        log.info("Find materials by type Init Service");
        log.info("Find materials by type material Type: " + materialType);
        Set<MaterialResponse> materialResponseSet = materialRepository.findByType(materialType).stream().map(this::entityToResponse).collect(Collectors.toSet());
        if (materialResponseSet.isEmpty()) throw new ForbiddenException();
        log.info("Find materials by type {}", materialResponseSet.stream().findFirst());
        log.info("Find materials by type Finish Service");
        return materialResponseSet;
    }

    @Override
    public Set<MaterialResponse> findMaterialsBySalePurchase(LocalDate salePurchase) {
        log.info("Find materials by date sale Init Service");
        log.info("Find materials by date sale Service salePurchase: " + salePurchase);
        Set<MaterialEntity> materialEntitySet = materialRepository.findByDatePurchase(salePurchase).orElseThrow(() -> new NotFoundException("No data for this date"));
        if (materialEntitySet.isEmpty()) {
            log.warn("No materials found for date: {}", salePurchase);
            throw new NotFoundException("No materials found for the given sale purchase");
        }
        log.info("Find materials by date sale Finish Service");
        return materialEntitySet.stream().map(this::entityToResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<MaterialResponse> findMaterialsByCity(Long city) {
        log.info("Find materials by city Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findByCity_Id(city).stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find materials by city Finish Service");
        return materialResponseSet;
    }

    @Override
    @Transactional
    public MaterialResponse saveMaterial(MaterialRequest material) throws BadRequestException {
        log.info("Save material Init Service");

        log.info("Save material Service validate dates start");
        if (material.getDatePurchase() != null && material.getDateSale() != null &&
                material.getDatePurchase().isAfter(material.getDateSale())) {
            throw new BadRequestException("The purchase date cannot be after the sale date.");
        }
        log.info("Save material Service validate dates end");

        Long cityId = material.getCity();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException("City with id " + cityId + " not found"));

        MaterialEntity materialEntity = MaterialEntity.builder()
                .name(material.getName())
                .description(material.getDescription())
                .type(material.getType())
                .price(material.getPrice())
                .type(material.getType())
                .datePurchase(material.getDatePurchase())
                .dateSale(material.getDateSale())
                .status(material.getStatus())
                .city(city)
                .build();
        log.info("Save material DB Service");
        materialRepository.saveAndFlush(materialEntity);
        log.info("Save material Finish Service");
        return entityToResponse(materialEntity);
    }

    @Override
    @Transactional
    public MaterialResponse updateMaterial(Long materialId, MaterialRequest material) throws BadRequestException {
        log.info("Update material Init Service");

        Long cityId = material.getCity();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException("City with id " + cityId + " not found"));

        if (material.getDatePurchase() != null && material.getDateSale() != null &&
                material.getDatePurchase().isAfter(material.getDateSale())) {
            throw new BadRequestException("The purchase date cannot be after the sale date.");
        }

        MaterialEntity materialEntity = MaterialEntity.builder()
                .name(material.getName())
                .description(material.getDescription())
                .type(material.getType())
                .price(material.getPrice())
                .type(material.getType())
                .status(material.getStatus())
                .datePurchase(material.getDatePurchase())
                .dateSale(material.getDateSale())
                .city(city)
                .build();

        log.info("Update material DB Service");
        materialRepository.saveAndFlush(materialEntity);
        log.info("Update material Finish Service");
        return entityToResponse(materialEntity);
    }

    @Override
    public Set<String> findTypeMaterials() {
        return Arrays.stream(MaterialType.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    private MaterialResponse entityToResponse(MaterialEntity material) {
        MaterialResponse response = new MaterialResponse();
        BeanUtils.copyProperties(material, response);
        if (material.getCity() != null) {
            CityResponse cityResponse = new CityResponse();
            BeanUtils.copyProperties(material.getCity(), cityResponse);
            if (material.getCity().getDepartment() != null) {
                DepartmentResponse departmentResponse = new DepartmentResponse();
                BeanUtils.copyProperties(material.getCity().getDepartment(), departmentResponse);
                cityResponse.setDepartment(departmentResponse);
            }
            response.setCity(cityResponse);
        }
        return response;
    }
}
