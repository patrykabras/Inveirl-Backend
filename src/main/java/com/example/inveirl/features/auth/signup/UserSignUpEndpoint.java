package com.example.inveirl.features.auth.signup;

import com.example.inveirl.features.auth.login.UserLogInResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Auth")
class UserSignUpEndpoint {
    private final UserSignUpService service;

    @PostMapping("/auth/signup")
    public UserLogInResponse userSignUp(@RequestBody UserSignUpRequest request) {
        return service.signup(request);
    }
}