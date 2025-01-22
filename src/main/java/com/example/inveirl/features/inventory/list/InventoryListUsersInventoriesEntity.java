package com.example.inveirl.features.inventory.list;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.QuantityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users_inventories")
@Entity(name = "InventoryListUsersInventoriesEntity")
class InventoryListUsersInventoriesEntity {

    @Id
    @Column(insertable = false, updatable = false)
    private UUID id;

    @Column(insertable = false, updatable = false)
    private UUID userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId", referencedColumnName = "id", insertable = false, updatable = false)
    private InventoryListItemsEntity item;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private QuantityType quantityType;

    @Column(insertable = false, updatable = false)
    private float quantity;

    @Column(insertable = false, updatable = false)
    private boolean isExists;

    @Column(name = "expiration_date", insertable = false, updatable = false)
    private ZonedDateTime expirationDate;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public InventoryListResponse toInventoryListResponse() {
        return InventoryListResponse.builder()
                                    .itemId(item.getId())
                                    .name(item.getName())
                                    .itemType(item.getType())
                                    .quantityType(quantityType)
                                    .quantity(quantity)
                                    .exists(isExists)
                                    .expirationDate(expirationDate)
                                    .creationDate(metadata.getCreationDate())
                                    .modificationDate(metadata.getModificationDate())
                                    .build();
    }
}
