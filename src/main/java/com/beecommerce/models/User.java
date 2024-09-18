package com.beecommerce.models;

import com.beecommerce.models.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    // TODO: make username unique
//    @Indexed(unique = true)
    private String username;
    private String password;
    private boolean sex;
    private String avatar;
    private Role role;
    private List<String> favoriteProducts;
}
