package com.example.inveirl.features.item.list;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ItemListItemResponse {

    private UUID id;
    private String name;
    private ItemType itemType;
    private String barCode;
    private String imageUrl;
}
