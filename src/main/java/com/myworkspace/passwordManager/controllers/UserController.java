package com.myworkspace.passwordManager.controllers;

import com.myworkspace.passwordManager.dtos.requests.*;
import com.myworkspace.passwordManager.dtos.responses.*;
import com.myworkspace.passwordManager.exceptions.PasswordManagerException;
import com.myworkspace.passwordManager.services.PasswordService;
import com.myworkspace.passwordManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/passwordManager")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            RegistrationResponse response = userService.register(registrationRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        }
        catch (PasswordManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = userService.login(loginRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        }
        catch (PasswordManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/addPassword")
    public ResponseEntity<?> addPassword(@RequestBody AddPasswordRequest addPasswordRequest) {
        try {
            AddPasswordResponse response = passwordService.addPassword(addPasswordRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        }
        catch (PasswordManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        try {
            UpdatePasswordResponse response = passwordService.updatePassword(updatePasswordRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        }
        catch (PasswordManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/deletePassword")
    public ResponseEntity<?> deletePassword(@RequestBody DeleteRequest deleteRequest) {
        try {
            DeleteResponse response = passwordService.deletePassword(deleteRequest);
            return new ResponseEntity<>(new APIResponse(true, response), CREATED);
        }
        catch (PasswordManagerException e) {
            return new ResponseEntity<>(new APIResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

}
