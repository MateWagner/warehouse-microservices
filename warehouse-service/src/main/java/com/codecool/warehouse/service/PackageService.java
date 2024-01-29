package com.codecool.warehouse.service;

import com.codecool.warehouse.dto.DeliveryRequest;


public interface PackageService {

    void prepareDelivery(DeliveryRequest deliveryRequest);

}
