package com.example.inveirl.features.auth.login;

import com.example.inveirl.infrastructure.Metadata;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
@Entity(name = "UserLogInUserRefreshTokensEntity")
class UserLogInUserRefreshTokensEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private UUID userId;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private UUID refreshToken;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private boolean active;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }
}
