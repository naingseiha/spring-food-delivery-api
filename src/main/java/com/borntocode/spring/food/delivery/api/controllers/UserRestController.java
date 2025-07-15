package com.borntocode.spring.food.delivery.api.controllers;

import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;
import com.borntocode.spring.food.delivery.api.exceptions.ApiExceptionError;
import com.borntocode.spring.food.delivery.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(value = "/v1/users", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create(@RequestBody UserRequest userRequest) {
        try {
            log.info("Creating user {}", userRequest);
            UserResponse userResponse = userService.create(userRequest);
            return ResponseEntity.ok(userResponse);
        }catch (Exception ex) {
            log.error("Error while creating user {}", userRequest, ex);
            var apiErrorException = ApiExceptionError.builder()
                    .errorCode("400")
                    .message(ex.getLocalizedMessage())
                    .responseData(new Object())
                    .statusCode("400")
                    .build();
            return ResponseEntity.ok(apiErrorException);
        }
    }

    @PutMapping(value = "/v1/users/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        log.info("Updating user with id {}: {}", id, userRequest);

        UserResponse userResponse = userService.update(id, userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(value = "/v1/users/{id}", produces = "application/json")
    private ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        log.info("Fetching user with id {}", id);
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(value = "/v1/users", produces = "application/json")
    private ResponseEntity<List<UserResponse>> getAll() {
        log.info("Fetching all users");
        List<UserResponse> userResponses = userService.findAll();
        return ResponseEntity.ok(userResponses);
    }

    @DeleteMapping(value = "/v1/users/{id}", produces = "application/json")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting user with id {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
