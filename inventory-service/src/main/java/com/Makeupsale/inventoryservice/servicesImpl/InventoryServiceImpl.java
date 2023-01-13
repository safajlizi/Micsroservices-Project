package com.Makeupsale.inventoryservice.servicesImpl;

import com.Makeupsale.inventoryservice.Reositories.InventoryRepository;
import com.Makeupsale.inventoryservice.data_transfer_objects.InventoryResponse;
import com.Makeupsale.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepository inventoryRep;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode)  {
        log.info("wait Started");
        Thread.sleep(10000);
        log.info("wait Ended");

        return inventoryRep.findBySkuCodeIn(skuCode)
          .stream()
          .map(inventory ->
            InventoryResponse.builder().skuCode(inventory.getSkuCode())
              .isInStock(inventory.getQuantity() > 0)
              .build()
          )
          .toList();


    }

}
