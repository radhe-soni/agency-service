package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends MongoRepository<T, String> {
    Optional<T> findByUsername(String username);
}
