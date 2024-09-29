package com.beecommerce.dto.response;

import com.beecommerce.dto.ShapeResponse;
import com.beecommerce.models.ProductShape;
import com.beecommerce.models.enums.ProductOption;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String primaryImage;
    private List<String> images;
    private String brand;
    private String shop;
    private long quantity;
    private String sellingType;
    private ShapeResponse shape;
    private String description;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private List<CategoryResponse> categories;
    private Map<String, String> specifications;
    private Map<String, List<String>> attributes;
    private List<VariantResponse> productVariants;
    private Long reviewCount;
    private Double avgRating;
    private Date createdTime;
    private String priceRange;
    private String discountPriceRange;
    private List<CollectionResponse> collections;
}
