package com.myworkspace.passwordManager.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class APIResponse {
    private boolean isSuccessful;
    private Object object;
}
