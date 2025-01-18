package com.example.inveirl.features.item.add;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Item")
class ItemAddEndpoint {

    private ItemAddService service;

    @Operation(summary = "Add item", description = "POST methods that allow admin and user to add item")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    @PostMapping("items")
    public void addItem(@RequestBody @Valid ItemAddItemRequest item) {
        service.addItem(item);
    }
}