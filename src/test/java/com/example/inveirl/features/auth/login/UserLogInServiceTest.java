package com.example.inveirl.features.auth.login;

import com.example.inveirl.infrastructure.auth.jwt.JwtService;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserLogInServiceTest {

    private final JwtService jwtService = mock(JwtService.class);
    private final UserLogInUsersRepository repository = mock(UserLogInUsersRepository.class);
    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserLogInService service = new UserLogInService(authenticationManager, repository, jwtService);

    @Test
    void shouldAuthenticateUser() {
        // given
        final ArgumentCaptor<UserLogInUsersEntity> argument = ArgumentCaptor.forClass(UserLogInUsersEntity.class);
        final UserLogInRequest request = UserLogInRequest.builder()
                                                         .email("user@user.com")
                                                         .password("U$er")
                                                         .build();
        final UserLogInUsersEntity user = UserLogInUsersEntity.builder()
                                                              .id(UUID.randomUUID())
                                                              .email("user@user.com")
                                                              .role(RoleEnum.USER)
                                                              .refreshTokens(new ArrayList<>())
                                                              .build();
        when(repository.findByEmail(any())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any())).thenReturn("secure.jwt.code");


        // when
        final UserLogInResponse response = service.authenticate(request);

        // then
        verify(repository).findByEmail(request.getEmail());
        verify(repository).saveAndFlush(argument.capture());

        final UserLogInUsersEntity savedEntity = argument.getValue();
        final List<UserLogInUserRefreshTokensEntity> refreshTokens = savedEntity.getRefreshTokens();
        assertEquals(1, refreshTokens.size());
        assertEquals(refreshTokens.get(0)
                                  .getRefreshToken(), response.getRefreshToken());
        assertEquals("secure.jwt.code", response.getToken());
    }

    @Test
    void shouldAuthenticateAdmin() {
        // given
        final ArgumentCaptor<UserLogInUsersEntity> argument = ArgumentCaptor.forClass(UserLogInUsersEntity.class);
        final UserLogInRequest request = UserLogInRequest.builder()
                                                         .email("admin@admin.com")
                                                         .password("Adm1n")
                                                         .build();
        final UserLogInUsersEntity user = UserLogInUsersEntity.builder()
                                                              .id(UUID.randomUUID())
                                                              .email("admin@admin.com")
                                                              .role(RoleEnum.ADMIN)
                                                              .refreshTokens(new ArrayList<>())
                                                              .build();
        when(repository.findByEmail(any())).thenReturn(Optional.of(user));
        when(jwtService.generateAdminToken(any())).thenReturn("secure.jwt.code");


        // when
        final UserLogInResponse response = service.authenticateAdmin(request);

        // then
        verify(repository).findByEmail(request.getEmail());
        verify(repository).saveAndFlush(argument.capture());

        final UserLogInUsersEntity savedEntity = argument.getValue();
        final List<UserLogInUserRefreshTokensEntity> refreshTokens = savedEntity.getRefreshTokens();
        assertEquals(1, refreshTokens.size());
        assertEquals(refreshTokens.get(0)
                                  .getRefreshToken(), response.getRefreshToken());
        assertEquals("secure.jwt.code", response.getToken());
    }

    @Test
    void shouldThrowBadCredentials() {
        // given
        final UserLogInRequest request = UserLogInRequest.builder()
                                                         .email("user@user.com")
                                                         .password("Wr0ng Pa$$worp")
                                                         .build();
        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);


        // when
        assertThrows(BadCredentialsException.class, () -> service.authenticate(request));

        // then
        verify(repository, never()).findByEmail(any());
        verify(repository, never()).saveAndFlush(any());
    }

    @Test
    void shouldThrowBadCredentialsWhenUserWithRoleOtherThenAdminTryToLogIn() {
        // given
        final UserLogInRequest request = UserLogInRequest.builder()
                                                         .email("user@user.com")
                                                         .password("U$er")
                                                         .build();
        final UserLogInUsersEntity user = UserLogInUsersEntity.builder()
                                                              .id(UUID.randomUUID())
                                                              .email("user@user.com")
                                                              .role(RoleEnum.USER)
                                                              .refreshTokens(new ArrayList<>())
                                                              .build();
        when(repository.findByEmail(any())).thenReturn(Optional.of(user));


        // when
        assertThrows(BadCredentialsException.class, () -> service.authenticateAdmin(request));

        // then
        verify(repository).findByEmail(any());
        verify(repository, never()).saveAndFlush(any());
    }
}