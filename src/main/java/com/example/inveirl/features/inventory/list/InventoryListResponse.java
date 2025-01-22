package com.example.inveirl.features.inventory.list;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import com.example.inveirl.infrastructure.enumeration.QuantityType;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class InventoryListResponse {

    private UUID itemId;
    private String name;
    private ItemType itemType;
    private QuantityType quantityType;
    private float quantity;
    private boolean exists;
    private ZonedDateTime expirationDate;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;
}
