package com.example.inveirl.auth.logout;

import com.example.inveirl.infrastructure.exceptions.RefreshTokenNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class LogoutService {
    private final LogoutRefreshTokensRepository repository;

    public void logout(final UUID refreshToken) {
        final LogoutRefreshTokensEntity token = getActiveRefreshTokenBy(refreshToken);
        token.disable();
        repository.saveAndFlush(token);
    }

    private LogoutRefreshTokensEntity getActiveRefreshTokenBy(final UUID refreshToken) {
        return repository.findByRefreshTokenAndActiveIsTrue(refreshToken)
                         .orElseThrow(() -> RefreshTokenNotFoundException.of(refreshToken));
    }
}