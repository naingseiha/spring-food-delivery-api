package com.borntocode.spring.food.delivery.api.services;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
