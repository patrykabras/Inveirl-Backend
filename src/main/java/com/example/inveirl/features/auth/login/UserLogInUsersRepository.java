package com.example.inveirl.features.auth.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserLogInUsersRepository extends JpaRepository<UserLogInUsersEntity, UUID> {

    Optional<UserLogInUsersEntity> findByEmail(String email);
}