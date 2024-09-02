package com.beecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node
@Getter
@Setter

public class CostPrice {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private Double price;
    private LocalDateTime effectiveDate;

    @Relationship(type = "HAS_COST_PRICES", direction = Relationship.Direction.INCOMING)
    private Product product;

    @Override
    public String toString() {
        return "CostPrice{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
