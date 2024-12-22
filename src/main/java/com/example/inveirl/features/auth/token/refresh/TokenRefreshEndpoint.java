package com.example.inveirl.features.auth.token.refresh;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Auth")
class TokenRefreshEndpoint {
    private final TokenRefreshService service;

    @PostMapping("/auth/refresh")
    public TokenRefreshResponse refreshToken(@RequestBody final TokenRefreshRequest request) {
        return service.refreshToken(request);
    }
}