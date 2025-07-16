package com.borntocode.spring.food.delivery.api.services.impl;

import com.borntocode.spring.food.delivery.api.constants.Constant;
import com.borntocode.spring.food.delivery.api.dto.DeliveryPartnerRequest;
import com.borntocode.spring.food.delivery.api.dto.DeliveryPartnerResponse;
import com.borntocode.spring.food.delivery.api.models.DeliveryPartner;
import com.borntocode.spring.food.delivery.api.repositories.DeliveryPartnerRepository;
import com.borntocode.spring.food.delivery.api.services.DeliveryPartnerService;
import com.borntocode.spring.food.delivery.api.services.handlers.DeliveryPartnerHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final DeliveryPartnerHandlerService deliveryPartnerHandlerService;

    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository, DeliveryPartnerHandlerService deliveryPartnerHandlerService) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.deliveryPartnerHandlerService = deliveryPartnerHandlerService;
    }

    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();

        deliveryPartner = deliveryPartnerHandlerService.convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest, deliveryPartner);

        log.info("Creating Delivery Partner request: {}", deliveryPartner);
        deliveryPartnerRepository.saveAndFlush(deliveryPartner);

        if(deliveryPartner.getId() == null) {
            return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
        }

        return null;
    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if (deliveryPartner.isEmpty()) {
            log.warn("Delivery Partner with id {} not found", id);
            return null;
        }

        DeliveryPartner updateDeliveryPartner = deliveryPartnerHandlerService.convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest, deliveryPartner.get());
        updateDeliveryPartner.setCreatedBy(Constant.SYSTEM);
        updateDeliveryPartner.setCreatedAt(new Date());

        deliveryPartnerRepository.saveAndFlush(updateDeliveryPartner);

        return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(updateDeliveryPartner);
    }

    @Override
    public void delete(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            log.warn("Delivery Partner with id {} not found", id);
            return;
        }
        deliveryPartnerRepository.delete(deliveryPartner.get());
    }

    @Override
    public DeliveryPartnerResponse getById(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            log.warn("Delivery Partner with id {} not found", id);
            return null;
        } else {
            return deliveryPartnerHandlerService.convertDeliveryPartnerToDeliveryPartnerResponse(deliveryPartner.get());
        }
    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {
        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAll();
        if(deliveryPartners.isEmpty()) {
            log.warn("No Delivery Partners found");
            return List.of();
        }

        return deliveryPartners.stream()
                .map(deliveryPartnerHandlerService::convertDeliveryPartnerToDeliveryPartnerResponse)
                .toList();
    }
}
