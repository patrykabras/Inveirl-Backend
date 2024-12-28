package com.example.inveirl.features.auth.token.refresh;

import com.example.inveirl.infrastructure.auth.jwt.JwtService;
import com.example.inveirl.infrastructure.exceptions.RefreshTokenNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class TokenRefreshService {
    private final TokenRefreshUserRefreshTokensRepository repository;
    private final JwtService jwtService;

    public TokenRefreshResponse refreshToken(final TokenRefreshRequest request) {
        final TokenRefreshUserRefreshTokensEntity token = getActiveRefreshTokenBy(request.getRefreshToken());

        TokenRefreshUsersEntity user = token.getUser();
        final String jwtToken = jwtService.generateToken(user);

        return TokenRefreshResponse.builder()
                                   .name(user.getName())
                                   .token(jwtToken)
                                   .expiresIn(jwtService.getUserExpirationTime())
                                   .refreshToken(request.getRefreshToken())
                                   .build();
    }

    public TokenRefreshResponse refreshAdminToken(final TokenRefreshRequest request) {
        final TokenRefreshUserRefreshTokensEntity token = getActiveRefreshTokenBy(request.getRefreshToken());

        TokenRefreshUsersEntity user = token.getUser();
        user.validateIsAdmin(request.getRefreshToken());
        if (user.isAdmin()) {
            final String jwtToken = jwtService.generateAdminToken(user);
            return TokenRefreshResponse.builder()
                                       .name(user.getName())
                                       .token(jwtToken)
                                       .expiresIn(jwtService.getAdminExpirationTime())
                                       .refreshToken(request.getRefreshToken())
                                       .build();
        }
        final String jwtToken = jwtService.generateToken(user);

        return TokenRefreshResponse.builder()
                                   .name(user.getName())
                                   .token(jwtToken)
                                   .expiresIn(jwtService.getUserExpirationTime())
                                   .refreshToken(request.getRefreshToken())
                                   .build();
    }

    private TokenRefreshUserRefreshTokensEntity getActiveRefreshTokenBy(final UUID refreshToken) {
        return repository.findByRefreshTokenAndActiveIsTrue(refreshToken)
                         .orElseThrow(() -> RefreshTokenNotFoundException.of(refreshToken));
    }
}