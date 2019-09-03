package com.agencyservice.agencyservice.service;

import com.agencyservice.agencyservice.user.model.User;
import com.agencyservice.agencyservice.reactive.repository.UserReactiveRepository;
import com.agencyservice.agencyservice.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdminService {

    private Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserReactiveRepository userReactiveRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static final String IMPORT_ADMINS_JSON = "classpath:users/import_admins.json";

    @Value(IMPORT_ADMINS_JSON)
    private Resource resource;

    public void createAdminUsers() {
        try {
            TypeReference<User> typeReference = new TypeReference<>() {
            };
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(resource.getInputStream(), typeReference);
            log.info("creating admin users.");
            userRepository.findByUsername(user.getUsername()).orElseGet(()->userRepository.save(user));
            log.info("created admin users.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
