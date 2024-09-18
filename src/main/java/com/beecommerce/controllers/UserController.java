package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.dto.request.FavoriteProductRequest;
import com.beecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{userId}/favorites")
    public ResponseEntity<?> addFavoriteProduct(
            @PathVariable String userId,
            @RequestBody FavoriteProductRequest dto
            ) {
        if (dto.getAction() == FavoriteProductRequest.Action.ADD) {
            userService.addFavoriteProduct(userId, dto.getProductId());
        } else {
            userService.removeFavoriteProduct(userId, dto.getProductId());
        }
        return ResponseEntity
                .ok()
                .body(new ApiResponse<Object>(
                        null,
                        "Favorite list updated.",
                        HttpStatus.OK.value(),
                        true,
                        null
                ));
    }
}
