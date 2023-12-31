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
public class HouseNumber {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "house_number_id_sequence",
            sequenceName = "house_number_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_number_id_sequence")
    private Long id;

    private String houseNumber;
}
