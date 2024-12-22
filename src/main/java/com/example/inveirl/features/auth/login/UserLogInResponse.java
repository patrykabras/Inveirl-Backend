package com.example.inveirl.features.auth.login;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
class UserLogInResponse {

    private String name;
    private String token;
    private long expiresIn;
    private UUID refreshToken;
}
