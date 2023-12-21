package com.codecool.checkout.modell.jpa;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AreaName {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "area_name_id_sequence",
            sequenceName = "area_name_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_name_id_sequence")
    private Long id;

    private String areaName;
}
