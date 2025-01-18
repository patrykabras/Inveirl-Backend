package com.example.inveirl.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.UUID;

public class UserNotFoundException extends AbstractApplicationException {

    private UserNotFoundException(final String message) {
        super(message);
    }

    public static UserNotFoundException of(final UUID id) {
        return new UserNotFoundException("User with identifier '" + id + "' not found");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public URI getType() {
        return URI.create("user-not-found");
    }

    @Override
    public String getTitle() {
        return "User not found";
    }
}
