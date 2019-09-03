package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.user.model.Buyer;
import com.agencyservice.agencyservice.user.model.CustomUserDetails;
import com.agencyservice.agencyservice.user.model.Supplier;
import com.agencyservice.agencyservice.user.model.User;
import com.agencyservice.agencyservice.repository.BuyerRepository;
import com.agencyservice.agencyservice.repository.SupplierRepository;
import com.agencyservice.agencyservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.findByUsername(username);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }

    public Stream<UserDetails> loadAllSuppliers() {
        List<Supplier> users = supplierRepository.findAll();
        return users.stream().map(CustomUserDetails::new);
    }

    public Stream<UserDetails> loadAllBuyers() {
        List<Buyer> users = buyerRepository.findAllByVerifiedTrue();
        return  users.stream().map(CustomUserDetails::new);
    }

    public List<UserDetails> loadAllUsers() {
        return userRepository.findAll().stream().map(CustomUserDetails::new).collect(Collectors.toList());
    }
}
