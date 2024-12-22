package com.example.inveirl.features.auth.token.refresh;

import com.example.inveirl.infrastructure.auth.jwt.JwtService;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import com.example.inveirl.infrastructure.exceptions.RefreshTokenNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TokenRefreshServiceTest {

    private final JwtService jwtService = mock(JwtService.class);
    private final TokenRefreshUserRefreshTokensRepository repository =
            mock(TokenRefreshUserRefreshTokensRepository.class);
    private final TokenRefreshService service = new TokenRefreshService(repository, jwtService);


    @Test
    void shouldRefreshTokenForNonAdminRole() {
        // given
        final TokenRefreshRequest request = TokenRefreshRequest.builder()
                                                               .refreshToken(UUID.randomUUID())
                                                               .build();
        final TokenRefreshUserRefreshTokensEntity token = prepareTokenEntity(RoleEnum.USER);
        when(repository.findByRefreshTokenAndActiveIsTrue(any())).thenReturn(Optional.of(token));
        when(jwtService.generateToken(any())).thenReturn("secure.jwt.code");

        // when
        final TokenRefreshResponse response = service.refreshToken(request);

        // then
        verify(jwtService).generateToken(token.getUser());
        verify(jwtService, never()).generateAdminToken(token.getUser());
        assertEquals("secure.jwt.code", response.getToken());
    }

    @Test
    void shouldRefreshTokenForAdminRole() {
        // given
        final TokenRefreshRequest request = TokenRefreshRequest.builder()
                                                               .refreshToken(UUID.randomUUID())
                                                               .build();
        final TokenRefreshUserRefreshTokensEntity token = prepareTokenEntity(RoleEnum.ADMIN);
        when(repository.findByRefreshTokenAndActiveIsTrue(any())).thenReturn(Optional.of(token));
        when(jwtService.generateAdminToken(any())).thenReturn("secure.jwt.code");

        // when
        final TokenRefreshResponse response = service.refreshToken(request);

        // then
        verify(jwtService).generateAdminToken(token.getUser());
        verify(jwtService, never()).generateToken(token.getUser());
        assertEquals("secure.jwt.code", response.getToken());
    }

    @Test
    void shouldThrowRefreshTokenNotFoundException() {
        // given
        final UUID refreshToken = UUID.randomUUID();
        final TokenRefreshRequest request = TokenRefreshRequest.builder()
                                                               .refreshToken(refreshToken)
                                                               .build();
        when(repository.findByRefreshTokenAndActiveIsTrue(any())).thenReturn(Optional.empty());

        // when
        final RefreshTokenNotFoundException exception = assertThrows(RefreshTokenNotFoundException.class,
                                                                     () -> service.refreshToken(request));

        // then
        assertEquals("Refresh token '" + refreshToken + "' not found", exception.getMessage());
    }

    private static TokenRefreshUserRefreshTokensEntity prepareTokenEntity(final RoleEnum role) {
        return TokenRefreshUserRefreshTokensEntity.builder()
                                                  .user(TokenRefreshUsersEntity.builder()
                                                                               .role(role)
                                                                               .build())
                                                  .build();
    }
}