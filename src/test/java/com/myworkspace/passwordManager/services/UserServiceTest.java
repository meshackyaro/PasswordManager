package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.User;
import com.myworkspace.passwordManager.data.repositories.PasswordRepository;
import com.myworkspace.passwordManager.data.repositories.UserRepository;
import com.myworkspace.passwordManager.dtos.requests.LoginRequest;
import com.myworkspace.passwordManager.dtos.requests.LogoutRequest;
import com.myworkspace.passwordManager.dtos.requests.RegistrationRequest;
import com.myworkspace.passwordManager.dtos.responses.LoginResponse;
import com.myworkspace.passwordManager.dtos.responses.LogoutResponse;
import com.myworkspace.passwordManager.dtos.responses.RegistrationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private PasswordService passwordService;

    @Test
    public void newUserRegistration() {
        userRepository.deleteAll();
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setPassword("password");
        RegistrationResponse response = userService.register(registrationRequest);
        int currentUsers = userRepository.findAll().size();
        assertEquals(currentUsers, userService.getNumberOfUsers());

    }
    @Test
    public void newUserLogin_loginTest() {
        userRepository.deleteAll();
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setPassword("password");
        registrationRequest.setConfirmPassword(registrationRequest.getPassword());
        RegistrationResponse response = userService.register(registrationRequest);
        long currentUsers = userRepository.findAll().size();
        assertEquals(currentUsers, userService.getNumberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registrationRequest.getUsername());
        loginRequest.setPassword(registrationRequest.getPassword());
        LoginResponse response1 = userService.login(loginRequest);
        User user = userService.findUserByUsername(loginRequest.getUsername());
        assertTrue(user.isLoggedIn());
    }
    @Test
    public void logoutTest() {
        userRepository.deleteAll();
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setPassword("password");
        registrationRequest.setConfirmPassword(registrationRequest.getPassword());
        RegistrationResponse response = userService.register(registrationRequest);
        long currentUsers = userRepository.findAll().size();
        assertEquals(currentUsers, userService.getNumberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registrationRequest.getUsername());
        loginRequest.setPassword(registrationRequest.getPassword());
        LoginResponse response1 = userService.login(loginRequest);
        User user = userService.findUserByUsername(loginRequest.getUsername());
        assertTrue(user.isLoggedIn());

        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUsername("username");
        LogoutResponse response2 = userService.logout(logoutRequest);
        user = userService.findUserByUsername((logoutRequest.getUsername()));
        assertFalse(user.isLoggedIn());


    }

}
