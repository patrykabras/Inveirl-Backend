package com.example.inveirl.features.item.list;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ItemListItemRepository
        extends ReadOnlyRepository<ItemListItemsEntity, UUID>, JpaSpecificationExecutor<ItemListItemsEntity> {

}