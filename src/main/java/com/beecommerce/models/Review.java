package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Review {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private Double rating;
    private String comment;

     @Relationship(type = "RATED_BY", direction = Relationship.Direction.OUTGOING)
     private Customer customer;

     @Relationship(type = "RATED_PRODUCT", direction = Relationship.Direction.OUTGOING)
     private Product product;

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
