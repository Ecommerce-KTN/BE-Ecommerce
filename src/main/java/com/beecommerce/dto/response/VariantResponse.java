package com.beecommerce.dto.response;

import com.beecommerce.models.enums.ProductOption;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariantResponse {
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
