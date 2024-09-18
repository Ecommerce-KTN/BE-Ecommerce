package com.beecommerce.dto.request;
import lombok.Data;

@Data
public class PostReviewRequest {
    private String productId;
    private Double rating;
    private String comment;
}
