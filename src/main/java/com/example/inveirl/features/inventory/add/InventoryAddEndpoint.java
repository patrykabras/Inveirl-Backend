package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Tag(name = "Inventory")
class InventoryAddEndpoint {

    private InventoryAddService service;

    @Operation(summary = "Add item to user inventory", description = "POST methods that add item to user inventory")
    @PostMapping("/inventory/addItem")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    public void addItemToInventory(@RequestBody @Valid InventoryAddItemRequest item) {
        service.addItemToInventory(item);
    }
}