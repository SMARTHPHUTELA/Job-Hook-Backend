package com.jobhook.JobHook.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import com.jobhook.JobHook.entity.User;

public interface UserRepository extends MongoRepository<User,Long> {
    public Optional<User> findByEmail(String email);

}
