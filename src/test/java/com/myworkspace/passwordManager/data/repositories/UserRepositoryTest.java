package com.myworkspace.passwordManager.data.repositories;

import com.myworkspace.passwordManager.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryTest() {
        userRepository.deleteAll();
        User user = new User();
        userRepository.save(user);
        int currentUsers = userRepository.findAll().size();
        assertEquals(currentUsers, userRepository.count());

    }

}
