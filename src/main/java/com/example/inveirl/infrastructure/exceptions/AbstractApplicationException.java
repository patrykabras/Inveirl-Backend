package com.example.inveirl.infrastructure.exceptions;

import ch.qos.logback.classic.Level;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

@Getter
public abstract class AbstractApplicationException extends RuntimeException {

    protected final Level logLevel;

    protected AbstractApplicationException(final String message) {
        this(message, Level.WARN);
    }

    protected AbstractApplicationException(final String message, final Level logLevel) {
        super(message);
        this.logLevel = logLevel;
    }

    public abstract HttpStatus getHttpStatus();

    public abstract URI getType();

    public abstract String getTitle();
}
