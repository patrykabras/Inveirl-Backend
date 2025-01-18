package com.example.inveirl.admin.items.list;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@Schema(name = "ItemListFilterRequest", description = "Item list filter request")
class AdminItemsListFilterRequest {

    @Parameter(description = "User id", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Parameter(description = "Name contains", example = "egg")
    private String nameContains;
    @Parameter(description = "bardcode", example = "0792382370658")
    private String barCode;
}
