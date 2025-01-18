package com.example.inveirl.admin.users.list;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UsersListUsersRepository
        extends ReadOnlyRepository<UsersListUsersEntity, UUID>, JpaSpecificationExecutor<UsersListUsersEntity> {

}