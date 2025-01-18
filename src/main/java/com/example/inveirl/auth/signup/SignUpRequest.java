package com.example.inveirl.auth.signup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class SignUpRequest {

    private String email;
    private String password;
    private String username;
}
