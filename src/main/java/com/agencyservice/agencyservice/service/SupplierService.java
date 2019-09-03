package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.enums.ROLE;
import com.agencyservice.agencyservice.exception.UserExistsException;
import com.agencyservice.agencyservice.user.model.Supplier;
import com.agencyservice.agencyservice.reactive.repository.SupplierReactiveRepository;
import com.agencyservice.agencyservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierReactiveRepository supplierReactiveRepository;

    public Mono<Supplier> addSupplier(Supplier supplier){
        supplierRepository.findByUsername(supplier.getUsername()).ifPresent(user -> {
            throw new UserExistsException("username => "+user.getUsername() + " is already occupied.");
        });
        supplierRepository.findByGstNumber(supplier.getGstNumber()).ifPresent(user -> {
            throw new UserExistsException("GST number => \""+ user.getGstNumber() + "\" is already registered with us.");
        });
        supplier.setRoles(Collections.singleton(ROLE.SUPPLIER));
        return supplierReactiveRepository.save(supplier);
    }
}
