package com.borntocode.spring.food.delivery.api.controllers;

import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;
import com.borntocode.spring.food.delivery.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(value = "/v1/users", consumes = "application/json", produces = "application/json")
    private ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        log.info("Creating user {}", userRequest);
        UserResponse userResponse = userService.create(userRequest);
        return ResponseEntity.ok(userResponse);
    }
}
