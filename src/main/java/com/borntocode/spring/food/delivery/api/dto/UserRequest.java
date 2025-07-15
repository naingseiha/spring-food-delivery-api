package com.borntocode.spring.food.delivery.api.dto;

import com.borntocode.spring.food.delivery.api.enums.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
    private Long id;
    private String username;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @JsonProperty("user_type")
    private UserType userType;
    private String status;
    @JsonProperty("device_info")
    private DeviceRequest deviceRequest;

    @Getter
    @Setter
    public static class DeviceRequest {
        @JsonProperty("device_id")
        private String deviceId;

        @JsonProperty("device_type")
        private String deviceType;

        @JsonProperty("device_model")
        private String deviceModel;

        @JsonProperty("os_version")
        private String osVersion;

        @JsonProperty("app_version")
        private String appVersion;

        @JsonProperty("trust_device")
        private boolean trustDevice;
    }
}
