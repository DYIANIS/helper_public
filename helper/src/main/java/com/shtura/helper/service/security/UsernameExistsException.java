package com.shtura.helper.service.security;

public class UsernameExistsException extends Exception {

    public UsernameExistsException(String message) {
        super(message);
    }
}
