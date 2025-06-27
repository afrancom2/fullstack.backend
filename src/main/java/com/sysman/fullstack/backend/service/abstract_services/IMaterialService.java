package com.sysman.fullstack.backend.service.abstract_services;

import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.Set;

public interface IMaterialService {
    Set<MaterialResponse> findAllMaterials();

    Set<MaterialResponse> findMaterialsByType(MaterialType materialType);

    Set<MaterialResponse> findMaterialsByDatePurchase(LocalDate datePurchase);

    Set<MaterialResponse> findMaterialsByCity(Long cityId);

    MaterialResponse saveMaterial(MaterialRequest material) throws BadRequestException;

    MaterialResponse updateMaterial(Long materialId, MaterialRequest material) throws BadRequestException;

    Set<String> findTypeMaterials();
}
