package com.beecommerce.dto.request;

import com.beecommerce.models.OptionValue;
import com.beecommerce.models.ProductShape;
import com.beecommerce.models.ProductVariant;
import com.beecommerce.models.Specification;
import com.beecommerce.models.enums.ProductOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.lexmodelsv2.model.Specifications;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank
    @Size(min = 5, max = 120)
    private String name;

    private String brand;
    private String shop;
    private MultipartFile primaryImage;
    private List<MultipartFile> images;
    private String sellingType;
    private ShapeRequest shape;
    private String description;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private List<String> categories;
    private Map<String, String> specifications;
    private Map<ProductOption, List<String>> attributes;
    private List<ProductVariantRequest> productVariants;
}