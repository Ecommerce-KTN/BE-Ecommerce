package com.beecommerce.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private int numberOfReviews; // Added number of reviews
    private Double oldPrice; // Added old price
    private Double currentPrice; // Added current price
    private boolean isFavorited; // Added isFavorited property
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private List<Category> categories; //Update

    @Relationship(type = "HAS_IMPORT_PRICES", direction = Relationship.Direction.OUTGOING)
    @JsonIgnore
    private List<ImportPrice> importPrices;

    @Relationship(type = "HAS_SALE_PRICES", direction = Relationship.Direction.OUTGOING)
    private List<SalePrice> salePrices;

    @Relationship(type = "SUPPLIES", direction = Relationship.Direction.INCOMING)
    private Supplier supplier;

    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.INCOMING)
    private List<OrderDetail> orderDetails;





}
