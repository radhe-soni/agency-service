package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface BuyerRepository extends UserBaseRepository<Buyer>, MongoRepository<Buyer, String> {
    Optional<Buyer> findByGstNumber(String gstNumber);

    List<Buyer> findAllByVerifiedTrue();
}
