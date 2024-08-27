package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@Node
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    @Relationship(type = "SUPPLIES")
    private List<Product> products;



}
