package com.example.inveirl.features.inventory.list;

import com.example.inveirl.infrastructure.auth.jwt.AuthenticationContext;
import com.example.inveirl.infrastructure.page.PageRequestParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class InventoryListService {

    private final InventoryListUsersInventoriesRepository repository;

    public Page<InventoryListResponse> getInventoryItems(final InventoryListFilterRequest filterRequest,
                                                         final PageRequestParams pageRequestParams) {
        final Pageable pageable = pageRequestParams.toPageable();
        final UUID userId = AuthenticationContext.getCurrentUserId();
        InventoryListInventoriesSpecifications specifications = new InventoryListInventoriesSpecifications(userId,
                                                                                                           filterRequest);

        final Page<InventoryListUsersInventoriesEntity> allProducts = repository.findAll(specifications, pageable);
        final List<InventoryListResponse> inventoryItems = getPageContent(allProducts);

        return new PageImpl<>(inventoryItems, pageable, allProducts.getTotalElements());
    }

    private static List<InventoryListResponse> getPageContent(Page<InventoryListUsersInventoriesEntity> allProducts) {
        return allProducts.getContent()
                          .stream()
                          .map(InventoryListUsersInventoriesEntity::toInventoryListResponse)
                          .toList();
    }
}
