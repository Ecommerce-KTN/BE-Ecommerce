package com.beecommerce.dto.request;

import lombok.Data;

@Data
public class ProductColorRequest {
    private String color;
    private String imageForColor;
}