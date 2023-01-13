package com.Makeupsale.inventoryservice.services;

import com.Makeupsale.inventoryservice.data_transfer_objects.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException;
}
