package com.borntocode.spring.food.delivery.api.services.handlers;

import com.borntocode.spring.food.delivery.api.dto.DeviceRequest;
import com.borntocode.spring.food.delivery.api.dto.DeviceResponse;
import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;
import com.borntocode.spring.food.delivery.api.models.Device;
import com.borntocode.spring.food.delivery.api.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.borntocode.spring.food.delivery.api.Constant.SYSTEM;
import static com.borntocode.spring.food.delivery.api.Constant.USER_STATUS_ACTIVE;

@Service
@Slf4j
public class UserHandlerService {

    public void usernameHasText(String username) {
         if(StringUtils.hasText(username)) {
             return;
        }
        log.info("Username is empty or null");
        throw new IllegalArgumentException("Username must not be empty or null");
    }

    public void phoneNumberHasText(String phoneNumber) {
        if(StringUtils.hasText(phoneNumber)) {
            return;
        }
        log.info("Phone number is empty or null");
        throw new IllegalArgumentException("Phone number must not be empty or null");
    }

    public Date convertStringToDate(String dateOfBirth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            log.error("Error parsing date: {}", e.getMessage());
        }

        return new Date();
    }

    public UserResponse convertUsertoUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth() != null ? user.getDateOfBirth().toString() : null)
                .userType(user.getUserType())
                .status(user.getStatus())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .updatedBy(user.getUpdatedBy())
                .updatedAt(user.getUpdatedAt())
                .build();

    }

    public User convertUserRequestToUser(final UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setDateOfBirth(convertStringToDate(userRequest.getDateOfBirth()));
        user.setUserType(userRequest.getUserType());
        user.setStatus(USER_STATUS_ACTIVE);
        user.setCreatedBy(SYSTEM);
        user.setCreatedAt(new Date());

        return user;
    }

    public Device convertDeviceRequestToUserDevice(final User user, DeviceRequest deviceRequest) {
        Device device = new Device();
        device.setDeviceId(deviceRequest.getDeviceId());
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setDeviceModel(deviceRequest.getDeviceModel());
        device.setOsVersion(deviceRequest.getOsVersion());
        device.setTrustDevice(deviceRequest.isTrustDevice());
        device.setAppVersion(deviceRequest.getAppVersion());
        device.setUser(user);

        return device;
    }

    public DeviceResponse convertUserDeviceToDeviceResponse(final Device device) {
        return DeviceResponse.builder()
                .id(device.getId())
                .deviceId(device.getDeviceId())
                .deviceType(device.getDeviceType())
                .deviceModel(device.getDeviceModel())
                .osVersion(device.getOsVersion())
                .trustDevice(device.isTrustDevice())
                .appVersion(device.getAppVersion())
                .lastLogin(device.getLastLogin())
                .status(device.getStatus())
                .build();
    }
}
