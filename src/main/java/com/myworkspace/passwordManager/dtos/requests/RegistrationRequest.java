package com.myworkspace.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
