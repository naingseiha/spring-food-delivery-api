package com.borntocode.spring.food.delivery.api.services.handlers;

import com.borntocode.spring.food.delivery.api.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@Service
@Slf4j
public class KhQRHandlerService {

    private final RestTemplate restTemplate;

    public KhQRHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String posingToKhQRApi(PaymentRequest paymentRequest) {
        try {
            final String url = "https://khqr.example.com/api/payments";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer your" + "My SecretKey");

            HttpEntity<PaymentRequest> httpEntity = new HttpEntity<>(paymentRequest, headers);
            log.info("Sending payment request to KhQR API: {}", paymentRequest);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            log.info("Received response from KhQR API: {}", response.getBody());
            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
        }catch (Exception e) {
            log.error("Error posting to payment gateway", e);
        }

        return null;

    }
}
