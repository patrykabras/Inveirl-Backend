package com.example.inveirl.infrastructure.auth.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthenticationContext {

    public static UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static AuthenticatedUser getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext()
                                                                   .getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof JwtConfigurationUsersEntity userDetails) {
                return AuthenticatedUser.builder()
                                        .id(userDetails.getId())
                                        .username(userDetails.getUsername())
                                        .email(userDetails.getEmail())
                                        .role(userDetails.getRole())
                                        .creationDate(userDetails.getMetadata()
                                                                 .getCreationDate())
                                        .modificationDate(userDetails.getMetadata()
                                                                     .getModificationDate())
                                        .build();
            }

        }
        throw new RuntimeException("User not authenticated");
    }
}