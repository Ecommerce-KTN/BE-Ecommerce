package com.beecommerce.models.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    // Constructor nhận tham số
    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.role;
    }

    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.getRole().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Not a valid role: " + role);
    }
}
