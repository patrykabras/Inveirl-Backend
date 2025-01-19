package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface InventoryAddItemRepository extends ReadOnlyRepository<InventoryAddItemsEntity, UUID> {

}