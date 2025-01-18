package com.example.inveirl.auth.refresh;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
class TokenRefreshResponse {

    private String name;
    private String token;
    private long expiresIn;
    private UUID refreshToken;
}
