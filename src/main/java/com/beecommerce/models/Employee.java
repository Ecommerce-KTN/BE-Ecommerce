package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String role;
    private String status;

}
