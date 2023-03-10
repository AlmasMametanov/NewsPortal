package org.newsPortal.util;

public class EmailAlreadyExistsException extends RuntimeException {
    private String message;

    public EmailAlreadyExistsException() {
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
