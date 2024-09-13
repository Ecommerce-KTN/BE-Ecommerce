package com.beecommerce.services;

import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.models.User;
import com.beecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void createUser(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        userRepo.save(user);
    }
}
