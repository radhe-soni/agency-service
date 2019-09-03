package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
