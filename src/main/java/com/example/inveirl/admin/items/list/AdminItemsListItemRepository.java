package com.example.inveirl.admin.items.list;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface AdminItemsListItemRepository extends ReadOnlyRepository<AdminItemsListItemsEntity, UUID>,
        JpaSpecificationExecutor<AdminItemsListItemsEntity> {

}