package com.example.inveirl.features.item.add;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
class ItemAddService {

    private final ItemAddItemRepository repository;

    public void addItem(final ItemAddItemRequest request) {
        final var itemEntity = ItemAddItemsEntity.ofItemAddItemRequest(request);

        repository.save(itemEntity);
    }
}
