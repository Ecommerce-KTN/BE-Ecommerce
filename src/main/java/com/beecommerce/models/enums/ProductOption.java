package com.beecommerce.models.enums;

public enum ProductOption {
    COLOR("Color"),
    RAM("RAM"),
    STORAGE("Storage");

    private final String value;

    ProductOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ProductOption fromValue(String value) {
        for (ProductOption option : values()) {
            if (option.value.equalsIgnoreCase(value)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Unknown ProductOption value: " + value);
    }
}