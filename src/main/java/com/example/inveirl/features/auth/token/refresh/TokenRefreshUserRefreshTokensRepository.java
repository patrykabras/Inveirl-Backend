package com.example.inveirl.features.auth.token.refresh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface TokenRefreshUserRefreshTokensRepository extends JpaRepository<TokenRefreshUserRefreshTokensEntity, UUID> {

    Optional<TokenRefreshUserRefreshTokensEntity> findByRefreshTokenAndActiveIsTrue(UUID refreshToken);
}