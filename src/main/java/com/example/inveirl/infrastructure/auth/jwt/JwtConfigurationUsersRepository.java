package com.example.inveirl.infrastructure.auth.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JwtConfigurationUsersRepository extends JpaRepository<JwtConfigurationUsersEntity, UUID> {

    Optional<JwtConfigurationUsersEntity> findByEmail(String email);
}