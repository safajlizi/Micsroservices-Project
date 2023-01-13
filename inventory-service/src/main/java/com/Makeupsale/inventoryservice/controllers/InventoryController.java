package com.Makeupsale.inventoryservice.controllers;


import com.Makeupsale.inventoryservice.data_transfer_objects.InventoryResponse;
import com.Makeupsale.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    @Autowired
    InventoryService inventoryServ;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) throws InterruptedException {

        return inventoryServ.isInStock(skuCode);
    }
}
