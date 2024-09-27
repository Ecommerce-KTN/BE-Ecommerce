package com.beecommerce.models.enums;

import lombok.Getter;

@Getter
public enum ProductOption {
    COLOR("Color"),
    SIZE("Size"),
    RAM("RAM"),
    STORAGE("Storage");

    private final String displayName;

    ProductOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
