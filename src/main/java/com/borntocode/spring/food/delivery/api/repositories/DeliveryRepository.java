package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
