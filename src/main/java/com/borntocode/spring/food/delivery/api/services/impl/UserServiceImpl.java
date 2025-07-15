package com.borntocode.spring.food.delivery.api.services.impl;

import com.borntocode.spring.food.delivery.api.dto.DeviceRequest;
import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;
import com.borntocode.spring.food.delivery.api.models.Device;
import com.borntocode.spring.food.delivery.api.models.User;
import com.borntocode.spring.food.delivery.api.repositories.DeviceRepository;
import com.borntocode.spring.food.delivery.api.repositories.UserRepository;
import com.borntocode.spring.food.delivery.api.services.handlers.UserHandlerService;
import com.borntocode.spring.food.delivery.api.services.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserHandlerService userHandlerService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, DeviceRepository deviceRepository,
                           UserHandlerService userHandlerService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userHandlerService = userHandlerService;
    }

    @Transactional
    @Override
    public UserResponse create(UserRequest userRequest) {
        userHandlerService.usernameHasText(userRequest.getUsername());
        userHandlerService.phoneNumberHasText(userRequest.getPhoneNumber());

        User user = userHandlerService.convertUserRequestToUser(userRequest);
        log.info("Creating new user {}", user);

        userRepository.saveAndFlush(user);

        if(user.getId() != null) {
            Device device = userHandlerService.convertDeviceRequestToUserDevice(user, userRequest.getDeviceRequest());
            log.info("Creating new device {}", device);
            deviceRepository.save(device);
        } else {
            throw new IllegalArgumentException("Invalid device request");
        }

        return null;
    }

    @Transactional
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            log.info("User update with id {} not found", id);
            this.create(userRequest);
            return null;
        }

        User userUpdate = user.get();
        userUpdate.setUsername(userRequest.getUsername());
        userUpdate.setPhoneNumber(userRequest.getPhoneNumber());
        userUpdate.setEmail(userRequest.getEmail());
        userUpdate.setDateOfBirth(userHandlerService.convertStringToDate(userRequest.getDateOfBirth()));
        userUpdate.setAddress(userRequest.getAddress());
        userUpdate.setUserType(userRequest.getUserType());
        userUpdate.setGender(userRequest.getGender());

        log.info("Updating user {}", userUpdate);
        userRepository.saveAndFlush(userUpdate);

        if(userUpdate.getDevices() != null) {
           var deviceRequest = userRequest.getDeviceRequest();
           if(StringUtils.hasText(deviceRequest.getDeviceId())) {
               List<Device> devices = userUpdate.getDevices();
               Device deviceUpdate = devices.stream().filter(device -> device.getDeviceId().equalsIgnoreCase(deviceRequest.getDeviceId()))
                       .findAny().orElse(null);

               if(deviceUpdate != null) {
                   deviceUpdate.setDeviceModel(deviceRequest.getDeviceModel());
                   deviceUpdate.setDeviceType(deviceRequest.getDeviceType());
                   deviceUpdate.setOsVersion(deviceRequest.getOsVersion());
                   deviceUpdate.setTrustDevice(deviceRequest.isTrustDevice());
                   deviceUpdate.setAppVersion(deviceRequest.getAppVersion());
                   log.info("Updating device {}", deviceUpdate);
                   deviceRepository.saveAndFlush(deviceUpdate);
               } else {
                   Device newDevice = userHandlerService.convertDeviceRequestToUserDevice(userUpdate, deviceRequest);
                   log.info("Creating new device {}", newDevice);
                   deviceRepository.save(newDevice);
               }
           }
        }

        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.info("User with id {} not found", id);
            return new UserResponse();
        }
        return userHandlerService.convertUsertoUserResponse(user.get());
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.info("User with id {} not found", id);
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
        log.info("User with id {} deleted successfully", id);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info("No users found");
            return List.of();
        }
        return users.stream()
                .map(userHandlerService::convertUsertoUserResponse).toList();

    }
}
