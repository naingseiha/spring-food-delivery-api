package com.borntocode.spring.food.delivery.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateTimeUtils {

    private DateTimeUtils() {}

    public static Date convertStringToDate(String dateOfBirth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            log.error("Error parsing date: {}", e.getMessage());
        }

        return new Date();
    }
}
