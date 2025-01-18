package com.example.inveirl.auth.refresh;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class TokenRefreshEndpoint {
    private final TokenRefreshService service;

    @Tag(name = "User")
    @Operation(summary = "Refresh authorization token", description = """
            POST methods that allow to refresh authorization token using refresh token that was
            provided when user log in
            """)
    @PostMapping("/auth/refresh")
    public TokenRefreshResponse refreshToken(@RequestBody final TokenRefreshRequest request) {
        return service.refreshToken(request);
    }

    @Tag(name = "Admin")
    @Operation(summary = "Refresh authorization token for admin", description = """
            POST methods that allow to refresh authorization token using refresh token that was
            provided when admin log in
            """)
    @PostMapping("/auth/admin/refresh")
    public TokenRefreshResponse refreshAdminToken(@RequestBody final TokenRefreshRequest request) {
        return service.refreshAdminToken(request);
    }
}