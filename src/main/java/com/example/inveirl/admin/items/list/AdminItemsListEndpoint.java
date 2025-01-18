package com.example.inveirl.admin.items.list;

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
class AdminItemsListEndpoint {

    private AdminItemsListService service;

    @Operation(summary = "Get item list", description = "GET methods that provide item list")
    @GetMapping(value = "/admin/items", produces = "application/json")
    @RolesAllowed(Role.ADMIN)
    public List<AdminItemsListItemResponse> getItemDetails(final AdminItemsListFilterRequest filterRequest) {
        return service.getItems(filterRequest);
    }
}