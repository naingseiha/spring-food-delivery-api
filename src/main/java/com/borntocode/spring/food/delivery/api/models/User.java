package com.borntocode.spring.food.delivery.api.models;

import com.borntocode.spring.food.delivery.api.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;

    @Column(name = "gender", length = 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    private String address;

    @Column(name = "user_type")
    private UserType userType;
    private String status;
}
