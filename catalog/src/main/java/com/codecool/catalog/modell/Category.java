package com.codecool.catalog.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Category {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "category_item_id_sequence",
            sequenceName = "category_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_item_id_sequence")
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
    @ManyToOne
    Category parentCategory;

    public Category(UUID publicId, String name, String description) {
        this.publicId = publicId;
        this.name = name;
        this.description = description;
    }

    public Category(UUID publicId, String name, String description, Category parentCategory) {
        this.publicId = publicId;
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
    }
}
