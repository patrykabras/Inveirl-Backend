package com.example.inveirl.features.item.details;

import com.example.inveirl.infrastructure.exceptions.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class ItemDetailsService {

    private final ItemDetailsItemRepository repository;

    public ItemDetailsItemResponse getItemDetails(final UUID id) {
        return getItem(id).toItemDetailsItemResponse();
    }

    private ItemDetailsItemsEntity getItem(final UUID id) {
        return repository.findById(id)
                         .orElseThrow(() -> ItemNotFoundException.of(id));
    }
}
