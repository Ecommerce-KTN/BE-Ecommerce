package com.beecommerce.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Data
@Node
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String description;
    private String brand; // Added brand name
    private List<String> image;
    private String primaryImage;
    private Double rating;
    private int sold;
    private String sellingType;

    @Relationship(type = "HAS_CATEGORY", direction = Relationship.Direction.OUTGOING)
    private Category category;

    @Relationship(type = "HAS_COST_PRICES", direction = Relationship.Direction.OUTGOING)
    private List<CostPrice> costPrices;

    @Relationship(type = "HAS_PRICES", direction = Relationship.Direction.OUTGOING)
    private List<Price> prices;

    @Relationship(type = "HAS_DISCOUNT_PRICES", direction = Relationship.Direction.OUTGOING)
    private List<DiscountPrice> discountPrices;

    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.INCOMING)
    private List<OrderDetail> orderDetails;

    @Relationship(type = "HAS_REVIEW", direction = Relationship.Direction.INCOMING)
    private List<Review> reviews;








}
