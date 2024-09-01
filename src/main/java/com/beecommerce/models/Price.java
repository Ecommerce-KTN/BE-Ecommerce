package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Price {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String productId;
    private Double price;
    private LocalDateTime effectiveDate;

    @Relationship(type = "HAS_PRICES", direction = Relationship.Direction.INCOMING)
    private Product product;
}
