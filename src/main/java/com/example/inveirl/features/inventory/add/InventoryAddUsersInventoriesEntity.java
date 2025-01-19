package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.QuantityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users_inventories")
@Entity(name = "InventoryAddUsersInventoriesEntity")
class InventoryAddUsersInventoriesEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID userId;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID itemId;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID receiptItemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private QuantityType quantityType;

    @Column(nullable = false)
    private float quantity;

    @Column
    private boolean isExists;

    @Column(name = "expiration_date", updatable = false)
    private ZonedDateTime expirationDate;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public static InventoryAddUsersInventoriesEntity of(final InventoryAddItemRequest request,
                                                        final UUID itemId,
                                                        final UUID userId) {
        return InventoryAddUsersInventoriesEntity.builder()
                                                 .userId(userId)
                                                 .itemId(itemId)
                                                 .receiptItemId(null)
                                                 .quantityType(request.getQuantityType())
                                                 .quantity(request.getQuantity())
                                                 .expirationDate(request.getExpirationDate())
                                                 .isExists(true)
                                                 .build();
    }

    public void updateItem(final InventoryAddItemRequest request) {
        this.quantity += request.getQuantity();
    }

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }

}
