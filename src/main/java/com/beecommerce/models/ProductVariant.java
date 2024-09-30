package com.beecommerce.models;

import com.beecommerce.models.enums.ProductOption;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVariant {
    @Id
    private String id;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private String SKU;
    private List<String> images;
    private long quantity;
    private long sold;
    private Map<ProductOption, String> attributes;
}
