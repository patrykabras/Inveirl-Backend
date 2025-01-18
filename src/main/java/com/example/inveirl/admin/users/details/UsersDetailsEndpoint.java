package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "Users")
class UsersDetailsEndpoint {

    private final UsersDetailsService service;

    @Operation(summary = "Get user detail", description = "GET methods that provide user details")
    @GetMapping("admin/users/{userId}")
    @RolesAllowed(Role.ADMIN)
    public UsersDetailsResponse allUsers(
            @Parameter(description = "User identifier", required = true) @PathVariable final UUID userId) {
        return service.getUserDetails(userId);
    }
}