package com.beecommerce.services;

import com.beecommerce.dto.response.PaginatedResource;
import com.beecommerce.dto.request.GetProductReviewsRequest;
import com.beecommerce.dto.request.PostReviewRequest;
import com.beecommerce.models.Review;
import org.springframework.data.domain.Pageable;
import software.amazon.awssdk.annotations.NotNull;

public interface ReviewService {
    // TODO: Update product's review stats when create a new review
    void addReview(@NotNull String userId, @NotNull PostReviewRequest dto);
    PaginatedResource<Review> getProductReviewsPaginated(String productId, GetProductReviewsRequest dto, Pageable pageable);
}
