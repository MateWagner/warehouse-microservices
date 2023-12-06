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
public class Street {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "street_id_sequence",
            sequenceName = "street_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "street_id_sequence")
    Long id;

    String street;
}
