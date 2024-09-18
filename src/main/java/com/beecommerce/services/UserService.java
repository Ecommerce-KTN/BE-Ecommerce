package com.beecommerce.services;

import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.models.User;
import com.beecommerce.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        userRepository.save(user);
    }

    public void addFavoriteProduct(String userId, String productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user does not exist"));
        if (!user.getFavoriteProducts().contains(productId)) {
            user.getFavoriteProducts().add(productId);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("product already added in favorite");
        }
    }

    public void removeFavoriteProduct(String userId, String productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user does not exist"));
        if (user.getFavoriteProducts().contains(productId)) {
            user.getFavoriteProducts().remove(productId);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("product is not in favorite");
        }
    }
}
