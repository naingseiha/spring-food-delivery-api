package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
