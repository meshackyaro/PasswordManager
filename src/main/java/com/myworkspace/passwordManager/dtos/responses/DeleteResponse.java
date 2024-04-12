package com.myworkspace.passwordManager.dtos.responses;

import lombok.Data;

@Data
public class DeleteResponse {
    private String website;
    private String username;
    private String message;
}
