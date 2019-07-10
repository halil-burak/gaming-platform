package com.turkcell.playcell.gamingplatform.cms.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class c, String username) {
        super("Unable to find " + c + " with username: " + username);
    }

    public NotFoundException(Class c, Long id) {
        super("Unable to find " + c + " with id: " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
