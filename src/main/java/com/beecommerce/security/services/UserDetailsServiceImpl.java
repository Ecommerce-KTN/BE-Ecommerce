package com.beecommerce.security.services;

import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.models.CustomUserDetails;
import com.beecommerce.models.enums.Role;
import com.beecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                user.getRole()
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

        user.setRole(Role.USER);

        userRepo.save(user);
    }
}
