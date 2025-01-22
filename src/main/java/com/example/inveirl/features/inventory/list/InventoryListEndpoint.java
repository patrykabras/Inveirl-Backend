package com.example.inveirl.features.inventory.list;

import com.example.inveirl.infrastructure.enumeration.Role;
import com.example.inveirl.infrastructure.page.PageRequestParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Inventory")
class InventoryListEndpoint {

    private InventoryListService service;

    @GetMapping("/inventory")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    public Page<InventoryListResponse> getInventoryItems(
            @ParameterObject final InventoryListFilterRequest filterRequest,
            @ParameterObject final PageRequestParams pageRequestParams) {
        return service.getInventoryItems(filterRequest, pageRequestParams);
    }
}