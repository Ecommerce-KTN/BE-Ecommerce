package com.beecommerce.models;

public enum Status {
    PENDING, CONFIRMED, CANCELLED, DELIVERED;
    private String status;
    public String getStatus() {
        return this.status;
    }
}
