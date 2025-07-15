package com.borntocode.spring.food.delivery.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "tbl_feedback")
public class FeedBack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", length = 200)
    private String comment;
    private Double rating;
    private Date feedbackDate;
    private Long userId;
    private Long restaurantId;
    private Long orderId;
    private Long deliveryId;
}
