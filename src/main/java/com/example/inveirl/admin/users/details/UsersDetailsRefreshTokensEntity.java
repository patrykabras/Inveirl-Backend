package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.Metadata;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
@Entity(name = "UsersDetailsRefreshTokensEntity")
class UsersDetailsRefreshTokensEntity {

    @Id
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Column(updatable = false, insertable = false)
    private UUID userId;

    @Column(updatable = false, insertable = false)
    private UUID refreshToken;

    @Column(updatable = false, insertable = false)
    private boolean active;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public UsersDetailsResponse.RefreshTokens toRefreshTokens() {
        return UsersDetailsResponse.RefreshTokens.builder()
                                                 .id(id)
                                                 .refreshToken(refreshToken)
                                                 .active(active)
                                                 .creationDate(metadata.getCreationDate())
                                                 .modificationDate(metadata.getModificationDate())
                                                 .build();
    }
}
