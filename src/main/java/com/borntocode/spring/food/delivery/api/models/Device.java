package com.borntocode.spring.food.delivery.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_device")
public class Device extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String deviceType;
    private String deviceModel;
    private String osVersion;
    private String appVersion;
    private Date lastLogin;
    private boolean trustDevice;
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
