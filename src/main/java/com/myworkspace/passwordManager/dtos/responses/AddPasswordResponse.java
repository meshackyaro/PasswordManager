package com.myworkspace.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class AddPasswordResponse {
    private String website;
    private String username;
    private String password;
    private String message;
}
