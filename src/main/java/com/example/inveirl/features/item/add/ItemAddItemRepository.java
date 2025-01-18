package com.example.inveirl.features.item.add;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ItemAddItemRepository extends JpaRepository<ItemAddItemsEntity, UUID> {

}