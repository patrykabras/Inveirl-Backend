package com.example.inveirl.admin.users.list;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class UserListUsersSpecifications implements Specification<UsersListUsersEntity> {

    private transient UserListFilterRequest filterRequest;

    @Override
    public Predicate toPredicate(final Root<UsersListUsersEntity> root,
                                 final CriteriaQuery<?> query,
                                 final CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();

        if (filterRequest.getId() != null) {
            predicates.add(cb.equal(root.get("id"), filterRequest.getId()));
        }
        if (StringUtils.isNotBlank(filterRequest.getEmailContains())) {
            predicates.add(cb.like((root.get("email")), "%" + filterRequest.getEmailContains() + "%"));
        }
        if (StringUtils.isNotBlank(filterRequest.getUsernameContains())) {
            predicates.add(cb.like((root.get("username")), "%" + filterRequest.getUsernameContains() + "%"));
        }
        if (filterRequest.getRoles() != null && !filterRequest.getRoles()
                                                              .isEmpty()) {
            predicates.add(root.get("role")
                               .in(filterRequest.getRoles()));
        }
        return query.where(predicates.toArray(new Predicate[0]))
                    .getRestriction();
    }
}
