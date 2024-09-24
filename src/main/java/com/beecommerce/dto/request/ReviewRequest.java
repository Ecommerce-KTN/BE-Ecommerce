package com.beecommerce.dto.request;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequest {
    private String productId;
    private String customerId;
    private Double rating;
    private String comment;
}
