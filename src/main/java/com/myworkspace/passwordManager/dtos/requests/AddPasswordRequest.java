package com.myworkspace.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class AddPasswordRequest {
    private String website;
    private String username;
    private String password;
}
