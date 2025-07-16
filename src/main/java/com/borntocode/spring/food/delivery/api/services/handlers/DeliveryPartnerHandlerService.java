package com.borntocode.spring.food.delivery.api.services.handlers;

import com.borntocode.spring.food.delivery.api.constants.Constant;
import com.borntocode.spring.food.delivery.api.dto.DeliveryPartnerRequest;
import com.borntocode.spring.food.delivery.api.dto.DeliveryPartnerResponse;
import com.borntocode.spring.food.delivery.api.enums.VehicleType;
import com.borntocode.spring.food.delivery.api.models.DeliveryPartner;
import com.borntocode.spring.food.delivery.api.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class DeliveryPartnerHandlerService {
    public DeliveryPartner convertDeliveryPartnerRequestToDeliveryPartner(
            DeliveryPartnerRequest deliveryPartnerRequest,
            DeliveryPartner deliveryPartner) {
        deliveryPartner.setFirstName(deliveryPartnerRequest.getFirstName());
        deliveryPartner.setLastName(deliveryPartnerRequest.getLastName());
        deliveryPartner.setUsername(deliveryPartnerRequest.getUsername());
        deliveryPartner.setPassword(deliveryPartnerRequest.getPassword());
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());
        deliveryPartner.setDateOfBirth(DateTimeUtils.convertStringToDate(deliveryPartnerRequest.getDateOfBirth()));
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setVehicleType(VehicleType.valueOf(deliveryPartnerRequest.getVehicle()));
        deliveryPartner.setPhoneNumber(deliveryPartnerRequest.getPhoneNumber());
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setAvailable(deliveryPartnerRequest.isAvailable());
        deliveryPartner.setCreatedBy(Constant.SYSTEM);
        deliveryPartner.setCreatedAt(new Date());

        return deliveryPartner;
    }

    public DeliveryPartnerResponse convertDeliveryPartnerToDeliveryPartnerResponse(DeliveryPartner deliveryPartner) {
        DeliveryPartnerResponse deliveryPartnerResponse = new DeliveryPartnerResponse();
        deliveryPartnerResponse.setId(deliveryPartner.getId());
        deliveryPartnerResponse.setFirstName(deliveryPartner.getFirstName());
        deliveryPartnerResponse.setLastName(deliveryPartner.getLastName());
        deliveryPartnerResponse.setUsername(deliveryPartner.getUsername());
        deliveryPartnerResponse.setGender(deliveryPartner.getGender());
        deliveryPartnerResponse.setDateOfBirth(deliveryPartner.getDateOfBirth().toString());
        deliveryPartnerResponse.setAddress(deliveryPartner.getAddress());
        deliveryPartnerResponse.setPhoneNumber(deliveryPartner.getPhoneNumber());
        deliveryPartnerResponse.setEmail(deliveryPartner.getEmail());
        deliveryPartnerResponse.setAvailable(deliveryPartner.isAvailable());
        deliveryPartnerResponse.setCreatedBy(deliveryPartner.getCreatedBy());
        deliveryPartnerResponse.setCreatedAt(deliveryPartner.getCreatedAt().toString());
        deliveryPartnerResponse.setUpdatedBy(deliveryPartner.getUpdatedBy());
        deliveryPartnerResponse.setUpdatedAt(deliveryPartner.getUpdatedAt().toString());

        return deliveryPartnerResponse;
    }
}
