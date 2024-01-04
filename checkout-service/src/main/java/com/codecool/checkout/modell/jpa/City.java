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
public class City {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "city_id_sequence",
            sequenceName = "city_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_sequence")
    private Long id;

    private String city;
}
