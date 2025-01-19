package com.example.inveirl.features.inventory.add;

import com.example.inveirl.infrastructure.auth.jwt.AuthenticationContext;
import com.example.inveirl.infrastructure.exceptions.ItemNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class InventoryAddService {

    private final InventoryAddItemRepository itemRepository;
    private final InventoryAddUsersInventoriesRepository inventoriesRepository;

    public void addItemToInventory(final InventoryAddItemRequest request) {
        final UUID userId = AuthenticationContext.getCurrentUserId();
        final InventoryAddItemsEntity itemEntity = getItem(request.getItemId());

        final InventoryAddUsersInventoriesEntity invItem = getInvItemOrCreate(request, userId, itemEntity.getId());
        inventoriesRepository.save(invItem);
        log.info("Item with id '{}' has been updated for user with id '{}'", request.getItemId(), userId);
    }

    private InventoryAddUsersInventoriesEntity getInvItemOrCreate(final InventoryAddItemRequest request,
                                                                  final UUID userId,
                                                                  final UUID itemId) {
        return inventoriesRepository.getByUserIdAndItemId(userId, itemId)
                                    .map(invItem -> {
                                        invItem.updateItem(request);
                                        return invItem;
                                    })
                                    .orElseGet(() -> createNewInvItem(request, itemId, userId));
    }

    private InventoryAddUsersInventoriesEntity createNewInvItem(final InventoryAddItemRequest request,
                                                                final UUID itemId,
                                                                final UUID userId) {
        return InventoryAddUsersInventoriesEntity.of(request, itemId, userId);
    }

    private InventoryAddItemsEntity getItem(final UUID id) {
        return itemRepository.findById(id)
                             .orElseThrow(() -> ItemNotFoundException.of(id));
    }
}
