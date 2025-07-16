package com.borntocode.spring.food.delivery.api.services.handlers;

import com.borntocode.spring.food.delivery.api.constants.Constant;
import com.borntocode.spring.food.delivery.api.dto.PaymentRequest;
import com.borntocode.spring.food.delivery.api.enums.PaymentMethod;
import com.borntocode.spring.food.delivery.api.enums.PaymentStatus;
import com.borntocode.spring.food.delivery.api.models.Payment;
import com.borntocode.spring.food.delivery.api.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
public class PaymentHandlerService {
    private final PaymentRepository paymentRepository;
    private final KhQRHandlerService khQRHandlerService;
    private final CashQRHandlerService cashQRHandlerService;

    public PaymentHandlerService(PaymentRepository paymentRepository, KhQRHandlerService khQRHandlerService, CashQRHandlerService cashQRHandlerService) {
        this.paymentRepository = paymentRepository;
        this.khQRHandlerService = khQRHandlerService;
        this.cashQRHandlerService = cashQRHandlerService;
    }

    public String postingToPaymentGateway(PaymentRequest paymentRequest) {
        log.info("Posting to payment gateway");

        if(Constant.BANK.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is BANK");
            String khQRServiceResponse = khQRHandlerService.posingToKhQRApi(paymentRequest);
            // Verify the response from KhQR service
            savePaymentTransaction(paymentRequest, khQRServiceResponse);
            if(StringUtils.hasText(khQRServiceResponse)) {
                return Constant.SUCCESS;
            }

            return Constant.FAILED;

        }

        if(Constant.CASH.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is NOT CARD");
            // Call cash api
            String cashQRServiceResponse = cashQRHandlerService.posingToCashApi(paymentRequest);
            // verify the response from Cash service
            savePaymentTransaction(paymentRequest, cashQRServiceResponse);
            // core system logic
            // business logic here
            if(StringUtils.hasText(cashQRServiceResponse)) {
                return Constant.SUCCESS;
            }

            return Constant.FAILED;
        }

        if(Constant.CARD.equalsIgnoreCase(paymentRequest.getPaymentMethod())) {
            log.info("Payment method is CARD");
            return Constant.SUCCESS;
        }

        log.info("Payment method is NOT BANK");
        return Constant.FAILED;
    }

    public void savePaymentTransaction(PaymentRequest paymentRequest, String response) {
        Payment payment = new Payment();

        payment.setPaymentMethod(PaymentMethod.valueOf(paymentRequest.getPaymentMethod()));
        if(StringUtils.hasText(response)) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }

        payment.setAmount(paymentRequest.getAmount());
        payment.setOrderId(Long.valueOf(paymentRequest.getOrderId()));
        payment.setCreatedAt(new Date());
        payment.setCreatedBy(Constant.SYSTEM);

        log.info("Saving payment transaction");
        paymentRepository.save(payment);
    }
}
