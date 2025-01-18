package com.example.inveirl.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.UUID;

public class ItemNotFoundException extends AbstractApplicationException {

    private ItemNotFoundException(final String message) {
        super(message);
    }

    public static ItemNotFoundException of(final UUID itemId) {
        return new ItemNotFoundException("Item with identifier '" + itemId + "' not found");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public URI getType() {
        return URI.create("item-not-found");
    }

    @Override
    public String getTitle() {
        return "Item not found";
    }
}
