package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.Password;
import com.myworkspace.passwordManager.data.model.User;
import com.myworkspace.passwordManager.data.repositories.PasswordRepository;
import com.myworkspace.passwordManager.data.repositories.UserRepository;
import com.myworkspace.passwordManager.dtos.requests.*;
import com.myworkspace.passwordManager.dtos.responses.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PasswordRepository passwordRepository;

    @Test
    public void addPasswordTest() {
        userRepository.deleteAll();
        passwordRepository.deleteAll();
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
        long currentLoggedInUsers = userRepository.findAll().size();
        User user = userService.findUserByUsername(loginRequest.getUsername());
        assertTrue(user.isLoggedIn());
        assertEquals(currentLoggedInUsers, userService.getNumberOfUsers());

        AddPasswordRequest addPasswordRequest = new AddPasswordRequest();
        addPasswordRequest.setWebsite("website");
        addPasswordRequest.setUsername("username");
        addPasswordRequest.setPassword("password");
        AddPasswordResponse response2 = passwordService.addPassword(addPasswordRequest);
        long currentPasswords = passwordRepository.findAll().size();
        user = userService.findUserByUsername(addPasswordRequest.getUsername());
        assertEquals(currentPasswords, passwordService.count());
        Password foundPassword = passwordService.findByUsername("username");
        assertEquals("username", foundPassword.getUsername());
    }
    @Test
    public void updatePasswordTest() {
        userRepository.deleteAll();
        passwordRepository.deleteAll();
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
        long currentLoggedInUsers = userRepository.findAll().size();
        User user = userService.findUserByUsername(registrationRequest.getUsername());
        assertTrue(user.isLoggedIn());
        assertEquals(currentLoggedInUsers, userService.getNumberOfUsers());

        AddPasswordRequest addPasswordrequest = new AddPasswordRequest();
        addPasswordrequest.setWebsite("website");
        addPasswordrequest.setUsername("username");
        addPasswordrequest.setPassword("password");
        AddPasswordResponse response2 = passwordService.addPassword(addPasswordrequest);
        long currentPasswords = passwordRepository.findAll().size();
        user = userService.findUserByUsername(registrationRequest.getUsername());
        assertEquals(currentPasswords, passwordService.count());
        Password foundPassword = passwordService.findByUsername("username");
        assertEquals("username", foundPassword.getUsername());

        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setWebsite(addPasswordrequest.getWebsite());
        updatePasswordRequest.setUsername(addPasswordrequest.getUsername());
        updatePasswordRequest.setOldPassword(addPasswordrequest.getPassword());
        updatePasswordRequest.setNewPassword("newPassword");
        UpdatePasswordResponse response3 = passwordService.updatePassword(updatePasswordRequest);
        long newPasswords = passwordRepository.findAll().size();
        assertEquals(newPasswords, passwordService.count());
        Password foundNewPasswords = passwordService.findByUsername("username");
        assertEquals("newPassword", foundNewPasswords.getPassword());
    }
    @Test
    public void deletePasswordTest() {
        userRepository.deleteAll();
        passwordRepository.deleteAll();
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setPassword("password");
        registrationRequest.setConfirmPassword(registrationRequest.getPassword());
        RegistrationResponse response = userService.register(registrationRequest);
        long currentUsers = userRepository.findAll().size();
        User user = userService.findUserByUsername(registrationRequest.getUsername());
        assertEquals(currentUsers, userService.getNumberOfUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(registrationRequest.getUsername());
        loginRequest.setPassword(registrationRequest.getPassword());
        LoginResponse response1 = userService.login(loginRequest);
        long currentLoggedInUsers = userRepository.findAll().size();
        user = userService.findUserByUsername(registrationRequest.getUsername());
        assertTrue(user.isLoggedIn());
        assertEquals(currentLoggedInUsers, userService.getNumberOfUsers());

        AddPasswordRequest addPasswordrequest = new AddPasswordRequest();
        addPasswordrequest.setWebsite("website");
        addPasswordrequest.setUsername("username");
        addPasswordrequest.setPassword("password");
        AddPasswordResponse response2 = passwordService.addPassword(addPasswordrequest);
        long currentPasswords = passwordRepository.findAll().size();
        user = userService.findUserByUsername(registrationRequest.getUsername());
        assertEquals(currentPasswords, passwordService.count());

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setWebsite("website");
        deleteRequest.setUsername(addPasswordrequest.getUsername());
        deleteRequest.setPassword(addPasswordrequest.getPassword());
        DeleteResponse response3 = passwordService.deletePassword(deleteRequest);
        long currentPassword = passwordRepository.findAll().size();
        assertEquals(currentPassword, passwordService.count());
    }

}
