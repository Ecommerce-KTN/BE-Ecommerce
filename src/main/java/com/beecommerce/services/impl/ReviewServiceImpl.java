package com.beecommerce.services.impl;

import com.beecommerce.dto.response.PaginatedResource;
import com.beecommerce.dto.request.GetProductReviewsRequest;
import com.beecommerce.dto.request.PostReviewRequest;
import com.beecommerce.mapper.ReviewMapper;
import com.beecommerce.models.Review;
import com.beecommerce.repositories.ReviewRepository;
import com.beecommerce.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    private final MongoTemplate mongoTemplate;

    public void addReview(@NotNull String userId, @NotNull PostReviewRequest dto) {
        Review review = reviewMapper.postReviewDtoToReview(dto, userId);
        review.setCreatedAt(LocalDateTime.now());
        reviewRepository.save(review);
    }

    public PaginatedResource<Review> getProductReviewsPaginated(@NotNull String productId, GetProductReviewsRequest dto, Pageable pageable) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));

        if (dto.getMinRating() != null && dto.getMaxRating() != null) {
            query.addCriteria(Criteria.where("rating").gte(dto.getMinRating()).lte(dto.getMaxRating()));
        } else if (dto.getMinRating() != null) {
            query.addCriteria(Criteria.where("rating").gte(dto.getMinRating()));
        } else if (dto.getMaxRating() != null) {
            query.addCriteria(Criteria.where("rating").lte(dto.getMaxRating()));
        }

        long totalItems = mongoTemplate.count(query, Review.class);

        query.with(pageable);
        List<Review> reviews = mongoTemplate.find(query, Review.class);

        int currentPage = pageable.getPageNumber();
        int totalPages = (int) Math.ceil((double) totalItems / pageable.getPageSize());
        boolean hasNextPage = currentPage + 1 < totalPages;

//        return new PaginatedResource<>(
//                reviews,
//                currentPage,
//                totalPages,
//                totalItems,
//                hasNextPage
//        );


        Criteria criteria = new Criteria()
                .andOperator(
                    Criteria.where("rating").gte(dto.getMinRating()),
                    Criteria.where("rating").lte(dto.getMaxRating())
                );

        Page<Review> page = reviewRepository.findAllWithCriteriaAndPageable(Review.class, criteria, pageable);
        return new PaginatedResource<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }
}
