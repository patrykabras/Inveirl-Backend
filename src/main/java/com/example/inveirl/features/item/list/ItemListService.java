package com.example.inveirl.features.item.list;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class ItemListService {

    private final ItemListItemRepository repository;

    public List<ItemListItemResponse> getItems(final ItemListFilterRequest filterRequest) {
        final ItemListItemsSpecifications specifications = new ItemListItemsSpecifications(filterRequest);
        final List<ItemListItemsEntity> items = repository.findAll(specifications);

        return items.stream()
                    .map(ItemListItemsEntity::toItemDetailsItemResponse)
                    .toList();
    }
}
