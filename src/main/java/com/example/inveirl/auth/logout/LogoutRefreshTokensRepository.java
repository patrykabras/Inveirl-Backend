package com.example.inveirl.auth.logout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface LogoutRefreshTokensRepository extends JpaRepository<LogoutRefreshTokensEntity, UUID> {

    Optional<LogoutRefreshTokensEntity> findByRefreshTokenAndActiveIsTrue(UUID refreshToken);
}