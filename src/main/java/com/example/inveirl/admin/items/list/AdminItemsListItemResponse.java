package com.example.inveirl.admin.items.list;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class AdminItemsListItemResponse {

    private UUID id;
    private String name;
    private String barCode;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;
}
