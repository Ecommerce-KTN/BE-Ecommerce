package com.beecommerce.models.enums;

public enum ProductOption {
    SIZE, COLOR, WEIGHT, LENGTH, WIDTH, HEIGHT, RAM, STORAGE;
    private String productOption;
    public String getProductOption() {
        return this.productOption;
    }
}
