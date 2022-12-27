package com.Makeupsale.inventoryservice.Reositories;

import com.Makeupsale.inventoryservice.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
