package com.beecommerce.models;

import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SellingType;
import com.beecommerce.models.enums.SpecificationOption;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document("products")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;
    private String primaryImage;
    @NotEmpty(message = "Product brand cannot be empty.")
    @Size(min = 5, max = 120, message = "Product brand must be between 5 and 120 characters.")
    private String brand;
    private String shop;
    private long quantity;
    private List<String> images;
    private SellingType sellingType;
    private ProductShape shape;
    private String description;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    @JsonSerialize(using = DateSerializer.class)
    private Date createdTime;
    private List<Category> categories;
    private Map<SpecificationOption, String> specifications;
    private Map<ProductOption, List<String>> attributes;
    private List<ProductVariant> productVariants;
    private Long reviewCount;
    private Double avgRating;
    private long sold;
    private List<Collection> collections;

    public String calculatePriceRanges() {
        if (productVariants != null && !productVariants.isEmpty()) {
            Double minPrice = productVariants.stream().mapToDouble(ProductVariant::getPrice).min().orElse(0);
            Double maxPrice = productVariants.stream().mapToDouble(ProductVariant::getPrice).max().orElse(0);
            if(minPrice.equals(maxPrice)) {
                return String.format("%.2f", minPrice);
            }
            return String.format("%.2f - %.2f", minPrice, maxPrice);
        } else {
            return "";
        }
    }

    public String calculateDiscountPriceRanges() {
        if (productVariants != null && !productVariants.isEmpty()) {
            Double minPrice = productVariants.stream().mapToDouble(ProductVariant::getDiscountPrice).min().orElse(0);
            Double maxPrice = productVariants.stream().mapToDouble(ProductVariant::getDiscountPrice).max().orElse(0);
            if(minPrice.equals(maxPrice)) {
                return String.format("%.2f", minPrice);
            }
            return String.format("%.2f - %.2f", minPrice, maxPrice);
        } else {
            return "";
        }
    }

}