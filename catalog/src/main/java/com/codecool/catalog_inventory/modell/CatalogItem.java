package com.codecool.catalog_inventory.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    String description;
    String imgUrl;
    @JsonIgnore
    Boolean isActive;

    @ManyToOne
    Category category;

    public CatalogItem(UUID publicId, String name, String description, String imgUrl, Boolean isActive, Category category) {
        this.publicId = publicId;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.isActive = isActive;
        this.category = category;
    }
}
