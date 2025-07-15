package com.borntocode.spring.food.delivery.api.services;

import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest);
    UserResponse update(Long id, UserRequest userRequest);
    UserResponse findById(Long id);
    void delete(Long id);
    List<UserResponse> findAll();
}
