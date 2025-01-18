package com.example.inveirl.features.item.add;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "items")
@Entity(name = "ItemAddItemsEntity")
class ItemAddItemsEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(updatable = false)
    private String name;

    @Column(updatable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private ItemType type;

    @Column(updatable = false)
    private String barCode;

    @Column(updatable = false)
    private String imageUrl;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public static ItemAddItemsEntity ofItemAddItemRequest(final ItemAddItemRequest request) {
        return ItemAddItemsEntity.builder()
                                 .name(request.getName())
                                 .description(request.getDescription())
                                 .type(request.getItemType())
                                 .barCode(request.getBarCode())
                                 .imageUrl(request.getImageUrl())
                                 .build();
    }

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }

}
