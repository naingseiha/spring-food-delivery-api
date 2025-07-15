package com.borntocode.spring.food.delivery.api.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_menu_item")
public class MenuItem extends Serializers.Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private double price;
    private Integer availability;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}
