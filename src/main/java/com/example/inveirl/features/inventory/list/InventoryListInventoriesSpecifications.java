package com.example.inveirl.features.inventory.list;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class InventoryListInventoriesSpecifications implements Specification<InventoryListUsersInventoriesEntity> {

    private final UUID userId;
    private InventoryListFilterRequest filterRequest;

    @Override
    public Predicate toPredicate(Root<InventoryListUsersInventoriesEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get("userId"), userId));

        if (filterRequest.getItemNameContains() != null) {
            predicates.add(cb.like(root.get("item")
                                       .get("name"), "%" + filterRequest.getItemNameContains() + "%"));
        }

        return query.where(predicates.toArray(new Predicate[0]))
                    .getRestriction();
    }
}
