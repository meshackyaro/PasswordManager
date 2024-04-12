package com.myworkspace.passwordManager.exceptions;

public class UserAlreadyExistException extends PasswordManagerException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
