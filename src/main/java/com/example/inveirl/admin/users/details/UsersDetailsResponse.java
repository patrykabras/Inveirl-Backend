package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class UsersDetailsResponse {

    private UUID id;
    private String username;
    private String email;
    private RoleEnum role;
    private List<RefreshTokens> refreshTokens;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;

    @Getter
    @Builder(access = AccessLevel.PACKAGE)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    static class RefreshTokens {
        private UUID id;
        private UUID refreshToken;
        private boolean active;
        private ZonedDateTime creationDate;
        private ZonedDateTime modificationDate;
    }
}
