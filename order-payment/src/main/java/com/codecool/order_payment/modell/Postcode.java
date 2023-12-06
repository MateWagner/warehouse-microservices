package com.codecool.order_payment.modell;

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
public class Postcode {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "postcode_id_sequence",
            sequenceName = "postcode_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postcode_id_sequence")
    Long id;

    Integer postcode;
}
