package com.example.inveirl.features.auth.token.refresh;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
class TokenRefreshRequest {

    private UUID refreshToken;
}
