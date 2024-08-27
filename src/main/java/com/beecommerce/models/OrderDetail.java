package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.INCOMING)
    private Order order;
//    mối quan hệ
    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.OUTGOING)
    private Product product;
    private int quantity;
}
