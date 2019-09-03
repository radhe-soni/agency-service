package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface SupplierRepository extends  UserBaseRepository<Supplier>, MongoRepository<Supplier, String> {

    Optional<Supplier> findByGstNumber(String gstNumber);
}
