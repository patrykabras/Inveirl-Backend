package com.example.inveirl.auth.login;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserLogInResponse {

    private String name;
    private String token;
    private long expiresIn;
    private UUID refreshToken;
}
