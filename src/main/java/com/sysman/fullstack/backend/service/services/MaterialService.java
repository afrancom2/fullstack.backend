package com.sysman.fullstack.backend.service.services;

import com.sysman.fullstack.backend.entity.MaterialEntity;
import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.CityResponse;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.repository.MaterialRepository;
import com.sysman.fullstack.backend.service.abstract_services.IMaterialService;
import com.sysman.fullstack.backend.util.MaterialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialService implements IMaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public Set<MaterialResponse> findAllMaterials() {
        log.info("Find all materials Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findAllMaterials().stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find all materials Finish Service");
        return materialResponseSet;
    }

    @Override
    public Set<MaterialResponse> findMaterialsByType(MaterialType materialType) {
        log.info("Find materials by type Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findByType(materialType).stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find materials by type Finish Service");
        return materialResponseSet;
    }

    @Override
    public Set<MaterialResponse> findMaterialsByDateSale(Date saleDate) {
        log.info("Find materials by date sale Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findByDateSale(saleDate).stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find materials by date sale Finish Service");
        return materialResponseSet;
    }

    @Override
    public Set<MaterialResponse> findMaterialsByCity(String city) {
        log.info("Find materials by date sale Init Service");
        Set<MaterialResponse> materialResponseSet = materialRepository.findByCity_Name(city).stream().map(this::entityToResponse).collect(Collectors.toSet());
        log.info("Find materials by date sale Finish Service");
        return materialResponseSet;
    }

    @Override
    public MaterialResponse saveMaterial(MaterialRequest material) {
        log.info("Save material Init Service");
        MaterialEntity materialEntity = MaterialEntity.builder()
                .name(material.getName())
                .type(material.getType())
                .price(material.getPrice())
                .type(material.getType())
                .datePurchase(new Date())
                .dateSale(new Date())
                .status(material.getStatus())
                .build();
        log.info("Save material DB Service");
        materialRepository.saveAndFlush(materialEntity);
        log.info("Save material Finish Service");
        return entityToResponse(materialEntity);
    }

    @Override
    public MaterialResponse updateMaterial(MaterialRequest material) {
        log.info("Update material Init Service");
        MaterialEntity materialEntity = MaterialEntity.builder()
                .name(material.getName())
                .type(material.getType())
                .price(material.getPrice())
                .type(material.getType())
                .status(material.getStatus())
                .build();
        log.info("Update material DB Service");
        materialRepository.saveAndFlush(materialEntity);
        log.info("Update material Finish Service");
        return entityToResponse(materialEntity);
    }

    private MaterialResponse entityToResponse(MaterialEntity material) {
        MaterialResponse response = new MaterialResponse();
        BeanUtils.copyProperties(material, response);
        if (material.getCity() != null) {
            CityResponse cityResponse = new CityResponse();
            BeanUtils.copyProperties(material.getCity(), cityResponse);
            response.setCity(cityResponse);
        }
        return response;
    }
}
