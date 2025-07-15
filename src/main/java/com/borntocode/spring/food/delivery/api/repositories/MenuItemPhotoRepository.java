package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.MenuItemPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemPhotoRepository extends JpaRepository<MenuItemPhoto, Long> {
}
