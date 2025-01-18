package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UsersDetailsUsersRepository extends ReadOnlyRepository<UsersDetailsUsersEntity, UUID> {
}