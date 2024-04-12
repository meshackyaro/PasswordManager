package com.myworkspace.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class LogoutResponse {
    private String username;
    private String message;
}
