package com.beecommerce.dto.response;

import com.beecommerce.models.enums.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantResponse {
    private String id;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private String SKU;
    private List<String> images;
    private long quantity;
    private long sold;
    private Map<String, String> attributes;
}
