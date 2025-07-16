package com.borntocode.spring.food.delivery.api.services;

import com.borntocode.spring.food.delivery.api.dto.PaymentRequest;

public interface PaymentService {
    String pay(PaymentRequest paymentRequest);
    String inquiry(String orderId);
}
