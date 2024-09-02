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

public class Customer {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private List<Product> products;

    @Relationship(type = "HAS_ORDER", direction = Relationship.Direction.OUTGOING)
    private List<Order> orders;

    @Relationship(type = "RATED_BY", direction = Relationship.Direction.INCOMING)
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
