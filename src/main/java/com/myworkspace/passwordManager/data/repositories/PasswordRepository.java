package com.myworkspace.passwordManager.data.repositories;

import com.myworkspace.passwordManager.data.model.Password;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends MongoRepository<Password, String> {
    Password findByWebsite(String website);

    Password findByUsername(String username);
}
