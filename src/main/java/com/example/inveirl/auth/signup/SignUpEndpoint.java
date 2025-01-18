package com.example.inveirl.auth.signup;

import com.example.inveirl.auth.login.UserLogInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Global")
class SignUpEndpoint {
    private final SignUpService service;

    @Operation(summary = "Register new user", description = "POST methods that allow register new user")
    @PostMapping("/auth/signup")
    public UserLogInResponse userSignUp(@RequestBody SignUpRequest request) {
        return service.signup(request);
    }
}