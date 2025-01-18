package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "items")
@Entity(name = "ItemDetailsItemsEntity")
class ItemDetailsItemsEntity {

    @Id
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Column(updatable = false, insertable = false)
    private String name;

    @Column(updatable = false, insertable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false, insertable = false)
    private ItemType type;

    @Column(updatable = false, insertable = false)
    private String barCode;

    @Column(updatable = false, insertable = false)
    private String imageUrl;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    ItemDetailsItemResponse toItemDetailsItemResponse() {
        return ItemDetailsItemResponse.builder()
                                      .id(id)
                                      .name(name)
                                      .description(description)
                                      .itemType(type)
                                      .barCode(barCode)
                                      .imageUrl(imageUrl)
                                      .creationDate(metadata.getCreationDate())
                                      .modificationDate(metadata.getModificationDate())
                                      .build();
    }
}


