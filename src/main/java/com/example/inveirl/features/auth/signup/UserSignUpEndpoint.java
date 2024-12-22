package com.example.inveirl.features.auth.signup;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Auth")
class UserSignUpEndpoint {
    private final UserSignUpService service;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserSignUpUsersEntity> userSignUp(@RequestBody UserSignUpRequest request) {
        UserSignUpUsersEntity registeredUser = service.signup(request);

        return ResponseEntity.ok(registeredUser);
    }
}