package com.example.inveirl.features.auth.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class UserLogInRequest {

    private String email;
    private String password;
}
