package com.agencyservice.agencyservice.controller;

import com.agencyservice.agencyservice.enums.ROLE;
import com.agencyservice.agencyservice.user.model.Buyer;
import com.agencyservice.agencyservice.user.model.Supplier;
import com.agencyservice.agencyservice.payload.BuyerRegistrationResponse;
import com.agencyservice.agencyservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("register/")
@Validated
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("supplier")
    public Mono<ResponseEntity<Set<ROLE>>> register(@RequestBody Supplier supplier){
        return registrationService.registerSupplier(supplier)
                .map(roles -> new ResponseEntity<>(roles, HttpStatus.CREATED))
                .doOnError(error -> System.out.println("Error occured" + error.getMessage()))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.IM_USED));
    }

    @PostMapping("buyer")
    public Mono<ResponseEntity<BuyerRegistrationResponse>> register(@RequestBody Buyer buyer){
        return registrationService.registerBuyer(buyer)
                .map(res -> new ResponseEntity<>(res, HttpStatus.CREATED))
                .doOnError(error -> System.out.println("Error occured" + error.getMessage()))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.IM_USED));
    }

    @GetMapping("pending-buyer-registrations")
    @PreAuthorize("Admin")
    public Mono<ResponseEntity<List<Buyer>>> getPendingBuyerRegistrationRequests(){
        return registrationService.getPendingBuyerRegistrationRequests().collectList()
                .map(list -> new ResponseEntity<>(list, HttpStatus.OK))
                .doOnError(error -> System.out.println("Error occured" + error.getMessage()))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("verify-buyer/{buyerId}")
    @PreAuthorize("Admin")
    public Mono<ResponseEntity> verifyBuyerRegistration(@RequestParam("buyerId") String buyerId){
        return registrationService.verifyBuyerRegistration(buyerId)
                .map(res -> new ResponseEntity(HttpStatus.NO_CONTENT))
                .doOnError(error -> System.out.println("Error occured" + error.getMessage()))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
