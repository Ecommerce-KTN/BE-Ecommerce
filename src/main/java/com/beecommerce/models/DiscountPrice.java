package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiscountPrice {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private Double price;
    private LocalDateTime effectiveDate;
    private LocalDateTime expiredDate;
    private LocalDateTime endDate;

    @Relationship(type = "HAS_DISCOUNT_PRICES", direction = Relationship.Direction.INCOMING)
    private Product product;

}
