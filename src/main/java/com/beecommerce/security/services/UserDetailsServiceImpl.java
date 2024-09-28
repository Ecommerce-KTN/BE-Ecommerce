package com.beecommerce.security.services;

import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.models.CustomUserDetails;
import com.beecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

// The service to provide user details regarding application security
// It should only be used for managing user auth credentials and not be used for business logic.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.beecommerce.models.User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        return new CustomUserDetails(
            user.getUsername(),
            user.getPassword(),
            user.getId(),
            Collections.emptyList()
        );
    }

    public void registerUser(RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        com.beecommerce.models.User user = new com.beecommerce.models.User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());

        String encodedPassword = passwordEncoder.encode(req.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
    }
}
