package com.beecommerce.dto.reponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReviewResponse {
    private String id;
    private Double rating;
    private String comment;
    private String productId;
    private String userId;
}
