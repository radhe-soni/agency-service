package com.agencyservice.agencyservice.repository;

import com.agencyservice.agencyservice.user.model.Bank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface BankRepository extends ReactiveMongoRepository<Bank, String> {
    Optional<Bank> findByBankName(String bankName);
}
