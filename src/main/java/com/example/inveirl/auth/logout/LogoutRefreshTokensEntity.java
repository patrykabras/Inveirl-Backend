package com.example.inveirl.auth.logout;

import com.example.inveirl.infrastructure.Metadata;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
@Entity(name = "LogoutRefreshTokensEntity")
class LogoutRefreshTokensEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private UUID refreshToken;

    @Column(unique = true, length = 100, nullable = false)
    private boolean active;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public void disable() {
        this.active = false;
    }
}
