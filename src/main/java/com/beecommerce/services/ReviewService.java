package com.beecommerce.services;

import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.models.Review;
import com.beecommerce.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
