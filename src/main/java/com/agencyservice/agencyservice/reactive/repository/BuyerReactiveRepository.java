package com.agencyservice.agencyservice.reactive.repository;

import com.agencyservice.agencyservice.user.model.Buyer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BuyerReactiveRepository extends ReactiveMongoRepository<Buyer, String> {
    Flux<Buyer> findAllByVerifiedFalse();
    Flux<Buyer> findAllByVerifiedTrue();
}
