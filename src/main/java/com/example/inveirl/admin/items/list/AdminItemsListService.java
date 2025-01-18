package com.example.inveirl.admin.items.list;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class AdminItemsListService {

    private final AdminItemsListItemRepository repository;

    public List<AdminItemsListItemResponse> getItems(final AdminItemsListFilterRequest filterRequest) {
        final AdminItemsListItemsSpecifications specifications = new AdminItemsListItemsSpecifications(filterRequest);
        final List<AdminItemsListItemsEntity> items = repository.findAll(specifications);

        return items.stream()
                    .map(AdminItemsListItemsEntity::toItemDetailsItemResponse)
                    .toList();
    }
}
