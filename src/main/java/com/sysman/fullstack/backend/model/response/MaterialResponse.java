package com.sysman.fullstack.backend.model.response;

import com.sysman.fullstack.backend.util.MaterialStatus;
import com.sysman.fullstack.backend.util.MaterialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResponse {
    private String name;
    private String description;

    @NotNull(message = "Type is required")
    private MaterialType type;
    private Double price;
    private Date datePurchase;
    private Date dateSale;

    @NotNull(message = "Status is required")
    private MaterialStatus status;
    private CityResponse city;
}
