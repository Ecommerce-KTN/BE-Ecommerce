package com.beecommerce.models;

import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Review {
    @Id
    @GeneratedValue
    private String id;
    private Double rating;
    private String comment;
    private String productId;
    private String userId;
    private LocalDateTime createdAt;
}
