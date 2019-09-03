package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<User>, MongoRepository<User, String> {
}
