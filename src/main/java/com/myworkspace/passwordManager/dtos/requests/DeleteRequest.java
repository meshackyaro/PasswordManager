package com.myworkspace.passwordManager.dtos.requests;

import lombok.Data;

@Data
public class DeleteRequest {
    private String username;
    private String website;
    private String password;

}
