package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.exception.UserExistsException;
import com.agencyservice.agencyservice.user.model.Buyer;
import com.agencyservice.agencyservice.reactive.repository.BuyerReactiveRepository;
import com.agencyservice.agencyservice.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BuyerService {

    @Autowired
    private BuyerReactiveRepository buyerReactiveRepository;
    @Autowired
    private BuyerRepository buyerRepository;

    public Mono<Buyer> addBuyer(Buyer buyer) {
        buyerRepository.findByUsername(buyer.getUsername()).ifPresent(user -> {
            throw new UserExistsException(user.getUsername() + " is already occupied.");
        });
        buyerRepository.findByGstNumber(buyer.getGstNumber()).ifPresent(user -> {
            throw new UserExistsException("GST number => \""+ user.getGstNumber() + "\" is already registered with us.");
        });
        return buyerReactiveRepository.save(buyer);
    }

    public Flux<Buyer> findBuyersByVerifiedFalse() {

        return buyerReactiveRepository.findAllByVerifiedFalse();
    }

    public Mono<Buyer> findBuyerById(String buyerId) {
       return buyerReactiveRepository.findById(buyerId);
    }

    public Mono<Buyer> save(Buyer buyer) {
       return buyerReactiveRepository.save(buyer);
    }
}
