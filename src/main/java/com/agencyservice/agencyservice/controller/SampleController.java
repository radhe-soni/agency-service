package com.agencyservice.agencyservice.controller;

import com.agencyservice.agencyservice.user.model.Buyer;
import com.agencyservice.agencyservice.user.model.Supplier;
import com.agencyservice.agencyservice.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sample/")
public class SampleController {

    @GetMapping("supplier")
    public Mono<Supplier> getSupplierSample(){
        return Mono.just(new Supplier());
    }

    @GetMapping("buyer")
    public Mono<Buyer> getBuyerSample(){
        return Mono.just(new Buyer());
    }

    @GetMapping("user")
    public Mono<User> getUserSample(){
        return Mono.just(new User());
    }
}
