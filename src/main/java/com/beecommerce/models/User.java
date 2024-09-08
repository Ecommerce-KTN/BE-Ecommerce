package com.beecommerce.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "customers")
@ToString
public class User {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private boolean sex;
    private String avatar;
    private Role role;
}
