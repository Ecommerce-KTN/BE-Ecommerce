package com.beecommerce.dto.response;


import com.beecommerce.models.User;

public class UserResponse {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String username;
    private Boolean sex;
    private String avatar;
    private String role;

    UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.username = user.getUsername();
        this.sex = user.isSex();
        this.avatar = user.getAvatar();
        this.role = user.getRole().toString();
    }
}
