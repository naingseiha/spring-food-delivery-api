package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
