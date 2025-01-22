package com.example.inveirl.features.inventory.list;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "InventoryListFilterRequest", description = "Inventory list filter request")
class InventoryListFilterRequest {

    @Parameter(description = "Item name contains", example = "apple")
    private String itemNameContains;

}
