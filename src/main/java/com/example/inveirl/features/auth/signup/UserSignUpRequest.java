package com.example.inveirl.features.auth.signup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class UserSignUpRequest {

    private String email;
    private String password;
    private String username;
}
