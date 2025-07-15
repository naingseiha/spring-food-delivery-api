package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
}
