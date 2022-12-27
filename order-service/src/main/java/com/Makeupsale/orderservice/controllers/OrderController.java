package com.Makeupsale.orderservice.controllers;

import com.Makeupsale.orderservice.data_transfer_objects.OrderRequest;
import com.Makeupsale.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderService orderServ;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderServ.palceOrder(orderRequest);
        return "Order placed Successfully";
    }
}
