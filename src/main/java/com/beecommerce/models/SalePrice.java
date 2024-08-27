package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalePrice {
    @Id
    @GeneratedValue
    private Long id;

    private String productId;
    private Double price;
    private LocalDateTime effectiveDate;

    @Relationship(type = "APPLIES_TO", direction = Relationship.Direction.INCOMING)
    private Discount discount;

    @Relationship(type = "HAS_SALE_PRICES", direction = Relationship.Direction.INCOMING)
    private Product product;
}
