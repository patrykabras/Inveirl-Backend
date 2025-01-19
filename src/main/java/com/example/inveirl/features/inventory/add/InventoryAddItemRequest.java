package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.enumeration.QuantityType;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
class InventoryAddItemRequest {

    private UUID itemId;
    private QuantityType quantityType;
    private float quantity;
    private ZonedDateTime expirationDate;
}

