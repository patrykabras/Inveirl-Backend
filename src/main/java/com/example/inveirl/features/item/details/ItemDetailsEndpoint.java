package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "Item")
class ItemDetailsEndpoint {

    private ItemDetailsService service;

    @Operation(summary = "Get item details", description = "GET methods that provide list of items")
    @GetMapping("items/{itemId}")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    public ItemDetailsItemResponse getItemDetails(
            @Parameter(description = "Item identifier", required = true) @PathVariable UUID itemId) {
        return service.getItemDetails(itemId);
    }
}