package com.example.inveirl.features.auth.login;

import com.example.inveirl.infrastructure.auth.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class UserLogInService {
    private final AuthenticationManager authenticationManager;
    private final UserLogInUsersRepository repository;
    private final JwtService jwtService;

    public UserLogInResponse authenticate(final UserLogInRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                                                                     request.getPassword());
        authenticationManager.authenticate(authentication);

        final UserLogInUsersEntity user = getUser(request.getEmail());
        final UUID refreshToken = UUID.randomUUID();
        user.updateRefreshToken(refreshToken);
        repository.saveAndFlush(user);

        final String jwtToken = jwtService.generateToken(user);

        return UserLogInResponse.builder()
                                .name(user.getName())
                                .token(jwtToken)
                                .expiresIn(jwtService.getUserExpirationTime())
                                .refreshToken(refreshToken)
                                .build();
    }

    public UserLogInResponse authenticateAdmin(final UserLogInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                                                   request.getPassword()));

        final UserLogInUsersEntity user = getUser(request.getEmail());
        user.validateIsAdmin();

        final UUID refreshToken = UUID.randomUUID();
        user.updateRefreshToken(refreshToken);
        repository.saveAndFlush(user);

        final String jwtToken = jwtService.generateAdminToken(user);

        return UserLogInResponse.builder()
                                .name(user.getName())
                                .token(jwtToken)
                                .expiresIn(jwtService.getAdminExpirationTime())
                                .refreshToken(refreshToken)
                                .build();
    }

    private UserLogInUsersEntity getUser(final String email) {
        return repository.findByEmail(email)
                         .orElseThrow();
    }
}