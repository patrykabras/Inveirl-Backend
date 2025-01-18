package com.example.inveirl.features.item.list;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Item")
class ItemListEndpoint {

    private ItemListService service;

    @Operation(summary = "Get item list", description = "GET methods that provide list of items")
    @GetMapping(value = "/items", produces = "application/json")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    public List<ItemListItemResponse> getItems(final ItemListFilterRequest filterRequest) {
        return service.getItems(filterRequest);
    }
}