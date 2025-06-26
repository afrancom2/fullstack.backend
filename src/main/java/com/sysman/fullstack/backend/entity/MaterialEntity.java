package com.sysman.fullstack.backend.entity;

import com.sysman.fullstack.backend.util.MaterialStatus;
import com.sysman.fullstack.backend.util.MaterialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date datePurchase;
    @Column(name = "date_sale")
    private Date dateSale;

    @Enumerated(EnumType.STRING)
    private MaterialStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;
}
