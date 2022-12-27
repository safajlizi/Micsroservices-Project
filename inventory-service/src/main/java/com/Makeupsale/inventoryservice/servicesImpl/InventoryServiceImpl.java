package com.Makeupsale.inventoryservice.servicesImpl;

import com.Makeupsale.inventoryservice.Reositories.InventoryRepository;
import com.Makeupsale.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepository inventoryRep;
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        return inventoryRep.findBySkuCode(skuCode).isPresent();


    }

}
