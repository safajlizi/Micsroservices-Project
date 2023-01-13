package com.Makeupsale.orderservice.services;

import com.Makeupsale.orderservice.data_transfer_objects.OrderRequest;

public interface OrderService {
    public String palceOrder(OrderRequest orderRequest);
}
