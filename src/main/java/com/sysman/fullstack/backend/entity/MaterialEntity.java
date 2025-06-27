package com.sysman.fullstack.backend.entity;

import com.sysman.fullstack.backend.util.enums.MaterialStatus;
import com.sysman.fullstack.backend.util.enums.MaterialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private MaterialType type;
    private Double price;
    @Column(name = "date_purchase")
    private LocalDate datePurchase;
    @Column(name = "date_sale")
    private LocalDate dateSale;

    @Enumerated(EnumType.STRING)
    private MaterialStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
}
