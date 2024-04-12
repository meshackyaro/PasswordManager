package com.myworkspace.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class LogoutRequest {
    private String username;
    private String message;
}
