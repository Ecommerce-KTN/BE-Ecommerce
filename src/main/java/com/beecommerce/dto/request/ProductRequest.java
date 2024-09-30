package com.beecommerce.dto.request;

import com.beecommerce.models.enums.ProductOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @Id
    private String id;
    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;
    @NotEmpty(message = "Product brand cannot be empty.")
    @Size(min = 5, max = 120, message = "Product brand must be between 5 and 120 characters.")
    private String brand;
    private String shop;
    private MultipartFile primaryImage;
    private List<MultipartFile> images;
    private long quantity;
    private String sellingType;
    private ShapeRequest shape;
    private String description;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private List<String> categories;
    private Map<String, String> specifications;
    private Map<String, List<String>> attributes;
    private List<ProductVariantRequest> productVariants;
    private List<String> collections;
    private long reviewCount;
    private Double avgRating;
    private long sold;
}