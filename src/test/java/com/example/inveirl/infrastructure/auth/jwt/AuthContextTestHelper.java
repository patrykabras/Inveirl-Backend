package com.example.inveirl.infrastructure.auth.jwt;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

public class AuthContextTestHelper {

    public static void prepareAuthContext(final RoleEnum role) {
        SecurityContextHolder.clearContext();
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role.name()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(prepareUserEntity(role),
                                                                                null,
                                                                                authorities);
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    private static JwtConfigurationUsersEntity prepareUserEntity(final RoleEnum role) {
        return JwtConfigurationUsersEntity.builder()
                                          .id(UUID.randomUUID())
                                          .username("testUser")
                                          .email("test@test.com")
                                          .password("password")
                                          .role(role)
                                          .build();
    }

}