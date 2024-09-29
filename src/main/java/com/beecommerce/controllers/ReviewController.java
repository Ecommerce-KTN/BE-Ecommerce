package com.beecommerce.controllers;

import com.beecommerce.dto.request.PostReviewRequest;
import com.beecommerce.services.ReviewService;
import com.beecommerce.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    @Qualifier("JWT_ACCESS_TOKEN_UTIL")
    private JwtUtil jwtUtil;

    @Autowired
    private ReviewService reviewService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody PostReviewRequest dto) {
        System.out.println("Here");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtToken = (String) authentication.getCredentials();
        String userId = jwtUtil.getUserId(jwtToken);
        if (userId == null) {
            throw new UsernameNotFoundException("unable to extract user id");
        }
        reviewService.addReview(userId, dto);
        return new ResponseEntity<>("Review posted", HttpStatus.OK);
    }
}
