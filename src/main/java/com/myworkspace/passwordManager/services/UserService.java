package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.User;
import com.myworkspace.passwordManager.dtos.requests.LoginRequest;
import com.myworkspace.passwordManager.dtos.requests.LogoutRequest;
import com.myworkspace.passwordManager.dtos.requests.RegistrationRequest;
import com.myworkspace.passwordManager.dtos.responses.LoginResponse;
import com.myworkspace.passwordManager.dtos.responses.LogoutResponse;
import com.myworkspace.passwordManager.dtos.responses.RegistrationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    RegistrationResponse register(RegistrationRequest registrationRequest);

    List<User> findAll();

    long getNumberOfUsers();

    LoginResponse login(LoginRequest loginRequest);

    User findUserByUsername(String username);

    LogoutResponse logout(LogoutRequest logoutRequest);
}
