package com.example.inveirl.features.item.add;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ItemAddItemRequest {

    private String name;
    private String description;
    private ItemType itemType;
    private String barCode;
    private String imageUrl;
}

