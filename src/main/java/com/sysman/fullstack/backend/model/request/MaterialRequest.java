package com.sysman.fullstack.backend.model.request;

import com.sysman.fullstack.backend.util.enums.MaterialStatus;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MaterialRequest implements Serializable {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    @NotNull(message = "Type is required")
    private MaterialType type;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Purchase date is required")
    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate datePurchase;

    @NotNull(message = "Sale date is required")
    @FutureOrPresent(message = "Sale date cannot be in the past")
    private LocalDate dateSale;

    @NotNull(message = "Status is required")
    private MaterialStatus status;

    @NotNull(message = "City ID is required")
    private Long city;
}
