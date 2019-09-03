package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.enums.ROLE;
import com.agencyservice.agencyservice.user.model.Buyer;
import com.agencyservice.agencyservice.user.model.Supplier;
import com.agencyservice.agencyservice.payload.BuyerRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class RegistrationService {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private BuyerService buyerService;

    public Mono<Set<ROLE>> registerSupplier(Supplier supplier) {
        return supplierService.addSupplier(supplier).map(sup -> sup.getRoles());
    }

    public Mono<BuyerRegistrationResponse> registerBuyer(Buyer buyer) {

        return buyerService.addBuyer(buyer).map(b -> {
            BuyerRegistrationResponse res = new BuyerRegistrationResponse();
            res.setUserName(b.getUsername());
            res.setRoles(b.getRoles());
            return res;
        });
    }

    public Flux<Buyer> getPendingBuyerRegistrationRequests() {
        return buyerService.findBuyersByVerifiedFalse();
    }

    public Mono<Buyer> verifyBuyerRegistration(String buyerId) {
       return buyerService.findBuyerById(buyerId).map(buyer -> {
            buyer.setVerified(true);
            return buyer;
        }).flatMap(buyer -> buyerService.save(buyer));
    }
}
