package com.beecommerce.models.enums;

public enum SellingType {
    IN_STORE_SELLING_ONLY, ONLINE_SELLING_ONLY, BOTH;
    private String sellingType;
    public String getSellingType() {
        return this.sellingType;
    }
}
