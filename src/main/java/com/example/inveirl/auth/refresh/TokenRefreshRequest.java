package com.example.inveirl.auth.refresh;

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
