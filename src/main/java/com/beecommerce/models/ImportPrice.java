package com.beecommerce.models;

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
@ToString
public class ImportPrice {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private Double price;
    private LocalDateTime effectiveDate;

    @Relationship(type = "HAS_IMPORT_PRICES", direction = Relationship.Direction.INCOMING)
    private Product product;
}
