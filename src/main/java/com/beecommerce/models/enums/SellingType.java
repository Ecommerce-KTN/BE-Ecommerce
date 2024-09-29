package com.beecommerce.models.enums;

public enum SellingType {
    INSTORE,
    ONLINE,
    BOTH;
    private String sellingType;
    public String getSellingType() {
        return this.sellingType;
    }
}
