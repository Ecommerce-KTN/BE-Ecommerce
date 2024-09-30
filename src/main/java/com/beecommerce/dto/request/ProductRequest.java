package com.beecommerce.dto.request;

import com.beecommerce.models.enums.ProductOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
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
    private String name;
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
}