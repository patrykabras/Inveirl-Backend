package com.example.inveirl.features.auth.token.refresh;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
@Entity(name = "TokenRefreshUserRefreshTokensEntity")
class TokenRefreshUserRefreshTokensEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, updatable = false, insertable = false)
    private TokenRefreshUsersEntity user;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private UUID refreshToken;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private boolean active;

}
