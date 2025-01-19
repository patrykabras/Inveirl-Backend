package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "items")
@Entity(name = "InventoryAddItemsEntity")
class InventoryAddItemsEntity {

    @Id
    @Column(insertable = false, updatable = false)
    private UUID id;

    @Column(insertable = false, updatable = false)
    private String name;

    @Column(insertable = false, updatable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private ItemType type;

    @Column(insertable = false, updatable = false)
    private String barCode;

    @Column(insertable = false, updatable = false)
    private String imageUrl;

}
