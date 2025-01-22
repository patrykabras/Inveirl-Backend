package com.example.inveirl.features.inventory.list;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface InventoryListUsersInventoriesRepository extends ReadOnlyRepository<InventoryListUsersInventoriesEntity, UUID>,
        JpaSpecificationExecutor<InventoryListUsersInventoriesEntity> {

}