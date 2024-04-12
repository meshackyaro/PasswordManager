package com.myworkspace.passwordManager.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    private String id;
    private String username;
    private String password;
    private boolean isLoggedIn;
}
