package com.example.inveirl.features.item.list;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "items")
class ItemListItemsEntity {

    @Id
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Column(updatable = false, insertable = false)
    private String name;

    @Column(updatable = false, insertable = false)
    private String barCode;

    @Column(updatable = false, insertable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false, insertable = false)
    private ItemType type;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public ItemListItemResponse toItemDetailsItemResponse() {
        return ItemListItemResponse.builder()
                                   .id(id)
                                   .name(name)
                                   .barCode(barCode)
                                   .imageUrl(imageUrl)
                                   .itemType(type)
                                   .build();
    }
}
