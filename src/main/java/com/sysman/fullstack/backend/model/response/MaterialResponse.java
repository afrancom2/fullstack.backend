package com.sysman.fullstack.backend.model.response;

import com.sysman.fullstack.backend.util.enums.MaterialStatus;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResponse {
    private Long id;
    private String name;
    private String description;

    @NotNull(message = "Type is required")
    private MaterialType type;
    private Double price;
    private LocalDate datePurchase;
    private LocalDate dateSale;

    @NotNull(message = "Status is required")
    private MaterialStatus status;
    private CityResponse city;
}
