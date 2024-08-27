package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Node
@ToString
public class Customer {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @Relationship(type = "HAS_ORDER", direction = Relationship.Direction.OUTGOING)
    private List<Order> orders;

}
