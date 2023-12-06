package com.codecool.order_payment.modell;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "address_id_sequence",
            sequenceName = "address_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
    Long id;

    UUID publicID;

    UUID userPID;

    @OneToOne
    Postcode postcode;

    @OneToOne
    City city;

    @OneToOne
    Street street;

    @OneToOne
    AreaName areaName;

    @OneToOne
    HouseNumber houseNumber;

    Boolean isPreferred;
}
