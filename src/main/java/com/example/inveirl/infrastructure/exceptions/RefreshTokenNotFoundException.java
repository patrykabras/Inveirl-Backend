package com.example.inveirl.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.UUID;

public class RefreshTokenNotFoundException extends AbstractApplicationException {

    private RefreshTokenNotFoundException(final String message) {
        super(message);
    }

    public static RefreshTokenNotFoundException of(final UUID token) {
        return new RefreshTokenNotFoundException("Refresh token '" + token + "' not found");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public URI getType() {
        return URI.create("refresh-token-not-found");
    }

    @Override
    public String getTitle() {
        return "Refresh token not found";
    }
}
