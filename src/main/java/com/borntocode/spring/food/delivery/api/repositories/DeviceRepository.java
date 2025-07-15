package com.borntocode.spring.food.delivery.api.repositories;

import com.borntocode.spring.food.delivery.api.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
