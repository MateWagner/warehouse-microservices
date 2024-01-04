package com.codecool.warehouse.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class CatalogItem {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "catalog_item_id_sequence",
            sequenceName = "catalog_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalog_item_id_sequence")
    Long id;

    @Column(
            unique = true,
            nullable = false
    )
    UUID publicId;

    @Column(
            unique = true,
            nullable = false
    )
    String name;

    @Column(
            nullable = false
    )
    BigDecimal price;

    String description;
    String imgUrl;

    @JsonIgnore
    Boolean isActive;

    @ManyToOne
    Category category;

    public CatalogItem(UUID publicId, String name, BigDecimal price, String description, String imgUrl, Boolean isActive, Category category) {
        this.publicId = publicId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgUrl = imgUrl;
        this.isActive = isActive;
        this.category = category;
    }
}
