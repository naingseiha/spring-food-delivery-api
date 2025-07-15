package com.borntocode.spring.food.delivery.api.models;

import com.borntocode.spring.food.delivery.api.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private Date pickupTime = new Date();

    @Temporal(TemporalType.TIME)
    private Date deliveryTime = new Date();

    private String pickupAddress;
    private String deliveryAddress;
    private double deliveryFee;
    private DeliveryStatus deliveryStatus;
    private Long deliveryPartnerId;
    private Long orderId;
}
