package com.borntocode.spring.food.delivery.api.services.impl;

import com.borntocode.spring.food.delivery.api.dto.PaymentRequest;
import com.borntocode.spring.food.delivery.api.services.PaymentService;
import com.borntocode.spring.food.delivery.api.services.handlers.PaymentHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHandlerService paymentHandlerService;

    public PaymentServiceImpl(PaymentHandlerService paymentHandlerService) {
        this.paymentHandlerService = paymentHandlerService;
    }

    @Override
    public String pay(PaymentRequest paymentRequest) {
        return paymentHandlerService.postingToPaymentGateway(paymentRequest);
    }

    @Override
    public String inquiry(String orderId) {
        return "";
    }
}
