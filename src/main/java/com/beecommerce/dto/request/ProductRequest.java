package com.beecommerce.dto.request;

import com.beecommerce.models.OptionValue;
import com.beecommerce.models.ProductShape;
import com.beecommerce.models.ProductVariant;
import com.beecommerce.models.Specification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import software.amazon.awssdk.services.lexmodelsv2.model.Specifications;

import java.awt.*;
import java.util.Date;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;

    private String primaryImage;

    private String brand;

    private List<String> images;

    private String sellingType;

    private ProductShape shape;

    private String description;

    private Double basePrice;

    private Double discountPrice;

    private Double price;

    private Date createdTime;

    private List<CategoryRequest> categories;

    private List<Specification> specifications;

    private List<OptionValue> optionValues;

    private List<ProductVariant> productVariants;

}
