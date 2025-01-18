package com.example.inveirl.admin.items.list;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class AdminItemsListItemsSpecifications implements Specification<AdminItemsListItemsEntity> {

    private AdminItemsListFilterRequest filterRequest;

    @Override
    public Predicate toPredicate(Root<AdminItemsListItemsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();

        if (filterRequest.getId() != null) {
            predicates.add(cb.equal(root.get("id"), filterRequest.getId()));
        }
        if (StringUtils.isNotBlank(filterRequest.getNameContains())) {
            predicates.add(cb.like((root.get("name")), "%" + filterRequest.getNameContains() + "%"));
        }
        if (filterRequest.getBarCode() != null) {
            predicates.add(cb.equal(root.get("barCode"), filterRequest.getBarCode()));
        }
        return query.where(predicates.toArray(new Predicate[0]))
                    .getRestriction();
    }
}
