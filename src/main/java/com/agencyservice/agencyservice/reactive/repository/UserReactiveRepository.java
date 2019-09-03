package com.agencyservice.agencyservice.reactive.repository;

import com.agencyservice.agencyservice.user.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserReactiveRepository extends ReactiveMongoRepository<User, String> {
}
