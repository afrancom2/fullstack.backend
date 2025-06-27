package com.sysman.fullstack.backend.controller;

import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.service.abstract_services.IMaterialService;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/material")
public class MaterialController {
    private final IMaterialService materialService;

    @Operation(summary = "Get all materials with info")
    @GetMapping()
    public Set<MaterialResponse> getAllMaterials() {
        return materialService.findAllMaterials();
    }

    @Operation(summary = "Get all materials by type")
    @GetMapping("/type")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam MaterialType type) {
        return materialService.findMaterialsByType(type);
    }

    @Operation(summary = "Get all materials by date purchase")
    @GetMapping("/date")
    public Set<MaterialResponse> getMaterialsByDatePurchase(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePurchase) {
        return materialService.findMaterialsBySalePurchase(datePurchase);
    }

    @Operation(summary = "Get all materials by city")
    @GetMapping("/city")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam Long cityId) {
        return materialService.findMaterialsByCity(cityId);
    }

    @Operation(summary = "Post material")
    @PostMapping
    public ResponseEntity<MaterialResponse> saveMaterial(@RequestBody MaterialRequest request) throws BadRequestException {
        return ResponseEntity.ok(materialService.saveMaterial(request));
    }

    @Operation(summary = "Update material")
    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialResponse> updateMaterial(
            @Valid @Validated @PathVariable Long materialId,
            @RequestBody MaterialRequest request) throws BadRequestException {
        return ResponseEntity.ok(materialService.updateMaterial(materialId, request));
    }

    @Operation(summary = "Get type of materials")
    @GetMapping("/name-type")
    public Set<String> getTypeMaterials() {
        return materialService.findTypeMaterials();
    }

}
