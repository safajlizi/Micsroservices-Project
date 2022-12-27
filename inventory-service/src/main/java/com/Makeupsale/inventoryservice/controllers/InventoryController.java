package com.Makeupsale.inventoryservice.controllers;


import com.Makeupsale.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    @Autowired
    InventoryService inventoryServ;
    @GetMapping("/{sku-Code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-Code") String skuCode){

        return inventoryServ.isInStock(skuCode);
    }
}
