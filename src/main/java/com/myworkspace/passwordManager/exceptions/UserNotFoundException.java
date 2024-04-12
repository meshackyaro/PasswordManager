package com.myworkspace.passwordManager.exceptions;

public class UserNotFoundException extends PasswordManagerException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
