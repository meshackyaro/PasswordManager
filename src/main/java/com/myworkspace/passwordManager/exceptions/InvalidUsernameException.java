package com.myworkspace.passwordManager.exceptions;

import java.awt.event.TextEvent;

public class InvalidUsernameException extends PasswordManagerException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
