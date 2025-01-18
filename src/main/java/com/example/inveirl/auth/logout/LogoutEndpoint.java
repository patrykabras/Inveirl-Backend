package com.example.inveirl.auth.logout;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "Global")
class LogoutEndpoint {
    private final LogoutService service;

    @Operation(summary = "Logout user", description = """
            POST methods that allow to logout user and disable refresh token
            """)
    @PostMapping("/auth/logout")
    public void logout(@RequestHeader("Authorization") final UUID refreshToken) {
        service.logout(refreshToken);
    }
}