package com.sysman.fullstack.backend.controller;

import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.service.abstract_services.IMaterialService;
import com.sysman.fullstack.backend.util.enums.MaterialType;
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

    @GetMapping()
    public Set<MaterialResponse> getAllMaterials() {
        return materialService.findAllMaterials();
    }

    @GetMapping("/type")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam MaterialType type) {
        return materialService.findMaterialsByType(type);
    }

    @GetMapping("/date")
    public Set<MaterialResponse> getMaterialsByDatePurchase(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePurchase) {
        return materialService.findMaterialsByDatePurchase(datePurchase);
    }

    @GetMapping("/city?cityId")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam Long cityId) {
        return materialService.findMaterialsByCity(cityId);
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> saveMaterial(@RequestBody MaterialRequest request) throws BadRequestException {
        return ResponseEntity.ok(materialService.saveMaterial(request));
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialResponse> updateMaterial(
            @Valid @Validated @PathVariable Long materialId,
            @RequestBody MaterialRequest request) throws BadRequestException {
        return ResponseEntity.ok(materialService.updateMaterial(materialId, request));
    }

    @GetMapping("/name-type")
    public Set<String> getTypeMaterials() {
        return materialService.findTypeMaterials();
    }

}
