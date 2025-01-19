package com.example.inveirl.features.inventory.add;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface InventoryAddUsersInventoriesRepository extends JpaRepository<InventoryAddUsersInventoriesEntity, UUID> {

    Optional<InventoryAddUsersInventoriesEntity> getByUserIdAndItemId(UUID userId, UUID itemId);
}