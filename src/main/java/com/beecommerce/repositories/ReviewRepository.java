package com.beecommerce.repositories;

import com.beecommerce.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReviewRepository extends MongoRepository<Review, String>, CustomRepository<Review> {
    @Query("{ 'productId': ?0, 'rating': { $gte: ?1, $lte: ?2 } }")
    Page<Review> findByProductId(String productId, Double minRating, Double maxRating, Pageable pageable);
}
