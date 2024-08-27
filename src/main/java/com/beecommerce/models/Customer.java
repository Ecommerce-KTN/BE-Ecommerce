package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Node
@ToString
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
