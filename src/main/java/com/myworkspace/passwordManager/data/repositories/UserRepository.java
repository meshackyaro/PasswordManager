package com.myworkspace.passwordManager.data.repositories;

import com.myworkspace.passwordManager.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
