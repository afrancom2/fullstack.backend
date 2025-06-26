package com.sysman.fullstack.backend.service.abstract_services;

import com.sysman.fullstack.backend.entity.City;
import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.util.MaterialType;

import java.util.Date;
import java.util.Set;

public interface IMaterialService {
    Set<MaterialResponse> findAllMaterials();
    Set<MaterialResponse> findMaterialsByType(MaterialType materialType);
    Set<MaterialResponse> findMaterialsByDateSale(Date saleDate);
    Set<MaterialResponse> findMaterialsByCity(String city);
    MaterialResponse saveMaterial(MaterialRequest material);
    MaterialResponse updateMaterial(MaterialRequest material);
}
