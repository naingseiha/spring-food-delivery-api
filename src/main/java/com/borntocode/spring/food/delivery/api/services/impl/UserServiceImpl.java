package com.borntocode.spring.food.delivery.api.services.impl;

import com.borntocode.spring.food.delivery.api.dto.UserRequest;
import com.borntocode.spring.food.delivery.api.dto.UserResponse;
import com.borntocode.spring.food.delivery.api.models.Device;
import com.borntocode.spring.food.delivery.api.models.User;
import com.borntocode.spring.food.delivery.api.repositories.DeviceRepository;
import com.borntocode.spring.food.delivery.api.repositories.UserRepository;
import com.borntocode.spring.food.delivery.api.services.UserHandlerService;
import com.borntocode.spring.food.delivery.api.services.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

        User user = modelMapper.map(userRequest, User.class);
        user.setDateOfBirth(userHandlerService.convertStringToDate(userRequest.getDateOfBirth()));
        log.info("Creating new user {}", user);

        userRepository.saveAndFlush(user);

        if(user.getId() != null) {
            final UserRequest.DeviceRequest deviceRequest = userRequest.getDeviceRequest();
            Device device = new Device();
            device.setDeviceId(deviceRequest.getDeviceId());
            device.setDeviceType(deviceRequest.getDeviceType());
            device.setDeviceModel(deviceRequest.getDeviceModel());
            device.setOsVersion(deviceRequest.getOsVersion());
            device.setTrustDevice(deviceRequest.isTrustDevice());
            device.setAppVersion(deviceRequest.getAppVersion());
            device.setUser(user);

            deviceRepository.save(device);
        } else {
            throw new IllegalArgumentException("Invalid device request");
        }

        return null;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }
}
