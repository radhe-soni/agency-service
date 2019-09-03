package com.agencyservice.agencyservice.reactive.repository;

import com.agencyservice.agencyservice.user.model.Supplier;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SupplierReactiveRepository extends ReactiveMongoRepository<Supplier, String> {
}
