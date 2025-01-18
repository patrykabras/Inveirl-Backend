package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ItemDetailsItemRepository extends ReadOnlyRepository<ItemDetailsItemsEntity, UUID> {

}