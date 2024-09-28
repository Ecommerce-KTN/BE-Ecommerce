package com.beecommerce.services;

import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.models.User;
import com.beecommerce.models.enums.Role;
import com.beecommerce.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(Role.USER);
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

    public User convertToUser(Document document) {
        User user = new User();
        user.setId(document.getString("_id"));
        user.setEmail(document.getString("email"));
        user.setUsername(document.getString("username"));
        user.setPassword(document.getString("password"));
        user.setSex(document.getBoolean("sex"));
        user.setRole(Role.fromString(document.getString("role")));
        return user;
    }
}
