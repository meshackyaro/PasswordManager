package com.myworkspace.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String website;
    private String username;
    private String oldPassword;
    private String newPassword;
}
