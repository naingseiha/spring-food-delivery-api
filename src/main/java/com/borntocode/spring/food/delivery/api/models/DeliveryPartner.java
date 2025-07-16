package com.borntocode.spring.food.delivery.api.models;

import com.borntocode.spring.food.delivery.api.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_delivery_partner")
public class DeliveryPartner extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    private String email;
    private String address;
    private VehicleType vehicleType;
    private boolean available;
}
