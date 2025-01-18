package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.enumeration.ItemType;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ItemDetailsItemResponse {

    private UUID id;
    private String name;
    private String description;
    private ItemType itemType;
    private String barCode;
    private String imageUrl;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;
}
