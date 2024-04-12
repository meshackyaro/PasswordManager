package com.myworkspace.passwordManager.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Password {
    private String id;
    private String website;
    private String password;
    private String username;
}
