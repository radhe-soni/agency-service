package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.enums.ROLE;
import com.agencyservice.agencyservice.payload.LoginRequest;
import com.agencyservice.agencyservice.repository.UserBaseRepository;
import com.agencyservice.agencyservice.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoginService {

    @Autowired
    private UserBaseRepository<User> userBaseRepository;

    @Autowired
    private UserDetailsRepositoryReactiveAuthenticationManager authenticationManager;

    public Set<ROLE> performLogin(LoginRequest loginRequest){
       return userBaseRepository.findByUsername(loginRequest.getUsername())
                .filter(user-> user.getPassword().equals(loginRequest.getPassword()))
                .orElseThrow(() -> new RuntimeException("User does not exist.")).getRoles();
    }
}
