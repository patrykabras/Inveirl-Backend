package com.example.inveirl.features.auth.login;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Auth")
class UserLogInEndpoint {
    private final UserLogInService service;

    @PostMapping("/auth/login")
    public ResponseEntity<UserLogInResponse> userLogIn(@RequestBody final UserLogInRequest request) {
        UserLogInResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/admin/login")
    public ResponseEntity<UserLogInResponse> adminLogIn(@RequestBody final UserLogInRequest request) {
        UserLogInResponse response = service.authenticateAdmin(request);
        return ResponseEntity.ok(response);
    }
}