package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.User;
import com.myworkspace.passwordManager.data.repositories.UserRepository;
import com.myworkspace.passwordManager.dtos.requests.LoginRequest;
import com.myworkspace.passwordManager.dtos.requests.LogoutRequest;
import com.myworkspace.passwordManager.dtos.requests.RegistrationRequest;
import com.myworkspace.passwordManager.dtos.responses.LoginResponse;
import com.myworkspace.passwordManager.dtos.responses.LogoutResponse;
import com.myworkspace.passwordManager.dtos.responses.RegistrationResponse;
import com.myworkspace.passwordManager.exceptions.InvalidPasswordException;
import com.myworkspace.passwordManager.exceptions.InvalidUsernameException;
import com.myworkspace.passwordManager.exceptions.UserAlreadyExistException;
import com.myworkspace.passwordManager.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        User user = new User();
        ValidateUserFor(registrationRequest);
        validateUsernameFor(registrationRequest);
        validatePasswordFor(registrationRequest);
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        userRepository.save(user);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUsername(registrationRequest.getUsername());
        registrationResponse.setMessage("Successfully registered");
        return registrationResponse;
    }

    private void ValidateUserFor(RegistrationRequest registrationRequest) {
        if (findUserByUsername(registrationRequest.getUsername()) != null)
            throw new UserAlreadyExistException("Username already exist");
    }

    private static void validateUsernameFor(RegistrationRequest registrationRequest) {
        if (!registrationRequest.getUsername().matches("[a-zA-Z0-9]+"))
            throw new InvalidUsernameException("username must contain only Uppercase, lowercase and digits");
    }

    private static void validatePasswordFor(RegistrationRequest registrationRequest) {
        if (!registrationRequest.getPassword().matches("[a-zA-Z]+"))
            throw new InvalidPasswordException("Incorrect password format. Please use uppercase, lowercase and/or digits only");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public long getNumberOfUsers() {
        return userRepository.count();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User foundUser = findUserByUsername(loginRequest.getUsername());
        if (foundUser == null) throw new UserNotFoundException("User not found");

        foundUser.setUsername(loginRequest.getUsername());
        foundUser.setPassword(loginRequest.getPassword());
        foundUser.setLoggedIn(true);
        userRepository.save(foundUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginRequest.getUsername());
        loginResponse.setMessage("Successfully logged in");
        return loginResponse;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        User foundUser = findUserByUsername(logoutRequest.getUsername());
        foundUser.setLoggedIn(false);
        userRepository.save(foundUser);
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setUsername(logoutRequest.getUsername());
        logoutResponse.setMessage("Successfully logged out");
        return logoutResponse;
    }

}
