package com.sysman.fullstack.backend.controller;

import com.sysman.fullstack.backend.model.request.MaterialRequest;
import com.sysman.fullstack.backend.model.response.MaterialResponse;
import com.sysman.fullstack.backend.service.abstract_services.IMaterialService;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/material")
public class MaterialController {
    private IMaterialService materialService;

    @GetMapping()
    public Set<MaterialResponse> getAllMaterials() {
        return materialService.findAllMaterials();
    }

    @GetMapping("/by-type")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam MaterialType type) {
        return materialService.findMaterialsByType(type);
    }

    @GetMapping("/by-date-sale")
    public Set<MaterialResponse> getMaterialsByDateSale(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSale) {
        return materialService.findMaterialsByDateSale(dateSale);
    }

    @GetMapping("/by-city")
    public Set<MaterialResponse> getMaterialsByType(@RequestParam String city) {
        return materialService.findMaterialsByCity(city);
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> saveMaterial(@RequestBody MaterialRequest request) throws BadRequestException {
        return ResponseEntity.ok(materialService.saveMaterial(request));
    }

}
