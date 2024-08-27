package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Discount {
    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private Double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Relationship(type = "APPLIES_TO")
    private List<SalePrice> salePrices;
}
