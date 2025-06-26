package com.sysman.fullstack.backend.model.request;

import com.sysman.fullstack.backend.model.response.CityResponse;
import com.sysman.fullstack.backend.util.MaterialStatus;
import com.sysman.fullstack.backend.util.MaterialType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MaterialRequest implements Serializable {
    private String name;
    private String description;

    @NotNull(message = "Type is required")
    private MaterialType type;
    private Double price;
    private Date datePurchase;
    private Date dateSale;

    @NotNull(message = "Status is required")
    private MaterialStatus status;
    private CityRequest city;
}
