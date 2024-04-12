package com.myworkspace.passwordManager.data.repositories;

import com.myworkspace.passwordManager.data.model.Password;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordRepositoryTest {
    @Autowired
    private PasswordRepository passwordRepository;

    @Test
    public void passwordRepositoryTest() {
        passwordRepository.deleteAll();
        Password password = new Password();
        passwordRepository.save(password);
        int currentPasswords = passwordRepository.findAll().size();
        assertEquals(currentPasswords, passwordRepository.findAll().size());
    }
}
