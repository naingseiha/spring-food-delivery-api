package com.borntocode.spring.food.delivery.api.services.handlers;

import com.borntocode.spring.food.delivery.api.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CashQRHandlerService {


    private final RestTemplate restTemplate;

    public CashQRHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String posingToCashApi(PaymentRequest paymentRequest) {
        try {
            final String url = "https://cash.example.com/api/payments";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer your" + "My SecretKey cashQR");

            HttpEntity<PaymentRequest> httpEntity = new HttpEntity<>(paymentRequest, headers);
            log.info("Sending payment request to Cash API: {}", paymentRequest);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            log.info("Received response from Cash API: {}", response.getBody());
            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
        }catch(Exception e) {
            log.error("Error occurred while processing payment request: {}", e.getMessage());
        }
        return null;
    }
}
